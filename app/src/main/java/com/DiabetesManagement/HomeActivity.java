package com.DiabetesManagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button btndiet,btnmedicstion,
            btnexercise,btnrss,
            btndocter,btncharts,
            btnexit,btnseting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btndiet=findViewById(R.id.btndiet);
        btnmedicstion=findViewById(R.id.btnmedicstion);
        btnexercise=findViewById(R.id.btnexercise);
        btnrss=findViewById(R.id.btnrss);
        btndocter=findViewById(R.id.btndocter);
        btncharts=findViewById(R.id.btncharts);

        btnexit=findViewById(R.id.btnexit);
        btnseting=findViewById(R.id.btnsetting);


        btnmedicstion.setOnClickListener(this);

        btncharts.setOnClickListener(this);

        btnexit.setOnClickListener(this);




    }

    public void onClick(View v)
    {
        switch(v.getId()) {
            case R.id.btnmedicstion:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

                break;
            case R.id.btncharts:
                startActivity(new Intent(getApplicationContext(),ViewGraph.class));

                break;

            case R.id.btnexit:
                finish();

                break;

            default:
                break;
        }
    }



}