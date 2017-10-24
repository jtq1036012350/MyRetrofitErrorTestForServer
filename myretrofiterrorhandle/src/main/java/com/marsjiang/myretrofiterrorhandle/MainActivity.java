package com.marsjiang.myretrofiterrorhandle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marsjiang.myretrofiterrorhandle.http.BaseEntity;
import com.marsjiang.myretrofiterrorhandle.http.BaseObserver;
import com.marsjiang.myretrofiterrorhandle.http.RetrofitFactory;
import com.marsjiang.myretrofiterrorhandle.model.UserInfo;

import io.reactivex.Observable;

public class MainActivity extends BaseActivity {
    private EditText etName;
    private EditText etPass;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
//        login("jtq", "123");
    }

    /**
     * 初始化布局
     */
    private void initViews() {
        etName = (EditText) findViewById(R.id.et_name);
        etPass = (EditText) findViewById(R.id.et_pass);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(etName.getText().toString().trim(), etPass.getText().toString().trim());
            }
        });
    }

    private void login(String userId, String password) {
        Observable<BaseEntity<UserInfo>> observable = RetrofitFactory.getInstance().login(userId, password);
        observable.compose(compose(this.<BaseEntity<UserInfo>>bindToLifecycle())).subscribe(new BaseObserver<UserInfo>(this) {
            @Override
            protected void onHandleSuccess(UserInfo userInfo) {
                // 保存用户信息等操作
                Log.d("returnInfo", "成功" + userInfo.getUsername());
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
            }

            @Override
            protected void onHandleError(String msg) {
                super.onHandleError(msg);
                Log.d("returnInfo", "失败"+"----------------------");
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
