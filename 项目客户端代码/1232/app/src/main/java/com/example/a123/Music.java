package com.example.a123;


import com.like.LikeButton;

import java.io.Serializable;

public class Music {
      private String name;
      private int imageId;

public Music(String name,int id)
{
    this.name=name;
    this.imageId=id;
}


    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}


