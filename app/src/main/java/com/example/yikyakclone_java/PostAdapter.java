package com.example.yikyakclone_java;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()) //inflate a layout from context
                .inflate(R.layout.item_post, parent, false); //passing true is redundant
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.tvAuthorName.setText(post.getAuthorName());
        holder.tvContent.setText(post.getContent());
        holder.tvTimestamp.setText(post.getTimestamp());

        // Show the badge only for professors
        holder.tvProfessorBadge.setVisibility(post.isFromProfessor() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvAuthorName, tvContent, tvTimestamp, tvProfessorBadge;

        PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAuthorName = itemView.findViewById(R.id.tvAuthorName);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
            tvProfessorBadge = itemView.findViewById(R.id.tvProfessorBadge);
        }
    }

}