package com.project.mihai.aichatproject.gui.chat.presenter;

import com.project.mihai.aichatproject.gui.chat.view.ChatAdapterView;

/**
 * Created by adasc on 1/8/2018.
 */

public class ChatAdapterClickImplementation implements ChatAdapterClickPresenter {
    private ChatAdapterView view;

    public ChatAdapterClickImplementation(ChatAdapterView view) {
        this.view = view;
    }

    @Override
    public void onMicrophoneClick(String message, int type) {
        if (type == 2) {
            if (view != null) {
                if (view.isSpeaking()) {
                    view.muteMicrophone();
                } else {
                    view.speak(message);
                }
            }
        }

    }

}
