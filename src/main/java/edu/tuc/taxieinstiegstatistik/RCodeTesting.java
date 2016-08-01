package edu.tuc.taxieinstiegstatistik;

import java.util.Arrays;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

public class RCodeTesting {
//Nas
	public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        //R-engine starten
        Rengine re = new Rengine(null, false, null);
        
        //in R: >a<- c(1.2,2.3,4.5) :
        double da[] = {1.2, 2.3, 4.5};
        long xp3 = re.rniPutDoubleArray(da);
        re.rniAssign("a", xp3, 0);
        //look up for a:
        REXP x;
        x = re.eval("a");
        System.out.println(x);
        
       //Eigene GPS Daten
        double ownGPSlon[] = {-73.997459411621000, -74.00164794921870, -73.963340759277300, -74.009086608886700};
        double ownGPSlat[] = {40.736362457275300, 40.7242431640625, 40.802787780761700, 40.713817596435500};
        System.out.println(Arrays.toString(ownGPSlon));
        System.out.println(Arrays.toString(ownGPSlat));
        String ownGPSStrlon = Arrays.toString(ownGPSlon);
        String ownGPSStrlat = Arrays.toString(ownGPSlat);
    

        
     //   BufferedImage bi = new BufferedImage(480,480, BufferedImage.TYPE_INT_ARGB);
      //  re.eval("bmp(filename='D:/rjavabmptest.bmp', width = 480, height = 480)");
   
        re.eval("library(ggplot2)");
        re.eval("library(ggmap)");
        
     //   re.eval("bmp(filename='D:/OHA.bmp', width = 1920, height = 1080)");
     //   re.eval("plot(get_map(location = \"Osterode am Harz\", zoom = 15,maptype = \"hybrid\"))");
	 //   re.eval(" dev.off()");
      
          re.eval("GPSdata <- read.csv('D:/tripdatacompact4.csv',header=T,sep=\",\")");
          re.eval("mapget <- get_map(location = \"New York Manhattan\", zoom = 12,maptype = \"hybrid\")");
          
      //    re.eval("d=data.frame(beauty=c(1,2,6,4,4,6,7,8))");
      //    re.eval("d=data.frame(beauty=c(1,2,6,4,4,6,7,8))");
      //    x = re.eval("d");
      //    System.out.println("x: " +x);
          String commandString = "d=data.frame(lon=c(" +ownGPSStrlon +"), lat=c(" +ownGPSStrlat +"))";
          System.out.println("commandString: " +commandString);
          
          commandString = commandString.replaceAll("\\[", "").replaceAll("\\]","");
          System.out.println("commandString: " +commandString);
         
          re.eval(commandString);
    //    re.eval("map2 <- ggmap(mapget)+ geom_point(data=GPSdata,aes(x = pickup_longitude,y = pickup_latitude),colour = \"red\", size = 0.01)");
          re.eval("map2 <- ggmap(mapget)+ geom_point(data=d,aes(x = lon, y = lat),colour = \"red\", size = 0.1)");
          
      //    re.eval("finalmap <- ggmap(mapget) + stat_density2d(aes(x = pickup_longitude, y = pickup_latitude, fill = ..level.., alpha = ..level..),size = 0.1, data = GPSdata,geom = \"polygon\")+ scale_fill_gradient(low=\"red\", high=\"green\") + scale_alpha(range = c(0.1,1), guide=FALSE)");
          re.eval("bmp(filename='D:/Manhattan.bmp', width = 1024, height = 1024)");
      //    re.eval("plot(finalmap)");
          re.eval("plot(map2)");
          re.eval(" dev.off()");
	   System.out.println("done!");
 
        
    }
	
}
