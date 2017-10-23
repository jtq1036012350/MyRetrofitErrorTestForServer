package com.marsjiang.myretrofiterrorhandle;

import android.os.Bundle;
import android.util.Log;

import com.marsjiang.myretrofiterrorhandle.http.BaseEntity;
import com.marsjiang.myretrofiterrorhandle.http.BaseObserver;
import com.marsjiang.myretrofiterrorhandle.http.RetrofitFactory;
import com.marsjiang.myretrofiterrorhandle.model.UserInfo;

import io.reactivex.Observable;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login("jtq", "123");
    }

    private void login(String userId, String password) {
        Observable<BaseEntity<UserInfo>> observable = RetrofitFactory.getInstance().login(userId, password);
        observable.compose(compose(this.<BaseEntity<UserInfo>>bindToLifecycle())).subscribe(new BaseObserver<UserInfo>(this) {
            @Override
            protected void onHandleSuccess(UserInfo userInfo) {
                // 保存用户信息等操作
                Log.d("returnInfo", "成功" + userInfo.getUsername());
            }

            @Override
            protected void onHandleError(String msg) {
                super.onHandleError(msg);
                Log.d("returnInfo", "失败");
            }
        });
    }
}
