package com.kontreras.carousel.core;

import com.kontreras.carousel.core.Interface.ProgramList;
import com.kontreras.carousel.core.enums.CommandType;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitriy
 * Date: 04.05.13
 * Time: 16:51
 * To change this template use File | Settings | File Templates.
 */
public class ProgramListImpl implements ProgramList {

    public static final Logger LOG = Logger.getLogger(ProgramListImpl.class);

    private List<Program> programs;
    private int currentProgramIndex;

    public ProgramListImpl() {
        this.programs = new ArrayList<>();
        loadPrograms();
        currentProgramIndex = -1;
    }

    @Override
    public String[] getProgNames() {
        String[] names = new String[programs.size()];
        int i = 0;
        for (Program p : programs) {
            names[i++] = p.getName();
        }
        return names;
    }

    @Override
    public void loadPrograms() {
        programs.clear();
        File progDirectory = new File("programs");
        File[] progList = progDirectory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        });

        LOG.info("Loading program files:");
        SAXBuilder builder = new SAXBuilder();
        for (File file : progList) {
            LOG.info(file.getName());
            try {
                Document document = builder.build(file);
                Element root = document.getRootElement();
                Program prog = new Program();
                prog.setFile(file);
                prog.setName(root.getAttributeValue("name"));
                for (Element cmdXml : root.getChildren("command")) {
                    Command cmd = new Command(
                            Integer.parseInt(cmdXml.getAttributeValue("angle")),
                            Integer.parseInt(cmdXml.getAttributeValue("speed")),
                            CommandType.DATA);
                    prog.getCommands().add(cmd);
                }
                programs.add(prog);
            } catch (JDOMException e) {
                LOG.error("Program file parsing error! \n" + e.getMessage());
            } catch (IOException e) {
                LOG.error("Program file i/o error! \n" + e.getMessage());
            }
        }
    }

    @Override
    public Program getCurrentProgram() {
        return programs.get(currentProgramIndex);
    }

    @Override
    public void setCurrentProgram(int index) {
        currentProgramIndex = index;
    }

}
