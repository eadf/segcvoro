package com.nirarebakun.segcvoro;

import java.awt.Color;
import java.awt.geom.Point2D;

public class SegcvoroApplet extends java.applet.Applet implements TurtleLogger {
  
  Color[] colors = {Color.black,Color.black,Color.yellow, Color.white,Color.green,Color.pink, Color.magenta, Color.cyan, Color.red};
  
  static final long serialVersionUID = 422334534346323215L;
  Segcvoro segcvoro;
  
  private java.awt.Graphics myG;
  
  public void init() {
    double width= toDouble(getParameter("habap", "500"));
    double height=toDouble(getParameter("takap", "350"));
    int n=toInt(getParameter("Np", "20"));
    
    int widthI=(int) width;
    int heightI=(int) height;

    if(n==20){
      n = (int) (2 + 14d*Math.random());
    }

    Point2D.Double gs[] = new Point2D.Double[n];
    Point2D.Double ge[] = new Point2D.Double[n];
    
    double dxs;
    double dxe;
    double dys;
    double dye;
    
    for(int k=0; k<n; k++){
      dxs=Math.random()*(widthI-30)+15;
      dys=Math.random()*(heightI-30)+15;
      dxe=Math.random()*(widthI-30)+15;
      dye=Math.random()*(heightI-30)+15;

      gs[k] = new Point2D.Double(dxs,dys);
      ge[k] = new Point2D.Double(dxe,dye);
   
    }//for k<n
    
    segcvoro = new Segcvoro( width, height, n, gs, ge);
  }
  
  public final double toDouble(String dous){
    return (Double.valueOf(dous)).doubleValue();
  }//toDouble
  
  public final int toInt(String dous){
    return (Integer.valueOf(dous)).intValue();
  }//toInt
  
  public void fillRect(int x,int y, int width, int height) {
    myG.fillRect(x, y, width, height);
  }
  
  public void drawString(String s, int x, int y){
    myG.drawString(s, x, y);
  }
  
  public void setColor(int col) {
    assert(col > 0 && col < colors.length);
    myG.setColor(colors[col]);
  }
  
  public void drawLine(int x0, int y0, int x1, int y1){
    myG.drawLine(x0, y0, x1, y1);
  }
  
  public void begin(int i, int j, int type){
  
  }
  public void plot(int x, int y){
    myG.drawLine(x, y, x, y);
  }
  
  public void end(){
    
  }
  
  /**
   * An implementation of getParameter that has a default value fallback
   * @param name
   * @param defaultValue
   * @return the value of the parameter or defaultValue if it was not found
   */
  public String getParameter(String name, String defaultValue) {
    String rv = getParameter(name);
    if (rv == null) rv = defaultValue;
    return rv;
  }//getParameter
  
  public void paint(java.awt.Graphics g){
    myG = g;
    segcvoro.paint(this);
  }
  
}
