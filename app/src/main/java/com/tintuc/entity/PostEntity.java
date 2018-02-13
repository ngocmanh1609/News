package com.tintuc.entity;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Hp on 2/7/2018.
 */

public class PostEntity implements Serializable {

    private int id;
    private String title;
    private String desc;
    private String thumb;
    private int categoryId;
    private String content;

    public PostEntity() {}

    public PostEntity(JSONObject jsonObject) {
        id = jsonObject.optInt("post_id", 0);
        title = jsonObject.optString("post_title", "");
        desc = jsonObject.optString("post_desc", "");
        thumb = jsonObject.optString("post_thumb", "");
        categoryId = jsonObject.optInt("category_id", 0);
        content = jsonObject.optString("post_content", "");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", thumb='" + thumb + '\'' +
                ", categoryId=" + categoryId +
                ", content='" + content + '\'' +
                '}';
    }
}
