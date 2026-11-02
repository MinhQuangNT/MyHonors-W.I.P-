package com.example.yikyakclone_java;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final PostsApi POSTS_API =
            new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PostsApi.class);

    public static PostsApi getPostsApi() {
        return POSTS_API;
    }
}