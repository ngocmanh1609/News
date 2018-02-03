package com.tintuc.entity;

/**
 * Created by Hp on 2/3/2018.
 */

public class MenuEntity {

    private int id;
    private String title;
    private boolean selected;

    public MenuEntity() {

    }

    public MenuEntity(int id, String title, boolean selected) {
        this.id = id;
        this.title = title;
        this.selected = selected;
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "MenuEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", selected=" + selected +
                '}';
    }
}
