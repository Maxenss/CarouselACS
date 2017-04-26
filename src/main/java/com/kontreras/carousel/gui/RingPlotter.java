package com.kontreras.carousel.gui;

import com.kontreras.carousel.core.Interface.CommandBuffer;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 06.05.13
 * Time: 20:35
 * To change this template use File | Settings | File Templates.
 */
public class RingPlotter extends AbstractPlotter {

    private int angle = 0;
    private JLabel rpmLabel;

    public RingPlotter(CommandBuffer buffer, int delay) {
        super(buffer, delay);
        rpmLabel = new JLabel("", SwingConstants.RIGHT);
        rpmLabel.setFont(new Font(rpmLabel.getFont().getFontName(), Font.PLAIN, 16));
        rpmLabel.setBounds(130, 254, 50, 20);

        add(rpmLabel);
    }

    @Override
    protected void draw() {
        angle += (int) (delay/1000.0*buffer.get().getSpeed()*360.0/60);
        rpmLabel.setText(String.valueOf(Math.abs(buffer.get().getSpeed())));
    }

    @Override
    protected void clear() {
        angle = 0;
        rpmLabel.setText("");
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        repaint();
        Graphics2D graph = (Graphics2D) g;
        graph.setStroke(new BasicStroke(2));
        graph.setPaint(Color.RED);
        //graph.drawRect(0, 0 ,getWidth()-1, getHeight()-1);
        int centerX = getWidth()/2;
        int centerY = getHeight()/2;
        int radius = getWidth()/2;

        //angle = 270 - 30;
        int newX = (int) (centerX - Math.round(radius * Math.cos((270 + angle)*Math.PI/180.0)));
        int newY = (int) (centerY - Math.round(radius * Math.sin((270 + angle)*Math.PI/180.0)));

        graph.drawLine(centerX, centerY, newX, newY);
    }


}
