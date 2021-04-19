package com.example.loginlianxi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginlianxi.bean.LoginBean;
import com.example.loginlianxi.mvp.login.ILoginPresenter;
import com.example.loginlianxi.mvp.login.ILoginView;
import com.example.loginlianxi.ui.BaseMvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<ILoginPresenter, ILoginView> implements ILoginView {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    public void onSuccess(Object o) {
        LoginBean loginBean = (LoginBean) o;
        if (loginBean.getErrorCode()==0){
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
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
    @OnClick(R.id.btn_login)
    public void onClick() {
        presenter.getLogin(etUsername.getText().toString(),etPassword.getText().toString());
    }
}