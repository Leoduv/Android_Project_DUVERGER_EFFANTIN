package fr.esilvandroid.android_project;

import android.widget.ImageView;

import androidx.appcompat.view.menu.MenuView;

public class Item {

    private String ItemTitle;
    private int Itemview;

    public Item(String itemTitle, int imgView) {
        ItemTitle = itemTitle;
        Itemview = imgView;
    }

    public String getItemTitle() {
        return ItemTitle;
    }

    public void setItemTitle(String itemTitle) {
        ItemTitle = itemTitle;
    }

    public int getItemview() {
        return Itemview;
    }

    public void setItemview(int itemview) {
        Itemview = itemview;
    }
}

