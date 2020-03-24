package fr.esilvandroid.android_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddOfferActivity extends AppCompatActivity {

    private SQLiteHelper sqliteHelper;
    private String user_id;

    EditText company_et;
    EditText jobtitle_et;
    EditText summary_et;
    EditText skills_et;
    EditText link_et;
    RadioGroup status_rad;
    RadioButton selected_status;
    Button save_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addoffer);

        initViews();

        sqliteHelper = new SQLiteHelper(this);
        user_id = UserHelper.getUserId();

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (minimumInformation()) {
                    String company = company_et.getText().toString();
                    String jobtitle = jobtitle_et.getText().toString();
                    String summary = summary_et.getText().toString();
                    String skills = skills_et.getText().toString();
                    String link = link_et.getText().toString();

                    //get the selected status
                    int choice = status_rad.getCheckedRadioButtonId();
                    selected_status = findViewById(choice);
                    String status = selected_status.getText().toString();

                    //create new saved offer
                    Offer new_offer = new Offer(null, company, jobtitle, summary, skills, link, status, user_id);

                    //add it to the database
                    sqliteHelper.addOffer(new_offer);

                    Toast.makeText(getApplicationContext(), "New application added !", Toast.LENGTH_LONG).show();
                    //pas sur du finish
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Fill in Company and Job title", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    //this method is used to connect XML views to its Objects
    private void initViews() {
        company_et = findViewById(R.id.company_text);
        jobtitle_et = findViewById(R.id.jobtitle_text);
        summary_et = findViewById(R.id.summary_text);
        skills_et = findViewById(R.id.skills_text);
        link_et = findViewById(R.id.link_text);
        status_rad = findViewById(R.id.radio_status);
        save_button = findViewById(R.id.save_button);
    }

    private boolean minimumInformation() {
        if (company_et.getText().toString() != null && jobtitle_et.getText().toString() != null) {
            return true;
        } else {
            return false;
        }
    }

}
