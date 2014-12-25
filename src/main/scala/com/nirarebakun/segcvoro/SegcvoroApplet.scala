package com.nirarebakun.segcvoro

import java.awt.Color
import java.awt.geom.Point2D

class SegcvoroApplet extends java.applet.Applet with TurtleLogger {

  val colors = Array(Color.black,Color.black,Color.yellow, Color.white,Color.green,Color.pink, Color.magenta, Color.cyan, Color.red)
  
  final val serialVersionUID = 422334534346323215L
  var segcvoro:Segcvoro = null
  
  private var myG:java.awt.Graphics = null
  
  override def init:Unit = {
    val width = getParameter("habap", "500").toDouble
    val height = getParameter("takap", "350")toDouble
    var n = getParameter("Np", "20").toInt
    
    val widthI=width.toInt
    val heightI=height.toInt

    if(n==20){
      n = (2 + 14d*Math.random()).toInt
    }

    val gs = new Array[Point2D.Double](n)
    val ge = new Array[Point2D.Double](n)
    
    var dxs = 0d
    var dxe = 0d
    var dys = 0d
    var dye = 0d
    
    for(k <- 0 until n){
      dxs=Math.random()*(widthI-30)+15
      dys=Math.random()*(heightI-30)+15
      dxe=Math.random()*(widthI-30)+15
      dye=Math.random()*(heightI-30)+15

      gs(k) = new Point2D.Double(dxs,dys)
      ge(k) = new Point2D.Double(dxe,dye)
   
    }//for k<n
    
    segcvoro = new Segcvoro( width, height, n, gs, ge)
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
    segcvoro.paint(this)
  }
}