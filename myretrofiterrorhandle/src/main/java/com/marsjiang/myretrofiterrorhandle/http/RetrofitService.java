package com.marsjiang.myretrofiterrorhandle.http;


import com.marsjiang.myretrofiterrorhandle.model.UserInfo;
import com.marsjiang.myretrofiterrorhandle.model.VideoUrl;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * For Retrofit
 * Created by jaycee on 2017/6/23.
 */
public interface RetrofitService {

    //    @FormUrlEncoded
    @GET("UserLoginServlet")
    Observable<BaseEntity<UserInfo>> login(@Query("username") String username, @Query("password") String password);

    @GET("video/getUrl")
    Observable<BaseEntity<VideoUrl>> getVideoUrl(
            @Query("id") long id
    );

    @FormUrlEncoded
    @POST("user/addVideo")
    Observable<BaseEntity<Boolean>> addVideo(
            @FieldMap Map<String, Object> map
    );
}
