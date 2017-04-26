package com.kontreras.carousel.core.Interface;

import com.kontreras.carousel.core.Command;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 09.05.13
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */
public interface CommandBuffer {
    Command get();

    void put(Command cmd);
}
