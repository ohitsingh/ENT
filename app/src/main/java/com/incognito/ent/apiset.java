package com.incognito.ent;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface apiset {
    @FormUrlEncoded
    @POST("signup.php")
    Call<signup_response_model> getregister(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile,
            @Field("address") String address

    );
    @FormUrlEncoded
    @POST("login.php")
    Call<login_response_model> getLogin(
//            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
//            @Field("mobile") String mobile,
//            @Field("address") String address

    );

    @FormUrlEncoded
    @POST("feedback.php")
    Call<feedback_response_model> getfeedback(
            @Field("email") String email,
            @Field("name") String name,
            @Field("feedback") String feedback

    );

    @FormUrlEncoded
    @POST("support.php")
    Call<support_response_model> getsupport(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("query") String query

    );

}
