package com.kontreras.carousel.core;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 10.05.13
 * Time: 9:27
 * To change this template use File | Settings | File Templates.
 */
public class Readings {
    private String voltageAB;
    private String voltageBC;
    private String voltageCA;
    private String motorTemp;
    private String chasisStatus;
    private double currentOfLoad;
    private int rotorSpinFreq;

    public String getMotorTemp() {
        return motorTemp;
    }

    public void setMotorTemp(String motorTemp) {
        this.motorTemp = motorTemp;
    }

    public String getChasisStatus() {
        return chasisStatus;
    }

    public void setChasisStatus(String chasisStatus) {
        this.chasisStatus = chasisStatus;
    }

    public double getCurrentOfLoad() {
        return currentOfLoad;
    }

    public void setCurrentOfLoad(double currentOfLoad) {
        this.currentOfLoad = currentOfLoad;
    }

    public int getRotorSpinFreq() {
        return rotorSpinFreq;
    }

    public void setRotorSpinFreq(int rotorSpinFreq) {
        this.rotorSpinFreq = rotorSpinFreq;
    }

    public String getVoltageAB() {
        return voltageAB;
    }

    public void setVoltageAB(String voltageAB) {
        this.voltageAB = voltageAB;
    }

    public String getVoltageBC() {
        return voltageBC;
    }

    public void setVoltageBC(String voltageBC) {
        this.voltageBC = voltageBC;
    }

    public String getVoltageCA() {
        return voltageCA;
    }

    public void setVoltageCA(String voltageCA) {
        this.voltageCA = voltageCA;
    }

    @Override
    public String toString() {
        return "Readings{" +
                "chasisStatus='" + chasisStatus + '\'' +
                ", voltageAB='" + voltageAB + '\'' +
                ", voltageBC='" + voltageBC + '\'' +
                ", voltageCA='" + voltageCA + '\'' +
                ", currentOfLoad=" + currentOfLoad +
                ", rotorSpinFreq=" + rotorSpinFreq +
                '}';
    }
}
