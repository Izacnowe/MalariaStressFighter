package com.paradise.malariastressfighter.Health;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.paradise.malariastressfighter.R;
import com.paradise.malariastressfighter.Health.TextMessage.*;

public class TextMessage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
EditText textMessage;
    EditText phoneText;
Button sendBtn;
Spinner mSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_message);

        textMessage =(EditText)findViewById(R.id.text_messa);
       // phoneText =(EditText)findViewById(R.id.phone_text);
        sendBtn =(Button) findViewById(R.id.message_btn);
        mSpinner =(Spinner) findViewById(R.id.phone_spinner);

        Spinner spinner = (Spinner) findViewById(R.id.phone_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.phone, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


sendBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String phoneNumber= mSpinner.getSelectedItem().toString();
        String textMes= textMessage.getText().toString();

        if (phoneNumber.length()>0 && textMes.length()>0){
            sendMessage(phoneNumber,textMes);
        }
        else {
            Toast.makeText(getBaseContext(), "Please enter message", Toast.LENGTH_LONG).show();
        }
    }
});

    }
    private void sendMessage(String phoneNumber,String textMes){

        try {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, textMes, null, null);
           // String phonenumber = mSpinner.getSelectedItem().toString();
            Toast.makeText(getApplicationContext(), "SMS sent", Toast.LENGTH_LONG).show();
        }catch ( Exception e){
            Toast.makeText(getApplicationContext(), "SMS not sent. Please try Again!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) findViewById(R.id.phone_spinner);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
