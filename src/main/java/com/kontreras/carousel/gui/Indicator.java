package com.kontreras.carousel.gui;

import static com.kontreras.carousel.utils.ConfigHolder.*;

import com.kontreras.carousel.core.Readings;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 08.05.13
 * Time: 23:41
 * To change this template use File | Settings | File Templates.
 */
public class Indicator extends JComponent{


    public static final int TOP_X = 220;
    public static final int TOP_Y = 33;
    public static int INDICATOR_LABEL_STEP = 24;

    private IndicatorLabel voltageABLabel = new IndicatorLabel("", TOP_X, TOP_Y);
    private IndicatorLabel voltageBCLabel = new IndicatorLabel("", TOP_X, TOP_Y + INDICATOR_LABEL_STEP);
    private IndicatorLabel voltageCALabel = new IndicatorLabel("", TOP_X, TOP_Y + 2*INDICATOR_LABEL_STEP);
    private IndicatorLabel motorTempLabel = new IndicatorLabel("", TOP_X, TOP_Y + 3*INDICATOR_LABEL_STEP);
    private IndicatorLabel chasisStatusLabel = new IndicatorLabel("", TOP_X, TOP_Y + 4*INDICATOR_LABEL_STEP);

    public Indicator() {
        setBounds(560, 65, 350, 350);
        setOpaque(false);
        add(voltageABLabel);
        add(voltageBCLabel);
        add(voltageCALabel);
        add(motorTempLabel);
        add(chasisStatusLabel);
    }

    public void print(Readings readings) {
        System.out.println(readings);
        voltageABLabel.setText(readings.getVoltageAB());
        voltageBCLabel.setText(readings.getVoltageBC());
        voltageCALabel.setText(readings.getVoltageCA());
        motorTempLabel.setText(readings.getMotorTemp());
        chasisStatusLabel.setText(readings.getChasisStatus());
        //repaint();
    }

    private class IndicatorLabel extends JLabel {

        IndicatorLabel(String caption, int topX, int topY) {
            super(caption);
            setFont(new Font(getFont().getFontName(), Font.PLAIN, 18));
            setForeground(Color.blue);
            setBounds(topX, topY, 70, 20);
        }
    }
}
