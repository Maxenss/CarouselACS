package com.kontreras.carousel.core;

import com.kontreras.carousel.core.Interface.CommandBuffer;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 09.05.13
 * Time: 21:45
 * To change this template use File | Settings | File Templates.
 */
public class BlockingCommandBuffer implements CommandBuffer {

    private LinkedBlockingQueue<Command> buffer = new LinkedBlockingQueue<>(100);

    @Override
    public Command get() {
        Command cmd = null;
        try {
            cmd = buffer.take();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return cmd;
    }

    @Override
    public void put(Command cmd) {
        try {
            System.out.println(cmd);
            buffer.put(cmd);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
