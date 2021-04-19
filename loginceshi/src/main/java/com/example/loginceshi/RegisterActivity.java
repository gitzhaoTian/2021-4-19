package com.example.loginceshi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginceshi.bean.Bean;
import com.example.loginceshi.mvp.register.ILoginPresenter;
import com.example.loginceshi.mvp.register.ILoginView;
import com.example.loginceshi.ui.BaseMvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseMvpActivity<ILoginView<Bean>, ILoginPresenter> implements ILoginView<Bean> {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_repassword)
    EditText etRepassword;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    public void onSuccess(Bean bean) {
        if (bean.getErrorCode()==0){
            Intent intent = new Intent();
            intent.putExtra("username",etUsername.getText().toString());
            intent.putExtra("password",etPassword.getText().toString());
            setResult(200,intent);
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
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
        return R.layout.activity_register;
    }
    @OnClick(R.id.btn_register)
    public void onClick() {
        presenter.getLogin(etUsername.getText().toString()
        ,etPassword.getText().toString(),etRepassword.getText().toString());
    }
}