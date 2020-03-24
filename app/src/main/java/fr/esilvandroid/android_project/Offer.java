package fr.esilvandroid.android_project;

public class Offer {
    String id_offer;
    String company;
    String jobtitle;
    String summary;
    String skills;
    String link;
    String status;
    String id_user;

    public Offer(String id, String company, String jobtitle, String summary,
                 String skills, String link, String status, String user_id){
        this.id_offer = id;
        this.company = company;
        this.jobtitle = jobtitle;
        this.summary = summary;
        this.skills = skills;
        this.link = link;
        this.status = status;
        this.id_user = user_id;
    }
}
