package com.kontreras.carousel.core;

/**
 * Created by Maxim on 26.04.2017.
 */
public class PacketWorker {
    short RS485_CS_INITIAL_VALUE = 0x5a; // 90

    // Считает контрольную сумму
    short calculateControlSum(Packet packet)
    {
        short controllSum = RS485_CS_INITIAL_VALUE;

        for (int index = 0; index < packet.getSize(); index++)
        {
            controllSum += packet.getBytesArray()[index];
        }

        return controllSum;
    }

}
