package com.totalcross;

import com.totalcross.util.*;
import totalcross.ui.MainWindow;
import totalcross.ui.Switch;
import totalcross.ui.ImageControl;
import totalcross.io.device.gpiod.GpiodChip;
import totalcross.io.device.gpiod.GpiodLine;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.sys.Settings;

public class LightSwitch extends MainWindow {

    private GpiodChip gpioChip;
    private GpiodLine pin;
    private Switch swLightSwitch;
    private ImageControl icLight;

    public LightSwitch() {
        setUIStyle(Settings.MATERIAL_UI);
        setBackColor(Colors.COLOR_DARK_GRAY);
    }

    @Override
    public void initUI() {

        Images.loadImages();

        // Open Gpio chip
        gpioChip = GpiodChip.open(0);

        // Get Gpio line
        pin = gpioChip.line(21);

        // Request line as output and set the initial state to low
        pin.requestOutput("CONSUMER", 0);

        // Create switch components
        swLightSwitch = new Switch();

        // Set the colors of the switch's parts
        swLightSwitch.colorBallOn = Colors.COLOR_DARK_YELLOW;
        swLightSwitch.colorBallOff = Colors.COLOR_LIGHT_GRAY;
        swLightSwitch.colorBarOn = Colors.COLOR_LIGHT_YELLOW;
        swLightSwitch.colorBarOff = Colors.COLOR_MEDIUM_GRAY;

        // Create image control component with initial light off image
        icLight = new ImageControl(Images.iLightOff);
        icLight.scaleToFit = true;

        // Position light bulb image at the center of the screen,
        // with a width of 357 pixels and height of 450 pixels 
        add(icLight, CENTER, CENTER - 40, 357, 450);

        // Position switch component below the center of the screen,
        // with a width of 150 pixels and height of 30 pixels
        add(swLightSwitch, CENTER, AFTER, 150, 30);

        // Add a press listener to the switch
        swLightSwitch.addPressListener(onSwitchPressed);
    }

    PressListener onSwitchPressed = new PressListener() {
        @Override
        public void controlPressed(ControlEvent controlEvent) {
            if (swLightSwitch.isOn()) {
                icLight.setImage(Images.iLightOn);
                pin.setValue(1);
            } else {
                icLight.setImage(Images.iLightOff);
                pin.setValue(0);
            }
        }
    };
}
