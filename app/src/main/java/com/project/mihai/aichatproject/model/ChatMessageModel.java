package com.project.mihai.aichatproject.model;

/**
 * Created by adasc on 12/13/2017.
 */

public class ChatMessageModel {
    private String message;
    private int type;

    public ChatMessageModel(String message, int type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
