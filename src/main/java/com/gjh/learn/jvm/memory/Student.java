package com.gjh.learn.jvm.memory;

import com.gjh.learn.jvm.utils.Print;

import java.util.List;
import java.util.Vector;

/**
 * created on 2021/3/28
 *
 * @author kevinlights
 */
public class Student {
    private int id;
    private String name;
    private List<WebPage> history = new Vector<WebPage>();

    public Student(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public void visit(WebPage wp) {
        Print.GREEN("{} visit {}", name, wp.getUrl());
        history.add(wp);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WebPage> getHistory() {
        return history;
    }

    public void setHistory(List<WebPage> history) {
        this.history = history;
    }
}
