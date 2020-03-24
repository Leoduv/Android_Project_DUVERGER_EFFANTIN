package fr.esilvandroid.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SelectedConseilActivity extends AppCompatActivity {


    TextView tvTitle;
    TextView tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_conseil);

        tvTitle = findViewById(R.id.selectedTitleConseil);
        tvDesc = findViewById(R.id.selectedDescConseil);

        Intent intent = getIntent();

        if (intent.getExtras() != null){
            ConseilsModel conseilsModel = (ConseilsModel) intent.getSerializableExtra("data");

            tvTitle.setText(conseilsModel.getTitle());
            tvDesc.setText(conseilsModel.getDesc());
        }
    }
}
