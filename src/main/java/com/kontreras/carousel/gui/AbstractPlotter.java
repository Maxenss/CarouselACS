package com.kontreras.carousel.gui;

import com.kontreras.carousel.core.Interface.CommandBuffer;

import javax.swing.*;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 07.05.13
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractPlotter extends JComponent implements Plotter {

    protected CommandBuffer buffer;
    protected java.util.Timer timer;
    protected  int delay;

    public AbstractPlotter(CommandBuffer buffer, int delay) {
        this.buffer = buffer;
        this.delay = delay;
        setDoubleBuffered(true);
    }

    @Override
    public void startDraw() {
        clear();
        timer = new java.util.Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                draw();
            }
        }, 0, delay);

    }

    @Override
    public void stopDraw() {
        timer.cancel();
        clear();
    }

    protected abstract void draw();
    protected abstract void clear();
}
