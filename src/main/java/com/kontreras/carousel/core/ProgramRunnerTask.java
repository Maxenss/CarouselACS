package com.kontreras.carousel.core;

import com.kontreras.carousel.core.Interface.CommandBuffer;
import com.kontreras.carousel.core.enums.CommandType;
import com.kontreras.carousel.gui.Plotter;
import org.apache.log4j.Logger;

/**
* Created with IntelliJ IDEA.
* User: Dmitriy
* Date: 10.05.13
* Time: 16:06
* To change this template use File | Settings | File Templates.
*/
class ProgramRunnerTask implements Runnable {

    public static final Logger LOG = Logger.getLogger(ProgramRunnerTask.class);

    private Plotter graphChart;
    private Plotter ringChart;
    private CommandBuffer cmdDrawBuffer;
    private CommandBuffer cmdSerialBuffer;
    private Program prog;

    ProgramRunnerTask(Plotter graphPlotter, Plotter ringPlotter, CommandBuffer cmdDrawBuffer, CommandBuffer cmdSendBuffer, Program prog) {
        this.graphChart = graphPlotter;
        this.ringChart = ringPlotter;
        this.cmdDrawBuffer = cmdDrawBuffer;
        this.cmdSerialBuffer = cmdSendBuffer;
        this.prog = prog;
    }

    @Override
    public void run() {
        Command cmdStart = new Command(0, 0, CommandType.START);
        cmdDrawBuffer.put(cmdStart);
        cmdSerialBuffer.put(cmdStart);
        graphChart.startDraw();
        ringChart.startDraw();
        for (Command cmd : prog.getCommands()) {
            try {
                LOG.debug(cmd);
                cmdSerialBuffer.put(cmd);
                cmdDrawBuffer.put(cmd);
                Thread.sleep(cmd.getTime());
            } catch (InterruptedException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        graphChart.stopDraw();
        ringChart.stopDraw();

        Command cmdStop = new Command(0, 0, CommandType.STOP);
        cmdSerialBuffer.put(cmdStop);
    }
}
