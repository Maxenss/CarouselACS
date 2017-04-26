package com.kontreras.carousel.gui;

import javax.swing.*;

import com.kontreras.carousel.core.Interface.AppController;
import com.kontreras.carousel.utils.ConfigHolder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private AppController controller;
	
	public MainWindow(AppController controller, Plotter graphPlotter, Plotter ringPlotter, Indicator indicator) {

        this.controller = controller;

        setSize(ConfigHolder.MAIN_WINDOW_WIDTH, ConfigHolder.MAIN_WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new BackgroundImage();
        panel.setLayout(null);

        ((Component) graphPlotter).setBounds(120, 497, 840, 150);
        ((Component) ringPlotter).setBounds(90, 44, 350, 350);

        createControlButtons(panel);

        panel.add((Component) graphPlotter);
        panel.add((Component) ringPlotter);
        panel.add(indicator);

        setContentPane(panel);

        setLocationRelativeTo(null);
		setVisible(true);

	}

    private void createControlButtons(Container container) {
        JButton btnLoad = new JButton("");
        btnLoad.setBounds(412, 25, 90, 90);
        btnLoad.setContentAreaFilled(false);
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.selectProgram();
            }
        });
        container.add(btnLoad);

        JButton btnRun = new JButton("");
        btnRun.setBounds(412, 325, 90, 90);
        btnRun.setContentAreaFilled(false);
        btnRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startProgram();
            }
        });
        container.add(btnRun);

        JButton btnConfig = new JButton("");
        btnConfig.setBounds(32, 25, 90, 90);
        btnConfig.setContentAreaFilled(false);
        btnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.config();
            }
        });
        container.add(btnConfig);

        JButton btnStop = new JButton("");
        btnStop.setBounds(32, 325, 90, 90);
        btnStop.setContentAreaFilled(false);
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stopProgram();
            }
        });
        container.add(btnStop);
    }

}
