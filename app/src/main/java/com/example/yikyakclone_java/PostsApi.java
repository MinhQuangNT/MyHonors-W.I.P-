package com.example.yikyakclone_java;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsApi {
    @GET("api/posts")
    Call<List<Post>> getPosts();
}