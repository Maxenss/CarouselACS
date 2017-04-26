package com.kontreras.carousel.core.Interface;

import com.kontreras.carousel.core.Program;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 05.05.13
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
public interface ProgramList {
    String[] getProgNames();

    void loadPrograms();

    Program getCurrentProgram();
    void setCurrentProgram(int index);
}
