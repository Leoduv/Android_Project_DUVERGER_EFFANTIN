package fr.esilvandroid.android_project;

import java.io.Serializable;

public class ConseilsModel implements Serializable {

    private String title;
    private String desc;

    public ConseilsModel(String title, String desc) {
        this.title = title;
        this.desc = desc;
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
}
