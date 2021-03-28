package com.gjh.learn.jvm.memory;

import java.util.List;
import java.util.Vector;

/**
 * created on 2021/3/28
 *
 * @author kevinlights
 */
public class TraceStudent {
    static List<WebPage> webPages = new Vector<WebPage>();
    public static void createWebPages() {
        for (int i = 0; i < 100; i++) {
            WebPage wp = new WebPage();
            wp.setUrl("http://www." + Integer.toString(i) + ".com");
            wp.setContent(Integer.toString(i));
            webPages.add(wp);
        }
    }

    public static void main(String[] args) {
        createWebPages();
        Student a = new Student(3, "studentA");
        Student b = new Student(5, "studentB");
        Student c = new Student(7, "studentC");

        for (int i = 0; i < webPages.size(); i++) {
            if (i % a.getId() == 0) {
                a.visit(webPages.get(i));
            }
            if (i % b.getId() == 0) {
                b.visit(webPages.get(i));
            }
            if (i % c.getId() == 0) {
                c.visit(webPages.get(i));
            }
        }
        webPages.clear();
        System.gc();
    }
}
