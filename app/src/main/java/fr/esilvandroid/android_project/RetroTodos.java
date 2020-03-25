package fr.esilvandroid.android_project;

import com.google.gson.annotations.SerializedName;

public class RetroTodos {

    //Give the field a custom name//
    @SerializedName("title")
    private String title;

    public RetroTodos(String title) {
        this.title = title;

    }

    //Retrieve the data using setter/getter methods//
    public String getTodo() {
        return title;
    }

    public void setTodo(String title) {
        this.title = title;
    }
}
