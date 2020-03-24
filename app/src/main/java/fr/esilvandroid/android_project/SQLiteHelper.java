package fr.esilvandroid.android_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    //Database name
    private static final String database_name = "users_offers";
    private static final int database_version = 1;

    //TABLES
    //User table
    private static final String table_users = "users";
    //User columns
    private static final String pk_user_id = "id_user";
    private static final String k_username = "username";
    private static final String k_password = "password";
    private static final String k_email = "email";
    //Offers table
    private static final String table_offers = "offers";
    //Offers columns
    private static final String pk_offer_id = "id_offer";
    private static final String k_company = "company";
    private static final String k_jobtitle = "jobtitle";
    private static final String k_summary = "summary";
    private static final String k_skills = "skills";
    private static final String k_link = "link";
    private static final String k_status = "status";
    private static final String fk_user_id = "id_user";

    //SQL : create users table
    private static final String SQL_table_users = " CREATE TABLE " + table_users
            + " ( "
            + pk_user_id + " INTEGER PRIMARY KEY, "
            + k_username + " TEXT, "
            + k_password + " TEXT, "
            + k_email + " TEXT"
            + " ) ";

    //SQL : create offers table
    private static final String SQL_table_offers = " CREATE TABLE " + table_offers
            + " ( "
            + pk_offer_id + " INTEGER PRIMARY KEY, "
            + k_company + " TEXT, "
            + k_jobtitle + " TEXT, "
            + k_summary + " TEXT,"
            + k_skills + " TEXT,"
            + k_link + " TEXT,"
            + k_status + " TEXT,"
            + fk_user_id + " INTEGER,"
            + "FOREIGN KEY(" + fk_user_id + ") " +
            "REFERENCES " + table_users + "(" + pk_user_id + ")"
            + " ) ";

    //Constructor
    public SQLiteHelper(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create the Tables when called
        sqLiteDatabase.execSQL(SQL_table_users);
        sqLiteDatabase.execSQL(SQL_table_offers);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //drop tables to create new ones if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + table_users);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + table_offers);
    }

    //ADD USERS
    void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Add information in @values
        values.put(k_username, user.username);
        values.put(k_password, user.password);
        values.put(k_email, user.email);

        //insert row
        long row_id = db.insert(table_users, null, values);
    }

    //ADD OFFER
    public void addOffer(Offer offer) {
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Add information in @values
        values.put(k_company, offer.company);
        values.put(k_jobtitle, offer.jobtitle);
        values.put(k_summary, offer.summary);
        values.put(k_skills, offer.skills);
        values.put(k_link, offer.link);
        values.put(k_status, offer.status);
        values.put(fk_user_id, offer.id_user);

        long row_id = db.insert(table_offers, null, values);
    }

    //RETURN LIST OF OFFERS OF THE USER
    public List<OffersModel> getOfferList(String user_id){

        SQLiteDatabase db = this.getReadableDatabase();
        List<OffersModel> list = new ArrayList<>();

        String[] columns = new String[]{pk_offer_id, k_company, k_jobtitle, k_summary, k_skills, k_link, k_status, fk_user_id};
        String whereClause = fk_user_id + " = ?";
        String[] whereArgs = new String[]{user_id};
        //ORDER BY ?
        Cursor cursor = db.query(table_offers,columns,whereClause,whereArgs,null,null,null);
        try {
            while (cursor.moveToNext()) {
                OffersModel temp = new OffersModel(new Offer(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)));
                list.add(temp);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    //CREATE EXAMPLE OFFER FOR EACH NEW USER
    public boolean exampleOffer(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{pk_offer_id};
        String whereClause = fk_user_id + " = ?";
        String[] whereArgs = new String[]{user.id_user};
        Cursor cursor = db.query(table_offers, columns, whereClause, whereArgs, null, null, null);

        //if new user, he doesn't have any saved applications yet
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            return false;
        }
        else {
            //create an example application
            ContentValues example = new ContentValues();
            //add values
            example.put(k_company, "The Example Company");
            example.put(k_jobtitle, "App Tester");
            example.put(k_summary, "You will test apps all day");
            example.put(k_skills, "Java, Android, Dance");
            example.put(k_link, "https://www.google.fr/");
            example.put(k_status, "Interview");
            example.put(fk_user_id, user.id_user);
            //exe insert
            long row_id = db.insert(table_offers, null, example);

            return true;}
    }

    //AUTHENTICATE USER
    User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{pk_user_id, k_username, k_password, k_email};
        String whereClause = k_email + " = ?";
        String[] whereArgs = new String[]{user.email};
        Cursor cursor = db.query(table_users, columns, whereClause, whereArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    //EMAIL ALREADY IN DATABASE
    boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{pk_user_id, k_username, k_password, k_email};
        String whereClause = k_email + " = ?";
        String[] whereArgs = new String[]{email};
        Cursor cursor = db.query(table_users, columns, whereClause, whereArgs, null, null, null);

        //if cursor has value then in user database there is user associated with this given email so return true
        return cursor != null && cursor.moveToFirst() && cursor.getCount() > 0;

        //if email does not exist return false
    }
}
