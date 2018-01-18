package com.project.mihai.aichatproject.gui.register.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.project.mihai.aichatproject.R;
import com.project.mihai.aichatproject.gui.login.view.LoginActivity;
import com.project.mihai.aichatproject.gui.register.presenter.RegisterPresenter;
import com.project.mihai.aichatproject.gui.register.presenter.RegisterPresenterImplementation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    @BindView(R.id.tv_register_submit)
    TextView tvRegisterSubmit;

    @BindView(R.id.et_register_username)
    EditText etRegisterUsername;

    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;

    @BindView(R.id.et_register_repeat_password)
    EditText etRepeatPassword;

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initInstances();
    }

    private void initInstances() {
        presenter = new RegisterPresenterImplementation(this);
    }

    @Override
    public void onSucces() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    @Override
    public void setUsernamePasswordError() {
        etRegisterUsername.setError("Password and username must not be empty!");
        etRegisterPassword.setError("Password and username must not be empty!");
    }

    @Override
    public void setNotMatchingPasswordError() {
        etRegisterPassword.setError("Passwords must be the same!");
        etRepeatPassword.setError("Passwords must be the same!");
    }

    @OnClick(R.id.tv_register_submit)
    public void registerSubmitClickACtion() {
        presenter.connect(etRegisterUsername.getText().toString(),
                etRegisterPassword.getText().toString(),
                etRepeatPassword.getText().toString());
    }
}
