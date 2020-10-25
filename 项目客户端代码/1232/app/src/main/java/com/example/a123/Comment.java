package com.example.a123;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2018/2/5.
 */

public class  Comment{

    private String name;
    private String time;
    private String content;
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}