package ru.job4j.concurrent;

import java.io.*;

public class ParseFile {
    private File from;
    private File to;
    private String content;
    private String contentWithoutUnicode;
    private SaveParsedFile saveParsedFile;

    public ParseFile(File from, File to) {
        this.from = from;
        this.to = to;
        this.content = initContent();
        this.contentWithoutUnicode = initContentWithoutUnicode();
        saveParsedFile = new SaveParsedFile();
    }

    public synchronized void setFrom(File f) {
        from = f;
    }

    public synchronized File getFrom() {
        return from;
    }

    public String initContent() {
        StringBuilder output = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(from))) {
            br.lines().forEach(output::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.content = output.toString();
        return this.content;
    }

    public String initContentWithoutUnicode() {
        StringBuilder output = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(from))) {
            int data;
            while ((data = br.read()) > 0) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.contentWithoutUnicode = output.toString();
        return this.contentWithoutUnicode;
    }

    public synchronized void saveContent() {
        saveParsedFile.saveContent(this.content, this.to);
    }

    public String getContent() {
        return content;
    }

    public String getContentWithoutUnicode() {
        return contentWithoutUnicode;
    }

}
