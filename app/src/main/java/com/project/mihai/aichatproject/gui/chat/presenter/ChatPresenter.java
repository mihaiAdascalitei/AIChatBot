package com.project.mihai.aichatproject.gui.chat.presenter;

import com.project.mihai.aichatproject.model.ChatMessageModel;

import java.util.List;

/**
 * Created by adasc on 1/8/2018.
 */

public interface ChatPresenter {

    void onPostMessage(String message, List<ChatMessageModel> data, int type);

    void onRequestMessage(String message);

}
