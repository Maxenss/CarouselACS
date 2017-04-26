package com.kontreras.carousel.serial;

import static com.kontreras.carousel.utils.ConfigHolder.*;

import com.kontreras.carousel.utils.ConfigHolder;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 09.05.13
 * Time: 20:28
 * To change this template use File | Settings | File Templates.
 */
public class ComPortImpl implements ComPort {

    public static final Logger LOG = Logger.getLogger(ComPortImpl.class);

    private SerialPort serialPort;

    public ComPortImpl() {
        init();
    }

    @Override
    public void write(String data) {
        try {
            LOG.info("Com port write: " + data);
            serialPort.writeString(data);
        } catch (SerialPortException e) {
            LOG.error("Com port write error! \n" + e.getMessage());
        }
    }

    @Override
    public String read() {
        String data = null;
        try {
            data = serialPort.readString();
            LOG.info("Com port read: " + data);
        } catch (SerialPortException e) {
            LOG.error("Com port read error! \n" + e.getMessage());
        }
        return data;
    }

    // Создаем объект ком-порта и конфигурируем его данными
    @Override
    public void init() {
        serialPort =  new SerialPort(COMPORT_NAME);
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        }
        catch (SerialPortException e) {
            LOG.error("Com port initialization error! \n" + e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            serialPort.closePort();
        } catch (SerialPortException e) {
            LOG.error("Com port close error! \n" + e.getMessage());
            System.exit(0);
        }
    }
}
