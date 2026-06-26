package com.example.yikyakclone_java;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class NewPostActivity extends AppCompatActivity {

    private static final int MAX_CHARS = 256;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        EditText etContent = findViewById(R.id.etPostContent);
        TextView tvCharCount = findViewById(R.id.tvCharCount);
        ImageButton btnBack = findViewById(R.id.btnBack);
        ImageButton btnSend = findViewById(R.id.btnSend);

        // Back button closes this screen
        btnBack.setOnClickListener(v -> finish());

        // Live character counter
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCharCount.setText(s.length() + " / 256 chars");
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Send button
        btnSend.setOnClickListener(v -> {
            String content = etContent.getText().toString().trim();
            if (content.isEmpty()) {
                Toast.makeText(this, "Post can't be empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Pass the post content back to MainActivity
            Intent result = new Intent();
            result.putExtra("postContent", content);
            setResult(RESULT_OK, result);
            finish(); // go back to feed
        });
    }
}