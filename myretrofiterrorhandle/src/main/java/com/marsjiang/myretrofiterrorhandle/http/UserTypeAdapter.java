package com.marsjiang.myretrofiterrorhandle.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.marsjiang.myretrofiterrorhandle.model.UserInfo;

import java.lang.reflect.Type;

/**
 * UserTypeAdapter
 * Created by jaycee on 2017/6/23.
 */
public class UserTypeAdapter implements JsonDeserializer<UserInfo> {
    @Override
    public UserInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            Gson gson = new Gson();
            Log.d("returnInfo","json"+json.toString());
            return gson.fromJson(json, UserInfo.class);
        }
        return null;
    }
}
