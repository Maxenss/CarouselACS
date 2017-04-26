package com.kontreras.carousel.core;

import static com.kontreras.carousel.utils.ConfigHolder.*;

import com.kontreras.carousel.gui.Indicator;
import com.kontreras.carousel.serial.ComPort;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 09.05.13
 * Time: 22:45
 * To change this template use File | Settings | File Templates.
 */
public class SerialReader  implements Runnable {

    public static final Logger LOG = Logger.getLogger(SerialReader.class);

    private ComPort port;
    private Readings readings;
    private Indicator indicator;

    public SerialReader(ComPort port, Indicator indicator) {
        this.port = port;
        this.indicator = indicator;
        readings = new Readings();
    }

    @Override
    public void run() {
        do {
            port.write(":" + GET_DATA_CMD + "\r\n");
            String data = port.read();
            decode(data);
            indicator.print(readings);
            try {
                Thread.sleep(20*1000);
            } catch (InterruptedException e) {
                LOG.error("SerialReader unexpected error! \n" + e.getMessage());
            }
        } while (true);
    }

    private void decode(String data) {
        LOG.info("Indicator Data: " + data);
        readings.setVoltageAB(Long.toString(Math.round(Math.random() * 200)));
        readings.setVoltageBC(Long.toString(Math.round(Math.random() * 200)));
        readings.setVoltageCA(Long.toString(Math.round(Math.random() * 200)));
        readings.setMotorTemp(Long.toString(Math.round(Math.random() * 50)));
        readings.setChasisStatus(Long.toString(Math.round(Math.random()*10)));
    }

    public Indicator getIndicator() {
        return indicator;
    }
}
