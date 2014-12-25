package com.nirarebakun.segcvoro

import java.awt.Color
import java.awt.geom.Point2D

class SegcvoroApplet extends java.applet.Applet with TurtleLogger {

  val colors = Array(Color.black, Color.black, Color.yellow, Color.white, Color.green, 
                     Color.pink, Color.magenta, Color.cyan, Color.red)
  
  final val serialVersionUID = 422334534346323215L
  var segcvoro:Segcvoro = null
  var myWidth = 0
  var myHeight = 0
  var gs:Array[Point2D.Double] = null
  var ge:Array[Point2D.Double] = null
  
  private var myG:java.awt.Graphics = null
  
  override def init:Unit = {
    myWidth = getParameter("habap", "500").toInt
    myHeight = getParameter("takap", "350").toInt
    
    val (an,ags,age) = if (false) randomSegments(myWidth,myHeight) else getSegments(myWidth,myHeight)
    ge = age
    gs = ags
    segcvoro = new Segcvoro( myWidth, myHeight, gs, ge)
  }
  
  def randomSegments(width:Int, height:Int) = {
    var n = getParameter("Np", "20").toInt

    if(n==20){
      n = (2 + 14d*Math.random).toInt
    }

    val gs = new Array[Point2D.Double](n)
    val ge = new Array[Point2D.Double](n)
    
    def f(p:Double) = Math.random*(p-30)+15
    for(k <- 0 until n) {
      gs(k) = new Point2D.Double(f(width),f(height))
      ge(k) = new Point2D.Double(f(width),f(height))
   
    }//for k<n
    (n,gs,ge)
  }
  
  def getSegments(width:Int, height:Int) = {
    val template = Array( (0.0001d,0.0001d), (1d,0d), (0.9999d,0.9999d), (0d,1d), (0d,0d))
    val oSegments = template.map(p=>new Point2D.Double(p._1*0.8*width+0.1*width, p._2*0.8*height + 0.1*height))
    val iSegments = template.map(p=>new Point2D.Double(p._1*0.1*width+0.5*width, p._2*0.1*height + 0.5*height))

    val n = oSegments.size + iSegments.size
    val gs = new Array[Point2D.Double](n-2)
    val ge = new Array[Point2D.Double](n-2)
    
    var p = 0
    var i = 0
    for(j <- 1 until oSegments.size) {
      gs(p) = oSegments(i)
      ge(p) = oSegments(j)
      i += 1
      p += 1
    }//for k<n
    
    i = 0
    for(j <- 1 until iSegments.size) {
      gs(p) = iSegments(i)
      ge(p) = iSegments(j)
      i += 1
      p += 1
    }//for k<n
    (n,gs,ge)
  }
  
  def fillRect(x:Int, y:Int, width:Int, height:Int) = myG.fillRect(x, y, width, height)
  
  def drawString(s:String, x:Int, y:Int) = myG.drawString(s, x, y)
  
  def setColor(col:Int) = {
    assert(col > 0 && col < colors.length)
    myG.setColor(colors(col))
  }
  
  def drawLine(x0:Int, y0:Int, x1:Int, y1:Int) = myG.drawLine(x0, y0, x1, y1)
  
  def begin(i:Int, j:Int, aType:Int) = {
  }
  
  def plot(x:Int, y:Int) = myG.drawLine(x, y, x, y)
  
  def end() = {
  }
  
  /**
   * An implementation of getParameter that has a default value fallback
   * @param name
   * @param defaultValue
   * @return the value of the parameter or defaultValue if it was not found
   */
  def getParameter(name:String, defaultValue:String)= {
    var rv = getParameter(name)
    if (rv == null) defaultValue else rv
  }//getParameter
  
  override def paint( g:java.awt.Graphics ) = {
    myG = g
    val n = gs.size
    g.setColor(colors(1));
    g.fillRect(1,1,myWidth,myHeight);
    g.setColor(colors(3));
    g.drawString("N="+n,15,15);
    
    for( k <- 0 until n){
      setColor( 3);
      drawLine(gs(k).x.toInt,gs(k).y.toInt,ge(k).x.toInt,ge(k).y.toInt);
      setColor( 2);
      drawString(""+k,((gs(k).x+ge(k).x)/2d).toInt,((gs(k).y+ge(k).y)/2d).toInt);
    }//for k<n
    
    segcvoro.paint(this)
  }
}