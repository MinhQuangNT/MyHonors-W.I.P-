package com.example.yikyakclone_java;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
                    String timestamp = new SimpleDateFormat("MMM d, h:mm a", Locale.getDefault())
                            .format(new Date());

                    Post newPost = new Post("Minh", content, timestamp, false);
                    posts.add(0, newPost);
                    adapter.notifyItemInserted(0);
                    recyclerView.scrollToPosition(0);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posts = new ArrayList<>(List.of(
                new Post("Griffin Nye", "I love java!", "Today, 9:00 AM", true),
                new Post("Quang", "There's an impostor among us.", "Today, 10:15 AM", false),
                new Post("Alex", "Orang.", "Today, 11:30 AM", false)
        ));

        recyclerView = findViewById(R.id.recyclerViewPosts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PostAdapter(posts);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabNewPost);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewPostActivity.class);
            activityResultLauncher.launch(intent);
        });
    }
}