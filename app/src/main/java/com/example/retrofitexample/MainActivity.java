package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.retrofitexample.network.NetworkService;
import com.example.retrofitexample.network.pojo.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView txtLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txtLabel = findViewById(R.id.txtLabel);
        
        NetworkService
                .getInstance()
                .getRetrofitApi()
                .getPostById(1)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Post post = response.body();

                        txtLabel.append(Integer.toString(post.getId()));
                        txtLabel.append(post.getTitle());
                        txtLabel.append(post.getBody());
                        txtLabel.append(Integer.toString(post.getUserId()));
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        txtLabel.append("Error occured while getting request");
                        t.printStackTrace();
                    }
                });
    }
}