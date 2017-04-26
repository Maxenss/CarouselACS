package com.kontreras.carousel;

import com.kontreras.carousel.core.*;
import com.kontreras.carousel.core.Interface.AppController;
import com.kontreras.carousel.core.Interface.CommandBuffer;
import com.kontreras.carousel.core.Interface.ProgramList;
import com.kontreras.carousel.gui.*;
import com.kontreras.carousel.serial.ComPort;
import com.kontreras.carousel.serial.ComPortImpl;
import com.kontreras.carousel.utils.ConfigHolder;
import jssc.SerialPortList;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CarouselApp {

    public static final Logger LOG = Logger.getLogger(CarouselApp.class);

    public static void main(String[] args) {
        // PropertyConfigurator.configure("log4j.properties");
        // testserial();
        //	loadConfig();
        // init();

        int a = 3;
        int b = 10;
        int c = 20;
        String str = "" + a + b + c;
        System.out.println(str);
        byte ar[] = str.getBytes();
        System.out.println(str.getBytes().length);

        for (int i = 0; i < ar.length; i++) {
         System.out.println(ar[i]);
        }

    }

    private static void init() {
        ProgramList progList = new ProgramListImpl();
        progList.loadPrograms();

        CommandBuffer cmdDrawBuffer = new HoldingCommandBuffer();
        CommandBuffer cmdSerialBuffer = new BlockingCommandBuffer();

        Indicator indicator = new Indicator();
        Plotter ringPlotter = new RingPlotter(cmdDrawBuffer, 200);
        Plotter graphPlotter = new GraphPlotter(cmdDrawBuffer, 1000);

        ComPort port = new ComPortImpl();

        SerialReader reader = new SerialReader(port, indicator);
        Thread readThread = new Thread(reader);
        readThread.start();

        SerialWriter writer = new SerialWriter(port, cmdSerialBuffer);
        Thread writeThread = new Thread(writer);
        writeThread.start();

        createMainWindow(cmdDrawBuffer, cmdSerialBuffer, progList, graphPlotter, ringPlotter, indicator);
    }

    private static void createMainWindow(final CommandBuffer cmdDrawBuffer, final CommandBuffer cmdSerialBuffer, final ProgramList progList, final Plotter graphPlotter, final Plotter ringPlotter, final Indicator indicator) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new NimbusLookAndFeel());
                } catch (UnsupportedLookAndFeelException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                AppController controller = new AppControllerImp(cmdDrawBuffer, cmdSerialBuffer, progList, graphPlotter, ringPlotter, indicator);
                new MainWindow(controller, graphPlotter, ringPlotter, indicator);
            }
        });
    }

    public static void loadConfig() {
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("app.properties"));
            ConfigHolder.init(prop);

        } catch (FileNotFoundException e) {
            LOG.error("Properties file not found! \n" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            LOG.error("Properties file i/o error! \n" + e.getMessage());
            System.exit(0);
        }
    }

    public static void testserial() {
        String[] portNames = SerialPortList.getPortNames();
        LOG.info("Available com ports:");
        for (int i = 0; i < portNames.length; i++) {
            LOG.info(portNames[i]);
        }
    }


}
