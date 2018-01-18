package com.project.mihai.aichatproject.gui.register.presenter;

import com.project.mihai.aichatproject.gui.register.view.RegisterView;

/**
 * Created by adasc on 1/18/2018.
 */

public class RegisterPresenterImplementation implements RegisterPresenter {
    RegisterView view;

    public RegisterPresenterImplementation(RegisterView view) {
        this.view = view;
    }

    @Override
    public void connect(String username, String password, String repeatPassword) {
        if (!(username.length() > 0) && !(password.length() > 0)) {
            if (view != null) {
                view.setUsernamePasswordError();
                return;
            }
        }

        if (!repeatPassword.equals(password)) {
            if (view != null) {
                view.setNotMatchingPasswordError();
                return;
            }

        }
        if (view != null) {
            view.onSucces();
        }

    }
}
