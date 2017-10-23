package com.marsjiang.myretrofiterrorhandle.myretrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.marsjiang.myretrofiterrorhandle.exception.MyException;
import com.marsjiang.myretrofiterrorhandle.http.BaseEntity;
import com.marsjiang.myretrofiterrorhandle.model.UserInfo;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Jiang on 2017-09-28.
 */

final class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    private static final int FAILURE = 0;       // 失败 提示失败msg
    private static final int SUCCESS = 1;       // 成功
    private static final int TOKEN_EXPIRE = 2;  // token过期
    private static final int SERVER_EXCEPTION = 3;  // 服务器异常

    MyGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        String json = value.string();
        try {
            verify(json);
//            BaseEntity<UserInfo> aa = gson.fromJson(json, BaseEntity.class);
//            Log.d("returnInfo", "成功" + aa.getData());
//            return adapter.read(jsonReader);
            return adapter.read(gson.newJsonReader(new StringReader(json)));
        } finally {
            value.close();
        }
    }

    private void verify(String json) {
        BaseEntity<UserInfo> result = gson.fromJson(json, BaseEntity.class);
        Log.d("returnInfo", json);
        if (result.getCode() != SUCCESS) {
//            int a = result.getCode();
            switch (result.getCode()) {
                case SERVER_EXCEPTION:
                    throw new MyException(result.getMsg());
                case TOKEN_EXPIRE:
                    throw new MyException(result.getMsg());
                default:
//                    throw new MyException("不清楚什么原因！");
            }
        }
    }

}
