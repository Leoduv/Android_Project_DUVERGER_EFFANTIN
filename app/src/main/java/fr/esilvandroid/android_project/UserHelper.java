package fr.esilvandroid.android_project;

public class UserHelper {

    private static String user_id;

    public UserHelper(String user_id){
        UserHelper.user_id = user_id;
    }

    public static String getUserId(){
        return user_id;
    }

    static void setUser_id(String new_id){
        UserHelper.user_id = new_id;
    }
}
