package fr.esilvandroid.android_project;

import android.widget.ImageView;

import androidx.appcompat.view.menu.MenuView;

public class Item {

    private String ItemTitle;
    private String Site;
    private int Itemview;

    public Item(String itemTitle, int imgView, String site) {
        ItemTitle = itemTitle;
        Site = site;
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

    public String getSite() {
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }
}

