package com.kontreras.carousel.core;

class Packet {
    static final int MAX_PACKET_SIZE = 255;

    static char targetId = 'A';       // адрес прибора к которому мы хотим достучаться, символы от A..Z, a..z - наш адрес 'A'
    static char requesterId = '0';    // адресс прибора, от которого идет посылка
    int commandId;                    // код команды
    int numOfData;                    // Количество данных
    byte[] data;                      // Данные
    short controllSum;                 // Контрольная сумма

    private byte[] bytesArray;

    // Пакет в "готовом" виде
    String packetData;

    int getSize() {
        return bytesArray.length;
    }

    public byte[] getBytesArray() {
        return bytesArray;
    }

    public void setBytesArray(byte[] bytes) {
        this.bytesArray = bytes;
    }

    @Override
    public String toString() {
        // Тут составляем пакет
        return "";
    }
}
