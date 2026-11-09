package com.example.yikyakclone_java;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private PostAdapter adapter;
    private List<Post> posts;
    private RecyclerView recyclerView;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String content = data.getStringExtra("postContent");
                    if (content != null && !content.trim().isEmpty()) {
                        submitPost(content.trim());
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posts = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerViewPosts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PostAdapter(posts);
        recyclerView.setAdapter(adapter);

        loadPosts();

        FloatingActionButton fab = findViewById(R.id.fabNewPost);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewPostActivity.class);
            activityResultLauncher.launch(intent);
        });
    }

    private void loadPosts() {
        ApiClient.getPostsApi().getPosts().enqueue(new retrofit2.Callback<List<Post>>() {
            @Override
            public void onResponse(
                    retrofit2.Call<List<Post>> call,
                    retrofit2.Response<List<Post>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    posts.clear();
                    posts.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            "Server returned error " + response.code(),
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(
                    retrofit2.Call<List<Post>> call,
                    Throwable error) {
                Toast.makeText(
                        MainActivity.this,
                        "Couldn't connect to the server",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    private void submitPost(String content) {
        ApiClient.getPostsApi()
                .createPost(new CreatePostRequest(content))
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            posts.add(0, response.body());
                            adapter.notifyItemInserted(0);
                            recyclerView.scrollToPosition(0);
                        } else {
                            Toast.makeText(
                                    MainActivity.this,
                                    "Failed to send post. Server returned error " + response.code(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable error) {
                        Toast.makeText(
                                MainActivity.this,
                                "Couldn't submit post. Check that the server is running.",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });
    }
}
