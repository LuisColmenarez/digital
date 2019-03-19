package com.server.digital.models;

/*
 * @author lcolmenarez
*/
public class FingerPrint {

    private long id;
    private String content;

    public FingerPrint(final long id, final String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}