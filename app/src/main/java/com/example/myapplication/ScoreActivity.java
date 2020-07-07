package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private Boolean mIsMale ;
    private  int mheight ;
    private  int mweight;
    private  int mage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mIsMale = getIntent().getBooleanExtra("genderExtra", false);
        mheight = getIntent().getIntExtra("heightExtra" , 0) ;
        mweight = Integer.valueOf(getIntent().getStringExtra("weightExtra")) ;
        mage = getIntent().getIntExtra("ageExtra" , 0) ;
        float score = (float)mweight/ ( (float)mheight/100 * (float)mheight / 100 ) ;
        TextView scoreTextView = findViewById(R.id.score) ;
        scoreTextView.setText(String.valueOf(score));

    }
}
