package com.gjh.learn.jvm.memory;

/**
 * created on 2021/3/28
 *
 * @author kevinlights
 */
public class WebPage {
    private String url;
    private String content;
    private byte[] b;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.b = new byte[1024*1024];
    }
}
