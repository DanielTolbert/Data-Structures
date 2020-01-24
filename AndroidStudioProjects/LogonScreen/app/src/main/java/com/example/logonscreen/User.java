package com.example.logonscreen;

import java.io.Serializable;

public class User implements Serializable {
    private String text;
    private String id;

    public User(String text, String id) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean equals(User otherUser) {
        return this.getId().equals( otherUser.getId() );
    }
}
