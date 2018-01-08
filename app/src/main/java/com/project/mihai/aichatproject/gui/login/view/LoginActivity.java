package com.project.mihai.aichatproject.gui.login.view;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.mihai.aichatproject.R;
import com.project.mihai.aichatproject.gui.chat.view.ChatActivity;
import com.project.mihai.aichatproject.gui.login.presenter.LoginPresenter;
import com.project.mihai.aichatproject.gui.login.presenter.LoginPresenterImplementation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.iv_button_add)
    ImageView ivAddButton;

    @BindView(R.id.tv_login_text)
    TextView tvLogin;

    @BindView(R.id.et_login_username)
    EditText etUsername;

    @BindView(R.id.et_login_password)
    EditText etPassword;

    private LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        adaptViews();
        initInstances();
    }

    private void initInstances() {
        presenter = new LoginPresenterImplementation(this);
    }

    private void adaptViews() {
        ivAddButton.setColorFilter(ContextCompat.getColor(LoginActivity.this, R.color.blue_gray));
    }

    @OnClick(R.id.tv_login_text)
    public void loginTextClickAction() {
        presenter.login(etUsername.getText().toString(), etPassword.getText().toString());
    }


    @Override
    public void setUsernameError() {
        etUsername.setError(getString(R.string.login_username_error));
    }

    @Override
    public void setPasswordError() {
        etPassword.setError(getString(R.string.login_password_error));
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(LoginActivity.this, ChatActivity.class));
    }
}
