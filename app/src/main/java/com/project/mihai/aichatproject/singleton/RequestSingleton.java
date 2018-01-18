package com.project.mihai.aichatproject.singleton;

import android.support.annotation.Nullable;
import android.util.Log;

import com.project.mihai.aichatproject.model.HttpMethods;
import com.project.mihai.aichatproject.request.ServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by adasc on 1/18/2018.
 */

public class RequestSingleton {

    private final String MESSAGE_REQUEST_URL = "http://192.168.43.128:5000/message";

    public interface IHelperRequest {
        public void onSucceeded(boolean isSuccess, int code, String message, @Nullable String result);
    }

    private static final RequestSingleton ourInstance = new RequestSingleton();

    public static RequestSingleton getInstance() {
        return ourInstance;
    }

    private RequestSingleton() {
    }

    public void doMessageRequest(String message, final IHelperRequest helperRequest) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("message", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ServerRequest serverRequest = new ServerRequest(HttpMethods.POST, hashMap, MESSAGE_REQUEST_URL, new ServerRequest.IRequestHandler() {
            @Override
            public void onSucceeded(boolean isSuccess, int code, String message, @Nullable String result) {
                if (helperRequest != null) {
                    helperRequest.onSucceeded(isSuccess, code, message, result);
                }
            }
        }, jsonObject.toString());

        serverRequest.makeRequest();
    }
}
