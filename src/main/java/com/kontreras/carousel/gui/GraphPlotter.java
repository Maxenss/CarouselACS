package com.kontreras.carousel.gui;

import com.kontreras.carousel.core.Interface.CommandBuffer;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 04.05.13
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */
public class GraphPlotter extends AbstractPlotter {

    private int lastX;
    private int lastY = 75;

    public GraphPlotter(CommandBuffer buffer, int delay) {
       super(buffer, delay);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void clear() {
        repaint();
        lastX = 0;
        lastY = 75;
    }

   /* public void startDraw() {
        lastX = 0;
        lastY = 75;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                draw();
            }
        }, 100, 1000);


    }*/

    @Override
    protected void draw() {
        drawNextPoint(buffer.get().getSpeed());
    }

    public void drawNextPoint(int speed) {
        Graphics2D graph = (Graphics2D) getGraphics();
        graph.setStroke(new BasicStroke(2));
        graph.setPaint(Color.RED);
        int newX = lastX + 3;
        int newY = 75 - speed * 5;
        graph.drawLine(lastX, lastY, lastX, newY);
        graph.drawLine(lastX, newY, newX, newY);
        lastX = newX;
        lastY = newY;
    }

}
