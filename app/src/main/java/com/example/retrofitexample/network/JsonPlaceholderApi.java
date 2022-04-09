package com.example.retrofitexample.network;

import com.example.retrofitexample.network.pojo.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceholderApi {
    @GET("/posts/{id}")
    public Call<Post> getPostById(@Path("id") int id);
    
    @GET("/posts")
    public Call<List<Post>> getAllPosts();

    @GET("/posts")
    public Call<List<Post>> getPostByUserId(@Query("userId") int userId);

    @POST("/posts")
    public Call<Post> sendPost(@Body Post post);

}
