package com.DiabetesManagement;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RegisterNewActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private EditText txt_load;
    private EditText txt_repetition;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_activity);
        txt_load = findViewById(R.id.t_load);
        txt_repetition = findViewById(R.id.t_repetition);
        //set the default keyboard to be the numerical
        txt_load.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        txt_repetition.setRawInputType(InputType.TYPE_CLASS_NUMBER);


        spinner = findViewById(R.id.spinnerExerciseList);
        loadSpinnerData(); //load spinner data
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_exercise, menu);

        return true;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String exercise = parent.getItemAtPosition(position).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the bundle
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        //save spinner actual spinner selection
        savedInstanceState.putInt("spinnerPos", spinner.getSelectedItemPosition());
        //save string that is on load box
        savedInstanceState.putString("load", txt_load.getText().toString());
        // save string that is on repetition box
        savedInstanceState.putString("repetition", txt_repetition.getText().toString());
        // etc.
    }

    //in case of recreation of the activity, this method restores the values
    // that ware on the edittexts before the destruction
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        spinner.getSelectedItemPosition();
        int spinnerPosition = savedInstanceState.getInt("spinnerPos");
        String loadunitSaved = savedInstanceState.getString("load");
        String repunitSaved = savedInstanceState.getString("repetition");

        txt_load.setText(loadunitSaved); //set load
        txt_repetition.setText(repunitSaved); // set repetition
        spinner.setSelection(spinnerPosition); //set spinner position
    }

    private void loadSpinnerData() {
        // database handler
        MyDBHandler db = new MyDBHandler(this, null, null, 1);
        // Spinner Drop down elements
        List<String> exercises = db.getAllExercises();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, exercises);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }


    public void addActivity(View view){
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        String name = spinner.getSelectedItem().toString();
        if(editTextIsEmpty(txt_load)){
            //SEND MESSAGE WARNING
            final Dialog dialog = new Dialog(RegisterNewActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_box);

            // set the custom dialog components - text and button
            TextView text = dialog.findViewById(R.id.txtDiaTitle);
            text.setText(R.string.alert);
            TextView image = dialog.findViewById(R.id.txtDiaMsg);
            image.setText(R.string.alert_blank_load);
            Button dialogButton = dialog.findViewById(R.id.btnOk);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });
            dialog.show();
        }else if(editTextIsEmpty(txt_repetition)){
            //SEND MESSAGE WARNING

            final Dialog dialog = new Dialog(RegisterNewActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_box);
            // set the custom dialog components - text and button
            TextView text = dialog.findViewById(R.id.txtDiaTitle);
            text.setText(R.string.alert);
            TextView image = dialog.findViewById(R.id.txtDiaMsg);
            image.setText(R.string.alert_blank_rep);
            Button dialogButton = dialog.findViewById(R.id.btnOk);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });
            dialog.show();
        }
        else {

            //REGISTER THE ACTIVITY INTO THE EXERCISE TABLE
            int load = Integer.parseInt(txt_load.getText().toString());
            int repetition = Integer.parseInt(txt_repetition.getText().toString());
            Exercise ex = new Exercise(name, load, repetition);


            dbHandler.addExercise(ex);
            final Dialog dialog = new Dialog(RegisterNewActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_box);
            TextView text = dialog.findViewById(R.id.txtDiaTitle);
            text.setText(R.string.alert);
            TextView image = dialog.findViewById(R.id.txtDiaMsg);
            image.setText(R.string.alert_activity_added);
            Button dialogButton = dialog.findViewById(R.id.btnOk);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txt_load.setText("");
                    txt_repetition.setText("");
                    dialog.dismiss();

                }
            });
            dialog.show();
        }


    }
    //button action on click
    public void cancelClick(View view){
        finish(); //close activity
    }

    //verifies if the edittext is empty
    public boolean editTextIsEmpty(EditText et) {
        return et.getText().toString().trim().length() == 0;
    }

   //logo click, calls menu activity
    public void clickIconMenu(View view){
        Intent intent = new Intent(RegisterNewActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
