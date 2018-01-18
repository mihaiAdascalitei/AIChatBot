package com.project.mihai.aichatproject.gui.chat.view;

import com.project.mihai.aichatproject.model.ChatMessageModel;

import java.util.List;

/**
 * Created by adasc on 1/8/2018.
 */

public interface ChatView {
    void onAdapterViewUpdate();

    void onClearChatMessage();

    void onPostMessageResponse(String message);

}
