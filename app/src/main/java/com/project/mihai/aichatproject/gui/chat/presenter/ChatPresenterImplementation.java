package com.project.mihai.aichatproject.gui.chat.presenter;

import com.project.mihai.aichatproject.gui.chat.view.ChatView;
import com.project.mihai.aichatproject.model.ChatMessageModel;

import java.util.List;
import java.util.Random;

/**
 * Created by adasc on 1/8/2018.
 */

public class ChatPresenterImplementation implements ChatPresenter {

    ChatView view;

    public ChatPresenterImplementation(ChatView view) {
        this.view = view;
    }

    @Override
    public void onPostMessage(String message, List<ChatMessageModel> data) {
        Random rand = new Random();
        if (message.length() > 0) {
            data.add(new ChatMessageModel(message, rand.nextInt(2) + 1));
            if (view != null) {
                view.onAdapterViewUpdate();
                view.onClearChatMessage();
            }
        }
    }
}
