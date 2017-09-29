package com.marsjiang.myretrofiterrortest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.marsjiang.myretrofiterrortest.bean.UserReturnBean;
import com.marsjiang.myretrofiterrortest.myretrofit.MyGsonConverterFactory;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private String ipPortString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        http://jiang-pc:8080/MyLoginTest/servlet/UserLoginServlet
        ipPortString = "192.168.10.19:8080";
        String url = "http://" + ipPortString + "/MyLoginTest/servlet/";
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MyGsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();

//        onGet();
        onPost();
    }

    private void onPost() {
        ApiCore userBiz = retrofit.create(ApiCore.class);
        UserReturnBean userReturnBean = new UserReturnBean();
        userReturnBean.setUsername("hhha");
        userReturnBean.setUserpass("ccewihvuoera");
        Map<String,String> a = new HashMap<>();
        a.put("a","m");
        a.put("a1","m1");
        a.put("a2","m2");
        Gson gson = new Gson();
        Call<UserReturnBean> call = userBiz.postLoginInfo(gson.toJson(userReturnBean));
        call.enqueue(new Callback<UserReturnBean>() {
            @Override
            public void onResponse(Call<UserReturnBean> call, Response<UserReturnBean> response) {
                if (response.isSuccessful()) {
//                    if (response.body().getData() == null) {
//
//                    }
                    Log.e("infoooo", "normalGet:" + response.body() + "");
                }
            }

            @Override
            public void onFailure(Call<UserReturnBean> call, Throwable t) {
                Log.e("infoooo", "normalGet:" + t.toString() + "");
            }
        });
    }


    private void onGet() {
        ApiCore userBiz = retrofit.create(ApiCore.class);
        Call<Object> call = userBiz.getLoginInfo("aa","bb");
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
//                    if (response.body().getData() == null) {
//
//                    }
                    Log.e("infoooo", "normalGet:" + response.body() + "");
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("infoooo", "normalGet:" + t.toString() + "");
            }
        });
    }

    /**
     * 获取okhttp拦截器
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("zcb", "OkHttp====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }
}
