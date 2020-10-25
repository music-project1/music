package com.example.a123.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a123.Comment;
import com.example.a123.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context mContext;
    private List<Comment> comments;
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView commentContent;
        TextView commentUsername;
        ImageView avatar;

        public ViewHolder(View view) {
            super(view);
            commentContent = (TextView) view.findViewById(R.id.comment_content);
            commentUsername = (TextView) view.findViewById(R.id.comment_username);
            avatar = (ImageView) view.findViewById(R.id.comment_avatar);
        }
    }
        public CommentAdapter(List<Comment> commentList )
        {
            comments=commentList;
        }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null)
        {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_item,parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment= comments.get(position);
        holder.commentUsername.setText(comment.getName());
        Glide.with(mContext).load(comment.getAvatar()).into(holder.avatar);
    }
    @Override
    public int getItemCount() {
        return comments.size();
    }

}
