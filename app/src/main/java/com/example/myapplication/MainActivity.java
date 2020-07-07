package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private int mHeight = 160;
    private int mAge = 19;


    private boolean mIsMale = false;

    private TextView mAgeTextView;
    private TextView mHeightTextView;
    private EditText mWeightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHeightTextView = findViewById(R.id.height);
        mHeightTextView.setText(String.format(getString(R.string.height_unit), mHeight));

        mAgeTextView = findViewById(R.id.age);
        mAgeTextView.setText(String.valueOf(mAge));

        mWeightEditText = findViewById(R.id.weight);

        findViewById(R.id.btnCloseMain).setOnClickListener(this);
        findViewById(R.id.plusAge).setOnClickListener(this);
        findViewById(R.id.minusAge).setOnClickListener(this);
        findViewById(R.id.plusHeight).setOnClickListener(this);
        findViewById(R.id.minusHeight).setOnClickListener(this);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.check(mIsMale ? R.id.maleRadio : R.id.femaleRadio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mIsMale = checkedId == R.id.maleRadio;
            }
        });

    }

    @Override

    protected void onStart (){
        Log.d("MainActivityTAG","onStart");
        super.onStart();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCloseMain:
                validate();
                break;
            case R.id.plusAge:
                addAge();
                break;
            case R.id.minusAge:
                minusAge();
                break;
            case R.id.plusHeight:
                addHeight();
                break;
            case R.id.minusHeight:
                minusHeight();
                break;
        }
    }


    private void validate() {
        if (TextUtils.isEmpty(mWeightEditText.getText().toString())) {
            Toast.makeText (this , "Mettez votre poid", Toast.LENGTH_SHORT).show() ;
            return ;
        }

        String string = String.format("Gender %s\nPoids %s\nTaille %s\nAge %s",
                mIsMale ? "Male" : "Female",
                mWeightEditText.getText().toString(),
                mHeightTextView.getText().toString(),
                mAgeTextView.getText().toString());

        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,ScoreActivity.class);

        intent.putExtra("genderExtra", mIsMale );
        intent.putExtra("heightExtra", mHeight);
        intent.putExtra("weightExtra", mWeightEditText.getText().toString());
        intent.putExtra("ageExtra", mAge);

        startActivity(intent);
    }

    private void minusHeight() {
        if (mHeight == 0) return;
        mHeight--;
        mHeightTextView.setText(String.format(getString(R.string.height_unit), mHeight));
    }

    private void minusAge() {
        mAge--;
        if (mAge < 0) {
            mAge = 0;
        }
        mAgeTextView.setText(String.valueOf(mAge));
    }

    private void addHeight() {
        mHeight++;
        mHeightTextView.setText(String.format(getString(R.string.height_unit), mHeight));
    }

    private void addAge() {
        mAge++;
        mAgeTextView.setText(String.valueOf(mAge));
    }
}
