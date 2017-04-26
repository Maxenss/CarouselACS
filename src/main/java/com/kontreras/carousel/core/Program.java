package com.kontreras.carousel.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// Класс представляет собой одну заданную последовательность действия для карусели(программу)
public class Program implements Comparable<Program> {
    // Файл с командой (xml)
    private File file;
    // Название программы
    private String name;
    // Список команд, которые пренадлежат программе
    private List<Command> commands = new ArrayList<>();

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Program{" +
                "file=" + file +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Program program = (Program) o;

        if (!file.equals(program.file)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return file.hashCode();
    }

    @Override
    public int compareTo(Program p) {
        return p.name.compareTo(name);
    }
}
