package com.totalcross.util;
import totalcross.ui.image.Image;
import totalcross.ui.dialog.MessageBox;

public class Images {

   public static Image iLightOff, iLightOn;

   public static void loadImages() {

       try {
           iLightOff = new Image("images/light-bulb-off.png");
           iLightOn = new Image("images/light-bulb-on.png");

       } catch (Exception e) {
           MessageBox.showException(e, true);
       }
   }
}