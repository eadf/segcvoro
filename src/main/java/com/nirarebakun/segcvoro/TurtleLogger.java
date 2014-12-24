package com.nirarebakun.segcvoro;

public interface TurtleLogger {
  
  void begin(int i, int j, int type);
  void plot(int x, int y);
  void end();
  
  void fillRect(int x1, int y1, int width, int height);
  void drawString(String s, int x, int y);
  void setColor(int col);
  void drawLine(int x0, int y0, int x1, int y1);
  
  /**
   * An implementation of getParameter that has a default value fallback
   * @param name
   * @param defaultValue
   * @return the value of the parameter or defaultValue if it was not found
   */
  public String getParameter(String name, String defaultValue);
}
