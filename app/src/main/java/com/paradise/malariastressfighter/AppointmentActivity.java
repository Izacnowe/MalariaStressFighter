package com.paradise.malariastressfighter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paradise.malariastressfighter.Chat.ChatActivity;

import java.util.Calendar;
import java.util.HashMap;

public class AppointmentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mFirebaseBtn;
    private DatabaseReference mDatabase;

    private EditText mNameField;
    private EditText mDescField;
    private EditText mDateField;
    private EditText mTimeField;

    EditText selectDate,selectTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        mFirebaseBtn = (Button) findViewById(R.id.btn_appoint);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);

        selectDate=(EditText)findViewById(R.id.date);
        selectTime=(EditText)findViewById(R.id.time);

        selectDate.setOnClickListener((View.OnClickListener) this);
        selectTime.setOnClickListener((View.OnClickListener) this);
        mNameField = (EditText) findViewById(R.id.name);
        mDescField = (EditText) findViewById(R.id.desc);
       // mSellerField = (EditText) findViewById(R.id.seller);
        mTimeField = (EditText) findViewById(R.id.time);
        mDateField = (EditText) findViewById(R.id.date);

        mFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting the input from the user and pursing them to strings

                String name = mNameField.getText().toString().trim();
                String desc = mDescField.getText().toString().trim();
                String date = mDateField.getText().toString().trim();
                String times = mTimeField.getText().toString().trim();

                //putting data data into database
                HashMap<String, String> datamap = new HashMap<String, String>();

                datamap.put("Disease_name", name);
                datamap.put("Description", desc);
                datamap.put("date", date);
                datamap.put("time", times);

                // ending to the firebase database
                mDatabase.child("Appointments").push().setValue(datamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(AppointmentActivity.this, "Data Submittion sucecssfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AppointmentActivity.this, "Data Submittion failed", Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });
    }

    // oncreate class ends here
    public void Backs(View view) {
        Intent bac = new Intent(AppointmentActivity.this, ChatActivity.class);
        startActivity(bac);
    }

    public void cancl(View view) {
        Intent Cancel = new Intent(AppointmentActivity.this, NurseAdminActivity.class);
        startActivity(Cancel);
    }

    @Override
    public void onClick(View view) {
        if (view == selectDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            selectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view == selectTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            selectTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}