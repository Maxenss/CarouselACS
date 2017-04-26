package com.kontreras.carousel.core;

import com.kontreras.carousel.core.Interface.CommandBuffer;

// Разобраться
public class HoldingCommandBuffer implements CommandBuffer {

    private Command cmd;

    @Override
    public synchronized Command get() {
        return cmd;
    }

    @Override
    public synchronized void put(Command cmd) {
        this.cmd = cmd;
    }
}
