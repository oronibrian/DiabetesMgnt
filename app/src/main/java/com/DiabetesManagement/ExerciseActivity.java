package com.DiabetesManagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        // Get the database reference in the device
        MyDBHandler db = new MyDBHandler(this, null, null, 1);
        db.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //click on the add exercise button
    public void newExerciseClick(View view){
        Intent intent = new Intent(ExerciseActivity.this, AddExerciseToRoutine.class);
        startActivity(intent);
    }
    //click on the register button
    public void registerActivityClick(View view){
        Intent intent = new Intent(ExerciseActivity.this, RegisterNewActivity.class);
        startActivity(intent);
    }


    /**
     * Method that allows the invocation of the menu in this activity
     */
    public void clickIconMenu(View view){
        Intent intent = new Intent(ExerciseActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}