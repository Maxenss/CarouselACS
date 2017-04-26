package com.kontreras.carousel.core;

import com.kontreras.carousel.core.Interface.AppController;
import com.kontreras.carousel.core.Interface.CommandBuffer;
import com.kontreras.carousel.core.Interface.ProgramList;
import com.kontreras.carousel.gui.Indicator;
import com.kontreras.carousel.gui.Plotter;
import com.kontreras.carousel.gui.ProgramSelector;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 10.05.13
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
public class AppControllerImp implements AppController {

    private ProgramList progList;
    private Indicator indicator;
    private Plotter graphPlotter;
    private Plotter ringPlotter;
    private CommandBuffer cmdDrawBuffer;
    private CommandBuffer cmdSerialBuffer;

    public AppControllerImp(CommandBuffer cmdDrawBuffer, CommandBuffer cmdSerialBuffer, ProgramList progList, Plotter graphPlotter, Plotter ringPlotter, Indicator indicator) {
        this.cmdDrawBuffer = cmdDrawBuffer;
        this.cmdSerialBuffer = cmdSerialBuffer;
        this.graphPlotter = graphPlotter;
        this.indicator = indicator;
        this.progList = progList;
        this.ringPlotter = ringPlotter;
    }

    @Override
    public void startProgram() {
        Program currentProgram = progList.getCurrentProgram();
        System.out.println("Start program " + currentProgram);
        if (currentProgram != null) {
            ProgramRunnerTask task = new ProgramRunnerTask(graphPlotter, ringPlotter, cmdDrawBuffer, cmdSerialBuffer,  currentProgram);
            Thread thread = new Thread(task);
            thread.start();
        }

    }

    @Override
    public void stopProgram() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void selectProgram() {
        ProgramSelector.show(progList);
    }

    @Override
    public void config() {
        //reserve
    }
}
