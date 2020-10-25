package com.example.a123.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a123.Music;
import com.example.a123.R;
import com.example.a123.listener.ItemInnerClickListener;
import com.example.a123.listener.ItemClickListener;
import com.like.IconType;
import com.like.LikeButton;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>{
    private Context mContext;
    private List<Music> mMusicList;
    private ItemClickListener itemClickListener;
    private ItemInnerClickListener itemInnerClickListener;

     static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView musicImage;
        TextView musicName;
        LikeButton likeButton1;
        LikeButton likeButton2;
        LikeButton likeButton3;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            musicImage = (ImageView) view.findViewById(R.id.music_image);
            musicName = (TextView) view.findViewById(R.id.music_name);
            likeButton1 = (LikeButton) view.findViewById(R.id.thumb);
            likeButton2 = (LikeButton) view.findViewById(R.id.star);
            likeButton3=(LikeButton)view.findViewById(R.id.heart);
        }
    }
    public MusicAdapter(List<Music> musicList) {
        mMusicList = musicList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null)
        {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.music_item,parent, false);
        return new ViewHolder(view);
    }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
        Music music = mMusicList.get(position);
        holder.musicName.setText(music.getName());
        View itemView =holder.itemView;
                if (itemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition();
                        itemClickListener.itemClick(holder.itemView,position);
                    }
                });
            }
            holder.likeButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    itemInnerClickListener.itemClick(position);
                }
            });
        Glide.with(mContext).load(music.getImageId()).into(holder.musicImage);
    }

    public void setOnItemClickListener(ItemClickListener ItemClickListener) {
       itemClickListener = ItemClickListener;
    }

    public void setOnItemInnerClickListener(ItemInnerClickListener itemInnerClickListener) {
        this.itemInnerClickListener =itemInnerClickListener;
    }

    @Override
    public int getItemCount() {
        return mMusicList.size();
    }
}