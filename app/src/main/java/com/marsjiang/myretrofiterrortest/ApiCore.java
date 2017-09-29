package com.marsjiang.myretrofiterrortest;

import com.marsjiang.myretrofiterrortest.bean.UserReturnBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Jiang on 2017-09-28.
 */

public interface ApiCore {
    //用户信息
    @GET("UserLoginServlet")
    Call<Object> getLoginInfo(@Query("username") String username, @Query("password") String password);
    @FormUrlEncoded
    @POST("MyPostServlet")
    Call<UserReturnBean> postLoginInfo(@Field("postTestt") String  userReturnBean);
}

