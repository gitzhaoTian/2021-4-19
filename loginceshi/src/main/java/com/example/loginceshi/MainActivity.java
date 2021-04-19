package com.example.loginceshi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginceshi.bean.Bean;
import com.example.loginceshi.mvp.login.ILoginPresenter;
import com.example.loginceshi.mvp.login.ILoginView;
import com.example.loginceshi.ui.BaseMvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<ILoginView<Bean>, ILoginPresenter> implements ILoginView<Bean> {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    public void onSuccess(Bean bean) {
        if (bean.getErrorCode()==0){
            startActivity(new Intent(this,NewsActivity.class));
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected ILoginPresenter createPresenter() {
        return new ILoginPresenter();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                presenter.getLogin(etUsername.getText().toString(),etPassword.getText().toString());
                break;
            case R.id.btn_register:
                startActivityForResult(new Intent(this,RegisterActivity.class),100);
                break;
        }
    }
}