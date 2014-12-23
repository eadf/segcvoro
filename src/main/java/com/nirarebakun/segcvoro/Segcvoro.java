package com.nirarebakun.segcvoro;

import java.awt.Color;

/**
 * Use of Takashi OHYAMA's website (Open 2012/9/16, 0th revision Sunday, 16-Sep-2012 14:57:22 JST)
 * Please consider as follows;
 *
 * If you use program codes or other materials of Takashi OHYAMA's website, please write my name or website address in the References as follows;
 * Takashi Ohyama's website, http://www.nirarebakun.com/
 * or
 * Takashi OHYAMA's website of ***, http://www.nirarebakun.com/***.html
 *
 * These program codes and materials are free, but I am happy if you might pay donation.
 * 
 * @author Takashi Ohyama
 */
public class Segcvoro extends java.applet.Applet {

  static final long serialVersionUID = 422334534346323214L;

  final double pi = Math.PI;
  final double pi2 = Math.PI*2d;
  Color gcol1,gcol2,gcol3,gcol4,gcol5,gcol6,gcol7;
  int N,heightI,widthI;
  int i,j,k;
  double Nd,heightD,widthD;
  double tmpw;
  int br;

  double gxs[]=new double[100];
  double gys[]=new double[100];
  double gxe[]=new double[100];
  double gye[]=new double[100];

  double gslope[]=new double[100];
  double gintercept[]=new double[100];

  double gx,gy;

  double grx1;
  double gry1;
  double grx2;
  double gry2;

  double gux1;
  double guy1;
  double gux2;
  double guy2;
  double gux3;
  double guy3;
  double gux4;
  double guy4;

  double gpboriginx;
  double gpboriginy;
  double gpbijsp;
  double gpbijstheta;
  double gpbijep;
  double gpbijetheta;

  public final double toDouble(String dous){
    return (Double.valueOf(dous)).doubleValue();
  }//toDouble

  public double rand(){
    double rand1;
    rand1=Math.random();
    return rand1;
  }//rand

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
  
  public void init(){
    gcol1=Color.black;
    gcol2=Color.yellow;
    gcol3=Color.white;
    gcol4=Color.green;
    gcol5=Color.pink;
    gcol6=Color.magenta;
    gcol7=Color.cyan;

    widthD=toDouble(getParameter("habap", "500"));
    heightD=toDouble(getParameter("takap", "350"));
    Nd=toDouble(getParameter("Np", "20"));
    widthI=(int)widthD;
    heightI=(int)heightD;
    N=(int)Nd;
    if(N==20){
      N=2+(int)(14*rand());
    }
  }//init

  public final double pow(double a,double b){
    return Math.pow(a,b);
  }//pow

  public final double dis(double x1,double y1,double x2,double y2){
    return pow(pow(x2-x1,2.0)+pow(y2-y1,2.0),0.5);
  }//dis

  //main function
  //main関数
  public void paint(java.awt.Graphics g){

    //s is start
    //e is end
    //l is left
    //r is right
    //s sometime is l
    //e sometime is r

    double slx;
    double sly;
    double srx;
    double sry;

    double elx;
    double ely;
    double erx;
    double ery;

    double selx;
    double sely;
    double serx;
    double sery;

    double eslx;
    double esly;
    double esrx;
    double esry;




    double dxs;
    double dxe;
    double dys;
    double dye;
    double dslope;
    double dintercept;

    int xsI[]=new int[100];
    int ysI[]=new int[100];
    int xeI[]=new int[100];
    int yeI[]=new int[100];

    g.setColor(gcol1);
    g.fillRect(1,1,widthI,heightI);
    g.setColor(gcol3);
    g.drawString("N="+N,15,15);
    k=0;
    while(k<N){
      dxs=rand()*(widthI-30)+15;
      dys=rand()*(heightI-30)+15;
      dxe=rand()*(widthI-30)+15;
      dye=rand()*(heightI-30)+15;
      dslope=(dye-dys)/(dxe-dxs);
      dintercept=dys-dslope*dxs;

      gxs[k]=dxs;
      gys[k]=dys;
      gxe[k]=dxe;
      gye[k]=dye;
      if(gxs[k]>gxe[k]){
        tmpw=gxs[k];
        gxs[k]=gxe[k];
        gxe[k]=tmpw;
        tmpw=gys[k];
        gys[k]=gye[k];
        gye[k]=tmpw;
      }
      xsI[k]=(int)(gxs[k]);
      ysI[k]=(int)(gys[k]);
      xeI[k]=(int)(gxe[k]);
      yeI[k]=(int)(gye[k]);
      g.setColor(gcol3);
      g.drawLine(xsI[k],ysI[k],xeI[k],yeI[k]);
      g.setColor(gcol2);
      g.drawString(""+k,(xsI[k]+xeI[k])/2,(ysI[k]+yeI[k])/2);
      gslope[k]=dslope;
      gintercept[k]=dintercept;
      k++;
    }//while k<n

    for(i=0;i<N-1;i++){
      for(j=i+1;j<N;j++){

        //bisector of i's left vertex and j's left vertex
        bisec2poi(gxs[i],gys[i],gxs[j],gys[j]);
        slx=grx1;
        sly=gry1;
        srx=grx2;
        sry=gry2;
        g.setColor(Color.yellow);
        //                g.drawLine((int)(slx),(int)(sly),(int)(srx),(int)(sry));
        double ldi;
        double lys;
        ldi=(sly-sry)/(slx-srx);
        lys=sly-ldi*slx;
        double lx;
        double ly;
        //                for(lx=slx;lx<srx;lx=lx+0.5){
        double z1;
        double z1start;
        double z1end;
        z1start=slx;
        z1end=srx;
        if(Math.abs(ldi)>1.0){
          if(ldi>1.0){
            z1start=slx*ldi+lys;
            z1end=srx*ldi+lys;
          }else{
            z1start=srx*ldi+lys;
            z1end=slx*ldi+lys;
          }
        }
        ldi=(sly-sry)/(slx-srx);
        for(z1=z1start;z1<z1end;z1=z1+0.5){
          if(Math.abs(ldi)<1.0){
            lx=z1;
            ly=lx*ldi+lys;
          }else{
            ly=z1;
            lx=(ly-lys)/ldi;
          }
          int cnt;
          cnt=0;
          double disis;
          double disiseps;
          double disjseps;
          disis=dis(lx,ly,gxs[i],gys[i]);
          disiseps=dis(lx,ly,gxs[i]*0.999+gxe[i]*0.001,gys[i]*0.999+gye[i]*0.001);
          disjseps=dis(lx,ly,gxs[j]*0.999+gxe[j]*0.001,gys[j]*0.999+gye[j]*0.001);
          if(disiseps<disis){
            cnt++;
          }
          if(disjseps<disis){
            cnt++;
          }
          if(cnt==0){
            if(cntorder(i,j,lx,ly,disis)==0){
              g.drawLine((int)(lx),(int)(ly),(int)(lx),(int)(ly));
            }
          }
        }//lx

        //bisector of i's right vertex and j's right vertex
        bisec2poi(gxe[i],gye[i],gxe[j],gye[j]);
        elx=grx1;
        ely=gry1;
        erx=grx2;
        ery=gry2;
        g.setColor(Color.cyan);
        //                g.drawLine((int)(elx),(int)(ely),(int)(erx),(int)(ery));
        ldi=(ely-ery)/(elx-erx);
        lys=ely-ldi*elx;
        //                for(lx=elx;lx<erx;lx=lx+0.5){
        z1start=elx;
        z1end=erx;
        if(Math.abs(ldi)>1.0){
          if(ldi>1.0){
            z1start=elx*ldi+lys;
            z1end=erx*ldi+lys;
          }else{
            z1start=erx*ldi+lys;
            z1end=elx*ldi+lys;
          }
        }
        elx=grx1;
        ely=gry1;
        erx=grx2;
        ery=gry2;
        ldi=(ely-ery)/(elx-erx);
        for(z1=z1start;z1<z1end;z1=z1+0.5){
          if(Math.abs(ldi)<1.0){
            lx=z1;
            ly=lx*ldi+lys;
          }else{
            ly=z1;
            lx=(ly-lys)/ldi;
          }

          int cnt;
          cnt=0;
          double disie;
          double disieeps;
          double disjeeps;
          disie=dis(lx,ly,gxe[i],gye[i]);
          disieeps=dis(lx,ly,gxe[i]*0.999+gxs[i]*0.001,gye[i]*0.999+gys[i]*0.001);
          disjeeps=dis(lx,ly,gxe[j]*0.999+gxs[j]*0.001,gye[j]*0.999+gys[j]*0.001);
          if(disieeps<disie){
            cnt++;
          }
          if(disjeeps<disie){
            cnt++;
          }
          if(cnt==0){
            if(cntorder(i,j,lx,ly,disie)==0){
              g.drawLine((int)(lx),(int)(ly),(int)(lx),(int)(ly));
            }
          }
        }//lx

        //bisector of i's left vertex and j's right vertex
        bisec2poi(gxs[i],gys[i],gxe[j],gye[j]);
        selx=grx1;
        sely=gry1;
        serx=grx2;
        sery=gry2;
        g.setColor(Color.yellow);
        //                g.drawLine((int)(selx),(int)(sely),(int)(serx),(int)(sery));
        ldi=(sely-sery)/(selx-serx);
        lys=sely-ldi*selx;

        z1start=selx;
        z1end=serx;
        if(Math.abs(ldi)>1.0){
          if(ldi>1.0){
            z1start=selx*ldi+lys;
            z1end=serx*ldi+lys;
          }else{
            z1start=serx*ldi+lys;
            z1end=selx*ldi+lys;
          }
        }
        selx=grx1;
        sely=gry1;
        serx=grx2;
        sery=gry2;
        ldi=(sely-sery)/(selx-serx);
        for(z1=z1start;z1<z1end;z1=z1+0.5){
          if(Math.abs(ldi)<1.0){
            lx=z1;
            ly=lx*ldi+lys;
          }else{
            ly=z1;
            lx=(ly-lys)/ldi;
          }
          //                    ly=lx*ldi+lys;
          int cnt;
          cnt=0;
          double disis;
          double disiseps;
          double disjeeps;
          disis=dis(lx,ly,gxs[i],gys[i]);
          disiseps=dis(lx,ly,gxs[i]*0.999+gxe[i]*0.001,gys[i]*0.999+gye[i]*0.001);
          disjeeps=dis(lx,ly,gxe[j]*0.999+gxs[j]*0.001,gye[j]*0.999+gys[j]*0.001);
          if(disiseps<disis){
            cnt++;
          }
          if(disjeeps<disis){
            cnt++;
          }
          if(cnt==0){
            if(cntorder(i,j,lx,ly,disis)==0){
              g.drawLine((int)(lx),(int)(ly),(int)(lx),(int)(ly));
            }
          }
        }//lx

        //bisector of i's right vertex and j's right vertex
        bisec2poi(gxe[i],gye[i],gxs[j],gys[j]);
        eslx=grx1;
        esly=gry1;
        esrx=grx2;
        esry=gry2;
        g.setColor(Color.magenta);
        //                g.drawLine((int)(eslx),(int)(esly),(int)(esrx),(int)(esry));
        ldi=(esly-esry)/(eslx-esrx);
        lys=esly-ldi*eslx;

        z1start=eslx;
        z1end=esrx;
        if(Math.abs(ldi)>1.0){
          if(ldi>1.0){
            z1start=eslx*ldi+lys;
            z1end=esrx*ldi+lys;
          }else{
            z1start=esrx*ldi+lys;
            z1end=eslx*ldi+lys;
          }
        }
        eslx=grx1;
        esly=gry1;
        esrx=grx2;
        esry=gry2;
        ldi=(esly-esry)/(eslx-esrx);
        for(z1=z1start;z1<z1end;z1=z1+0.5){
          if(Math.abs(ldi)<1.0){
            lx=z1;
            ly=lx*ldi+lys;
          }else{
            ly=z1;
            lx=(ly-lys)/ldi;
          }
          //                    ly=lx*ldi+lys;
          int cnt;
          cnt=0;
          double disie;
          double disieeps;
          double disjseps;
          disie=dis(lx,ly,gxe[i],gye[i]);
          disieeps=dis(lx,ly,gxe[i]*0.999+gxs[i]*0.001,gye[i]*0.999+gys[i]*0.001);
          disjseps=dis(lx,ly,gxs[j]*0.999+gxe[j]*0.001,gys[j]*0.999+gye[j]*0.001);
          if(disieeps<disie){
            cnt++;
          }
          if(disjseps<disie){
            cnt++;
          }
          if(cnt==0){
            if(cntorder(i,j,lx,ly,disie)==0){
              g.drawLine((int)(lx),(int)(ly),(int)(lx),(int)(ly));
            }
          }
        }//lx


        //bisector of two line segments
        bisec2seg(i,j);
        double lsegcsx1;
        double lsegcsy1;
        double lsegcex1;
        double lsegcey1;
        double lsegcsx2;
        double lsegcsy2;
        double lsegcex2;
        double lsegcey2;
        lsegcsx1=gux1;
        lsegcsy1=guy1;
        lsegcex1=gux2;
        lsegcey1=guy2;
        g.setColor(Color.green);
        //                g.drawOval((int)(gx)-3,(int)(gy)-3,6,6);
        //                g.drawLine((int)(lsegcsx1),(int)(lsegcsy1),(int)(lsegcex1),(int)(lsegcey1));
        ldi=(lsegcsy1-lsegcey1)/(lsegcsx1-lsegcex1);
        lys=lsegcsy1-ldi*lsegcsx1;

        z1start=lsegcsx1;
        z1end=lsegcex1;
        if(Math.abs(ldi)>1.0){
          if(ldi>1.0){
            z1start=lsegcsx1*ldi+lys;
            z1end=lsegcex1*ldi+lys;
          }else{
            z1start=lsegcex1*ldi+lys;
            z1end=lsegcsx1*ldi+lys;
          }
        }
        lsegcsx1=gux1;
        lsegcsy1=guy1;
        lsegcex1=gux2;
        lsegcey1=guy2;
        ldi=(lsegcsy1-lsegcey1)/(lsegcsx1-lsegcex1);
        for(z1=z1start;z1<z1end;z1=z1+0.5){
          if(Math.abs(ldi)<1.0){
            lx=z1;
            ly=lx*ldi+lys;
          }else{
            ly=z1;
            lx=(ly-lys)/ldi;
          }
          //                    ly=lx*ldi+lys;
          int cnt;
          cnt=0;
          double dii;
          double ysi;
          double intersectionxi;
          dii=-1.0/gslope[i];
          ysi=ly-dii*lx;
          intersectionxi=(ysi-gintercept[i])/(gslope[i]-dii);
          if(intersectionxi<gxs[i] || intersectionxi>gxe[i]){
            cnt++;
          }//intersectionxi<gxs[i] or intersectionxi>gxe[i]
          double dij;
          double ysj;
          double intersectionxj;
          dij=-1.0/gslope[j];
          ysj=ly-dij*lx;
          intersectionxj=(ysj-gintercept[j])/(gslope[j]-dij);
          if(intersectionxj<gxs[j] || intersectionxj>gxe[j]){
            cnt++;
          }
          if(cnt==0){
            double disijc;
            disijc=Math.abs(gslope[i]*lx-ly+gintercept[i])/pow(pow(gslope[i],2.0)+1.0,0.5);
            if(cntorder(i,j,lx,ly,disijc)==0){
              g.drawLine((int)(lx),(int)(ly),(int)(lx),(int)(ly));
            }
          }
        }//lx
        lsegcsx2=gux3;
        lsegcsy2=guy3;
        lsegcex2=gux4;
        lsegcey2=guy4;
        //                g.drawLine((int)(lsegcsx2),(int)(lsegcsy2),(int)(lsegcex2),(int)(lsegcey2));
        ldi=(lsegcsy2-lsegcey2)/(lsegcsx2-lsegcex2);
        lys=lsegcsy2-ldi*lsegcsx2;

        z1start=lsegcsx2;
        z1end=lsegcex2;
        if(Math.abs(ldi)>1.0){
          if(ldi>1.0){
            z1start=lsegcsx2*ldi+lys;
            z1end=lsegcex2*ldi+lys;
          }else{
            z1start=lsegcex2*ldi+lys;
            z1end=lsegcsx2*ldi+lys;
          }
        }
        lsegcsx2=gux3;
        lsegcsy2=guy3;
        lsegcex2=gux4;
        lsegcey2=guy4;
        ldi=(lsegcsy2-lsegcey2)/(lsegcsx2-lsegcex2);
        for(z1=z1start;z1<z1end;z1=z1+0.5){
          if(Math.abs(ldi)<1.0){
            lx=z1;
            ly=lx*ldi+lys;
          }else{
            ly=z1;
            lx=(ly-lys)/ldi;
          }
          //                    ly=lx*ldi+lys;
          int cnt;
          cnt=0;
          double dii;
          double ysi;
          double intersectionxi;
          dii=-1.0/gslope[i];
          ysi=ly-dii*lx;
          intersectionxi=(ysi-gintercept[i])/(gslope[i]-dii);
          if(intersectionxi<gxs[i] || intersectionxi>gxe[i]){
            cnt++;
          }//intersectionxi<gxs[i] or intersectionxi>gxe[i]
          double dij;
          double ysj;
          double intersectionxj;
          dij=-1.0/gslope[j];
          ysj=ly-dij*lx;
          intersectionxj=(ysj-gintercept[j])/(gslope[j]-dij);
          if(intersectionxj<gxs[j] || intersectionxj>gxe[j]){
            cnt++;
          }
          if(cnt==0){
            double disijc;
            disijc=Math.abs(gslope[i]*lx-ly+gintercept[i])/pow(pow(gslope[i],2.0)+1.0,0.5);
            if(cntorder(i,j,lx,ly,disijc)==0){
              g.drawLine((int)(lx),(int)(ly),(int)(lx),(int)(ly));
            }
          }
        }//lx

      }//j
    }//i

    for(i=0;i<N-1;i++){
      for(j=i+1;j<N;j++){

        //bisector of segment and a point
        //bisector is parabola arc.
        double lpboriginx;
        double lpboriginy;
        double x;
        double y;
        double xrmp;
        double yrmp;
        double ystart;
        double yend;

        double disjs;
        double disje;
        int cnt;

        double lpbijsp;
        double lpbijstheta;
        //parabola bisector of line segment i and left point of segment j
        bisecsegpois(i,j);
        lpbijsp=gpbijsp;
        lpbijstheta=pi-gpbijstheta;
        if(gxs[j]>gpboriginx){
          lpbijstheta=pi2-gpbijstheta;
        }
        lpboriginx=gpboriginx;
        lpboriginy=gpboriginy;
        double yisrm;
        double yierm;
        yisrm=Math.sin(lpbijstheta)*(gxs[i]-lpboriginx)+Math.cos(lpbijstheta)*(gys[i]-lpboriginy);
        yierm=Math.sin(lpbijstheta)*(gxe[i]-lpboriginx)+Math.cos(lpbijstheta)*(gye[i]-lpboriginy);
        ystart=yisrm;
        yend=yierm;
        if(yisrm>yierm){
          ystart=yierm;
          yend=yisrm;
        }
        lpboriginx=gpboriginx;
        lpboriginy=gpboriginy;
        g.setColor(Color.green);
        for(y=ystart;y<yend;y=y+0.1){
          x=pow(y,2.0)/(4.0*lpbijsp);
          xrmp=lpboriginx+Math.cos(-lpbijstheta)*x-Math.sin(-lpbijstheta)*y;
          yrmp=lpboriginy+Math.sin(-lpbijstheta)*x+Math.cos(-lpbijstheta)*y;
          cnt=0;
          disjs=dis(xrmp,yrmp,gxs[j],gys[j]);
          disje=dis(xrmp,yrmp,gxe[j],gye[j]);
          if(disje<disjs){
            cnt++;
          }else{
            double disjseps;
            disjseps=dis(xrmp,yrmp,gxs[j]*0.999+gxe[j]*0.001,gys[j]*0.999+gye[j]*0.001);
            if(disjseps<disjs){
              cnt++;
            }
          }
          lpboriginx=gpboriginx;
          lpboriginy=gpboriginy;
          if(cnt==0){
            if(cntorder(i,j,xrmp,yrmp,disjs)==0){
              g.drawLine((int)(xrmp),(int)(yrmp),(int)(xrmp),(int)(yrmp));
            }
          }//cnt==0
        }//y

        double lpbijep;
        double lpbijetheta;
        //parabola bisector of line segment i and right point of segment j
        bisecsegpoie(i,j);
        lpbijep=gpbijep;
        lpbijetheta=pi-gpbijetheta;
        if(gxe[j]>gpboriginx){
          lpbijetheta=pi2-gpbijetheta;
        }
        lpboriginx=gpboriginx;
        lpboriginy=gpboriginy;
        yisrm=Math.sin(lpbijetheta)*(gxs[i]-lpboriginx)+Math.cos(lpbijetheta)*(gys[i]-lpboriginy);
        yierm=Math.sin(lpbijetheta)*(gxe[i]-lpboriginx)+Math.cos(lpbijetheta)*(gye[i]-lpboriginy);
        ystart=yisrm;
        yend=yierm;
        if(yisrm>yierm){
          ystart=yierm;
          yend=yisrm;
        }
        lpboriginx=gpboriginx;
        lpboriginy=gpboriginy;
        g.setColor(Color.pink);
        for(y=ystart;y<yend;y=y+0.1){
          x=pow(y,2.0)/(4.0*lpbijep);
          xrmp=lpboriginx+Math.cos(-lpbijetheta)*x-Math.sin(-lpbijetheta)*y;
          yrmp=lpboriginy+Math.sin(-lpbijetheta)*x+Math.cos(-lpbijetheta)*y;
          cnt=0;
          disjs=dis(xrmp,yrmp,gxe[j],gye[j]);
          disje=dis(xrmp,yrmp,gxs[j],gys[j]);
          if(disje<disjs){
            cnt++;
          }else{
            double disjeeps;
            disjeeps=dis(xrmp,yrmp,gxe[j]*0.999+gxs[j]*0.001,gye[j]*0.999+gys[j]*0.001);
            if(disjeeps<disjs){
              cnt++;
            }
          }
          if(cnt==0){
            if(cntorder(i,j,xrmp,yrmp,disjs)==0){
              g.drawLine((int)(xrmp),(int)(yrmp),(int)(xrmp),(int)(yrmp));
            }
          }//cnt==0
        }//y

        double lpbjisp;
        double lpbjistheta;
        //parabola bisector of line segment j and left point of segment i
        bisecsegpois(j,i);
        lpbjisp=gpbijsp;
        lpbjistheta=pi-gpbijstheta;
        if(gxs[i]>gpboriginx){
          lpbjistheta=pi2-gpbijstheta;
        }
        lpboriginx=gpboriginx;
        lpboriginy=gpboriginy;
        double yjsrm;
        double yjerm;
        yjsrm=Math.sin(lpbjistheta)*(gxs[j]-lpboriginx)+Math.cos(lpbjistheta)*(gys[j]-lpboriginy);
        yjerm=Math.sin(lpbjistheta)*(gxe[j]-lpboriginx)+Math.cos(lpbjistheta)*(gye[j]-lpboriginy);
        ystart=yjsrm;
        yend=yjerm;
        if(yjsrm>yjerm){
          ystart=yjerm;
          yend=yjsrm;
        }
        lpboriginx=gpboriginx;
        lpboriginy=gpboriginy;
        g.setColor(Color.cyan);
        for(y=ystart;y<yend;y=y+0.1){
          x=pow(y,2.0)/(4.0*lpbjisp);
          xrmp=lpboriginx+Math.cos(-lpbjistheta)*x-Math.sin(-lpbjistheta)*y;
          yrmp=lpboriginy+Math.sin(-lpbjistheta)*x+Math.cos(-lpbjistheta)*y;
          cnt=0;
          disjs=dis(xrmp,yrmp,gxs[i],gys[i]);
          disje=dis(xrmp,yrmp,gxe[i],gye[i]);
          if(disje<disjs){
            cnt++;
          }else{
            double disiseps;
            disiseps=dis(xrmp,yrmp,gxs[i]*0.999+gxe[i]*0.001,gys[i]*0.999+gye[i]*0.001);
            if(disiseps<disjs){
              cnt++;
            }
          }
          if(cnt==0){
            if(cntorder(i,j,xrmp,yrmp,disjs)==0){
              g.drawLine((int)(xrmp),(int)(yrmp),(int)(xrmp),(int)(yrmp));
            }
          }
        }//y

        double lpbjiep;
        double lpbjietheta;
        //parabola bisector of line segment j and right point of segment i
        bisecsegpoie(j,i);
        lpbjiep=gpbijep;
        lpbjietheta=pi-gpbijetheta;
        if(gxe[i]>gpboriginx){
          lpbjietheta=pi2-gpbijetheta;
        }
        lpboriginx=gpboriginx;
        lpboriginy=gpboriginy;
        yjsrm=Math.sin(lpbjietheta)*(gxs[j]-lpboriginx)+Math.cos(lpbjietheta)*(gys[j]-lpboriginy);
        yjerm=Math.sin(lpbjietheta)*(gxe[j]-lpboriginx)+Math.cos(lpbjietheta)*(gye[j]-lpboriginy);
        ystart=yjsrm;
        yend=yjerm;
        if(yjsrm>yjerm){
          ystart=yjerm;
          yend=yjsrm;
        }
        lpboriginx=gpboriginx;
        lpboriginy=gpboriginy;
        g.setColor(Color.red);
        for(y=ystart;y<yend;y=y+0.1){
          x=pow(y,2.0)/(4.0*lpbjiep);
          xrmp=lpboriginx+Math.cos(-lpbjietheta)*x-Math.sin(-lpbjietheta)*y;
          yrmp=lpboriginy+Math.sin(-lpbjietheta)*x+Math.cos(-lpbjietheta)*y;
          cnt=0;
          disjs=dis(xrmp,yrmp,gxe[i],gye[i]);
          disje=dis(xrmp,yrmp,gxs[i],gys[i]);
          if(disje<disjs){
            cnt++;
          }else{
            double disieeps;
            disieeps=dis(xrmp,yrmp,gxe[i]*0.999+gxs[i]*0.001,gye[i]*0.999+gys[i]*0.001);
            if(disieeps<disjs){
              cnt++;
            }
          }
          if(cnt==0){
            if(cntorder(i,j,xrmp,yrmp,disjs)==0){
              g.drawLine((int)(xrmp),(int)(yrmp),(int)(xrmp),(int)(yrmp));
            }
          }//cnt==0
        }//y

      }//j
    }//i
  }//paint main

  //bisectors of two points
  void bisec2poi( double tx1, double ty1, double tx2, double ty2){
    double ssdi;
    double ssdi2;
    double ssys;
    double sscx;
    double sscy;

    ssdi=(ty1-ty2)/(tx1-tx2);
    ssdi2=-1.0/ssdi;
    sscx=(tx1+tx2)/2.0;
    sscy=(ty1+ty2)/2.0;
    ssys=sscy-ssdi2*sscx;

    grx1=0.0;
    gry1=ssys;
    if(ssys<0.0){
      grx1=-ssys/ssdi2;
      gry1=0.0;
    }
    if(ssys>heightD){
      grx1=(heightD-ssys)/ssdi2;
      gry1=heightD;
    }
    grx2=widthD;
    gry2=ssdi2*widthD+ssys;
    if(gry2<0.0){
      grx2=-ssys/ssdi2;
      gry2=0.0;
    }
    if(ssdi2*widthD+ssys>heightD){
      grx2=(heightD-ssys)/ssdi2;
      gry2=heightD;
    }
  }//bisec2poi

  //2 bisectors of line segment i and line segment j
  void bisec2seg(int pari,int parj){


    double lcx1;
    double lcy1;

    double lthetai;
    double lthetaj;
    double lctheta;
    double lcslope;
    double lcys;


    lcx1=100.0;
    lcy1=10.0;

    lcx1=(gintercept[pari]-gintercept[parj])/(gslope[parj]-gslope[pari]);
    lcy1=gslope[pari]*lcx1+gintercept[pari];

    lthetai=Math.atan(gslope[pari]);
    lthetaj=Math.atan(gslope[parj]);
    lctheta=(lthetai+lthetaj)/2.0;
    lcslope=Math.tan(lctheta);
    lcys=lcy1-lcslope*lcx1;

    gux1=0.0;
    guy1=lcys;
    if(lcys<0.0){
      gux1=-lcys/lcslope;
      guy1=0.0;
    }
    if(lcys>heightD){
      gux1=(heightD-lcys)/lcslope;
      guy1=heightD;
    }
    gux2=widthD;
    guy2=lcslope*widthD+lcys;
    if(guy2<0.0){
      gux2=-lcys/lcslope;
      guy2=0.0;
    }
    if(lcslope*widthD+lcys>heightD){
      gux2=(heightD-lcys)/lcslope;
      guy2=heightD;
    }

    lthetaj=Math.atan(gslope[parj]);
    if(lthetai<lthetaj){
      lthetai=lthetai+pi;
    }else{
      lthetaj=lthetaj+pi;
    }
    lctheta=(lthetai+lthetaj)/2.0;
    lcslope=Math.tan(lctheta);
    lcys=lcy1-lcslope*lcx1;

    gux3=0.0;
    guy3=lcys;
    if(lcys<0.0){
      gux3=-lcys/lcslope;
      guy3=0.0;
    }
    if(lcys>heightD){
      gux3=(heightD-lcys)/lcslope;
      guy3=heightD;
    }
    gux4=widthD;
    guy4=lcslope*widthD+lcys;
    if(guy4<0.0){
      gux4=-lcys/lcslope;
      guy4=0.0;
    }
    if(lcslope*widthD+lcys>heightD){
      gux4=(heightD-lcys)/lcslope;
      guy4=heightD;
    }

  }//bisec2poi

  //parabola bisector of line segment i and left point of segment j
  void bisecsegpois(int pari, int parj){
    double lthetaj;
    double dis;
    double lslopej;
    double linterceptj;
    double lp;
    double lintersectionx;
    double lintersectiony;
    double lcx;
    double lcy;

    //distance between line i and point(left point of j)
    dis=Math.abs(gslope[pari]*gxs[parj]-gys[parj]+gintercept[pari])/pow(pow(gslope[pari],2.0)+1.0,0.5);
    lp=dis/2.0;
    lslopej=-1.0/gslope[pari];
    linterceptj=gys[parj]-lslopej*gxs[parj];
    lintersectionx=(gintercept[pari]-linterceptj)/(lslopej-gslope[pari]);
    lintersectiony=lintersectionx*lslopej+linterceptj;
    lcx=(gxs[parj]+lintersectionx)/2.0;
    lcy=(gys[parj]+lintersectiony)/2.0;
    lthetaj=Math.atan(lslopej);

    gpboriginx=lcx;
    gpboriginy=lcy;
    gpbijsp=lp;
    gpbijstheta=lthetaj;

  }//bisecsegpois

  //parabola bisector of line segment i and right point of segment j
  void bisecsegpoie(int pari, int parj){

    //distance between line i and point(right point of j)
    double dis=Math.abs(gslope[pari]*gxe[parj]-gye[parj]+gintercept[pari])/pow(pow(gslope[pari],2.0)+1.0,0.5);
    double lp=dis/2.0;
    double lslopej=-1.0/gslope[pari];
    double linterceptj=gye[parj]-lslopej*gxe[parj];
    double lintersectionx=(gintercept[pari]-linterceptj)/(lslopej-gslope[pari]);
    double lintersectiony=lintersectionx*lslopej+linterceptj;
    double lcx=(gxe[parj]+lintersectionx)/2.0;
    double lcy=(gye[parj]+lintersectiony)/2.0;
    double lthetaj=Math.atan(lslopej);

    gpboriginx=lcx;
    gpboriginy=lcy;
    gpbijep=lp;
    gpbijetheta=lthetaj;

  }//bisecsegpoie

  public int cntorder(int si, int sj, double sx, double sy, double sd){
    int lcnt;
    int l;
    lcnt=0;
    for(l=0;l<N;l++){
      if(l!=si && l!=sj){
        double disl;
        disl=Math.abs(gslope[l]*sx-sy+gintercept[l])/pow(pow(gslope[l],2.0)+1.0,0.5);
        if(disl<sd){
          double dil;
          double ysl;
          double intersectionxl;
          dil=-1.0/gslope[l];
          ysl=sy-dil*sx;
          intersectionxl=(ysl-gintercept[l])/(gslope[l]-dil);
          if(intersectionxl<gxs[l] || intersectionxl>gxe[l]){
            double disls=dis(sx,sy,gxs[l],gys[l]);
            if(disls<sd){
              lcnt++;
            }else{
              double disle;
              disle=dis(sx,sy,gxe[l],gye[l]);
              if(disle<sd){
                lcnt++;
              }
            }
          }else{
            lcnt++;
          }
          if(lcnt>0){
            break;
          }
        }//disl<sd
      }//l!=si && l!=sj
    }//l
    return lcnt;
  }//cntorder

}

