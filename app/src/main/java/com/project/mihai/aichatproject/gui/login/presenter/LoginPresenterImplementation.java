package com.project.mihai.aichatproject.gui.login.presenter;

import com.project.mihai.aichatproject.gui.login.view.LoginView;
import com.project.mihai.aichatproject.utils.ChatUtils;

/**
 * Created by adasc on 1/7/2018.
 */

public class LoginPresenterImplementation implements LoginPresenter {
    LoginView view;

    public LoginPresenterImplementation(LoginView view) {
        this.view = view;
    }

    @Override
    public void login(String username, String password) {
        if (ChatUtils.isNull(username)) {
            if (view != null) {
                view.setUsernameError();
                return;
            }
        }
        if (ChatUtils.isNull(password)) {
            if (view != null) {
                view.setPasswordError();
                return;
            }
        }

        if (view != null) {
            view.onSuccess();
        }
    }
}
