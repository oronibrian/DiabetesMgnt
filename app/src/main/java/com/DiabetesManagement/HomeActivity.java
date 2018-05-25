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
<<<<<<< HEAD
        btnexercise.setOnClickListener(this);
        btnrss.setOnClickListener(this);
=======
>>>>>>> 7f3d522fd568b7da9ed4d38fe5e65038ff9c498e




    }

    public void onClick(View v)
    {
        switch(v.getId()) {
            case R.id.btnmedicstion:
<<<<<<< HEAD
                finish();
=======
>>>>>>> 7f3d522fd568b7da9ed4d38fe5e65038ff9c498e
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

                break;
            case R.id.btncharts:
<<<<<<< HEAD
                finish();
=======
>>>>>>> 7f3d522fd568b7da9ed4d38fe5e65038ff9c498e
                startActivity(new Intent(getApplicationContext(),ViewGraph.class));

                break;

<<<<<<< HEAD
            case R.id.btnexercise:
                finish();
                startActivity(new Intent(getApplicationContext(),ExerciseActivity.class));
                break;


            case R.id.btnrss:
                startActivity(new Intent(getApplicationContext(),RssNewsActivity.class));
                break;



=======
>>>>>>> 7f3d522fd568b7da9ed4d38fe5e65038ff9c498e
            case R.id.btnexit:
                finish();

                break;

            default:
                break;
        }
    }



}