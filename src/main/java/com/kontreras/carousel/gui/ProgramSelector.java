package com.kontreras.carousel.gui;

import com.kontreras.carousel.core.Interface.ProgramList;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramSelector extends JFrame {

    private static ProgramSelector instance;

    public static final ImageIcon SELECT_ICON = new ImageIcon("images/apply.png");
    public static final ImageIcon REFRESH_ICON = new ImageIcon("images/refresh.png");
    public static final ImageIcon LIST_UP_ICON = new ImageIcon("images/folder_up.png");
    public static final ImageIcon LIST_DOWN_ICON = new ImageIcon("images/folder_down.png");

    private ProgramList progList;
    //String[] data = new String[103];

    private JList<String> list;
    private JScrollPane listPane;
    private JButton btnSelect;
    private JButton btnRefresh;
    private JButton btnUp;
    private JButton btnDown;

    private int currentPage = 1;

    private ProgramSelector(ProgramList progList) {

        this.progList = progList;

        createList();
        createToolbar();

        JPanel panel = new JPanel(new MigLayout());
        panel.add(listPane, "span");
        panel.add(btnSelect, "width 130");
        panel.add(btnRefresh, "width 130");
        panel.add(btnUp, "width 130");
        panel.add(btnDown, "width 130");
        add(panel);

        setUndecorated(true);
        //setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        pack();
        setLocationRelativeTo(null);
    }

    public static void show(ProgramList progList) {
        if (instance == null) {
            instance = new ProgramSelector(progList);
        }
        instance.setVisible(true);
    }

    private void createToolbar() {
        btnSelect = new JButton("");
        btnSelect.setIcon(SELECT_ICON);
        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(list.getSelectedIndex());
                progList.setCurrentProgram(list.getSelectedIndex());
                setVisible(false);
            }
        });

        btnRefresh = new JButton("");
        btnRefresh.setIcon(REFRESH_ICON);
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progList.loadPrograms();
            }
        });

        btnUp = new JButton("");
        btnUp.setIcon(LIST_UP_ICON);
        btnUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollUp();
            }
        });

        btnDown = new JButton("");
        btnDown.setIcon(LIST_DOWN_ICON);
        btnDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollDown();
            }
        });
    }

    private void scrollUp() {
        int size = list.getModel().getSize();
        int index = (--currentPage)*10 - 10 + 1;
        if (index<1) {
            index = 1;
            currentPage++;
        }
        list.ensureIndexIsVisible(index - 1);
    }

    private void scrollDown() {
        int size = list.getModel().getSize();
        int index = (++currentPage)*10;
        if (index>size) {
            index = size;
            currentPage--;
        }
        list.ensureIndexIsVisible(index - 1);
    }

    private void createList() {
        list = new JList<>(progList.getProgNames());
        list.setFixedCellHeight(50);
        list.setFixedCellWidth(530);
        list.setFont(new Font(list.getFont().getFontName(), Font.PLAIN, 18));
        list.setCellRenderer(new MyListCellRenderer());
        list.setVisibleRowCount(10);
        listPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private static class MyListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if ((index % 2 == 0) && (! isSelected)) {
                label.setBackground(new Color(250, 250, 250)); //.setBorder(new LineBorder(Color.BLACK, 1));
            }
            return label;
        }
    }

}
