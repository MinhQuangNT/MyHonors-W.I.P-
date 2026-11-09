package com.example.yikyakclone_java;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostsApi {
    @GET("api/posts")
    Call<List<Post>> getPosts();

    @POST("api/posts")
    Call<Post> createPost(@Body CreatePostRequest request);
}