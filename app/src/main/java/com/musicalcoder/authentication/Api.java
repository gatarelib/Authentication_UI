package com.musicalcoder.authentication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("auth/register")
    Call<ResponseBody> registerUser(

        @Field("first_name") String fName,
        @Field("last_name") String lName,
        @Field("username") String uName,
        @Field("email") String email,
        @Field("password1") String pass1,
        @Field("password2") String pass2

    );
}
