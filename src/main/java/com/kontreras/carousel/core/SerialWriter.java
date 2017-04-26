package com.kontreras.carousel.core;

import com.kontreras.carousel.core.Interface.CommandBuffer;
import com.kontreras.carousel.serial.ComPort;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 09.05.13
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class SerialWriter implements Runnable {

    private ComPort port;
    private CommandBuffer buffer;

    public SerialWriter(ComPort port, CommandBuffer buffer) {
        this.port = port;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        do {
            Command cmd = buffer.get();
            String data = buildSendingData(cmd);
            //port.write(data);
        } while (true);
    }

    private String buildSendingData(Command cmd) {
        String data = null;
        switch (cmd.getType()) {

            case START: data = "";
                break;

            case STOP: data = "";
                break;

            case DATA: {
                data = ":" + "\r\n"; //cmd.getSpeed() cmd.getAngle()
                break;
            }
        }
        return data;
    }
}
