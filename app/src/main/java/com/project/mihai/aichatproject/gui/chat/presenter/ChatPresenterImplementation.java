package com.project.mihai.aichatproject.gui.chat.presenter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.project.mihai.aichatproject.gui.chat.view.ChatView;
import com.project.mihai.aichatproject.model.ChatMessageModel;
import com.project.mihai.aichatproject.singleton.RequestSingleton;
import com.project.mihai.aichatproject.utils.ChatUtils;

import java.net.HttpURLConnection;
import java.util.HashMap;
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
    public void onPostMessage(String message, List<ChatMessageModel> data, int type) {
        Random rand = new Random();
        if (message.length() > 0) {
            data.add(new ChatMessageModel(message, type));
            if (view != null) {
                view.onAdapterViewUpdate();
                view.onClearChatMessage();
            }
        }
    }

    @Override
    public void onRequestMessage(final String msg) {

        RequestSingleton.getInstance().doMessageRequest(msg, new RequestSingleton.IHelperRequest() {
            @Override
            public void onSucceeded(boolean isSuccess, int code, String message, @Nullable String result) {
                if (isSuccess) {
                    if (code == HttpURLConnection.HTTP_OK) {
                        Log.d("APIAPI", result);
                        if (view != null) {
                            String res = result.replaceAll("[^A-Za-z0-9ăâîșț., ]", "") + ".";
                            if (res.length() > 0) {
                                view.onPostMessageResponse(res);
                            } else {
                                view.onPostMessageResponse(ChatUtils.getTempWords());
                            }
                        }
                    } else {
                        Log.d("APIAPI", "code not ok " + code);
                        if (view != null) {
                            view.onPostMessageResponse(ChatUtils.getTempWords());
                        }
                    }
                } else {
                    Log.d("APIAPI", "request not ok " + message);
                    if (view != null) {
                        view.onPostMessageResponse(ChatUtils.getTempWords());

                    }
                }
            }
        });


    }
}
