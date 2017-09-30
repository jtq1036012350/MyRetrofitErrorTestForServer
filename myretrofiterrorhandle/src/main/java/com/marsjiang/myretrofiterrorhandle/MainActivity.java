package com.marsjiang.myretrofiterrorhandle;

import android.os.Bundle;

import com.marsjiang.myretrofiterrorhandle.http.BaseEntity;
import com.marsjiang.myretrofiterrorhandle.http.BaseObserver;
import com.marsjiang.myretrofiterrorhandle.http.RetrofitFactory;
import com.marsjiang.myretrofiterrorhandle.model.User;

import io.reactivex.Observable;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login("a","");
    }

    private void login(String userId, String password) {
        Observable<BaseEntity<User>> observable = RetrofitFactory.getInstance().login();
        observable.compose(compose(this.<BaseEntity<User>>bindToLifecycle())).subscribe(new BaseObserver<User>(this) {
            @Override
            protected void onHandleSuccess(User user) {
                // 保存用户信息等操作
            }

            @Override
            protected void onHandleError(String msg) {
                super.onHandleError(msg);
            }
        });
    }
}
