package com.kontreras.carousel.serial;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 09.05.13
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
public interface ComPort {

    void write(String data);
    String read();
    void init();
    void close();
}
