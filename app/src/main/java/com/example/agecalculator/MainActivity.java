package com.example.agecalculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private EditText dateOfBirthEditText, currentDateEditText;
    private TextView ageTextView;
    private Button calculateButton;
    private SimpleDateFormat formatForDate = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dateOfBirthEditText = findViewById(R.id.editTextText2);
        currentDateEditText = findViewById(R.id.editTextText3);
        ageTextView = findViewById(R.id.resultTextView);
        calculateButton = findViewById(R.id.calculateButton);


        currentDateEditText.setText(formatForDate.format(Calendar.getInstance().getTime()));

         // button click listener
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAge();
            }
        });
    }

    private void calculateAge() {
        String dateOfBirth = dateOfBirthEditText.getText().toString();
        String currentDate = currentDateEditText.getText().toString();

        if (dateOfBirth.isEmpty()) {
            dateOfBirthEditText.setError("Enter your birth date.");
            return;
        }

        try {

            Date birthDate = formatForDate.parse(dateOfBirth);
            Date todayDate = formatForDate.parse(currentDate);

            // Calculate age
            int age = calculateAgeFromDates(birthDate, todayDate);
            ageTextView.setText(String.format("You are %d years old.", age));
        } catch (ParseException e) {
            ageTextView.setText("Invalid date format.");
        }
    }

    private int calculateAgeFromDates(Date birthDate, Date todayDate) {
        Calendar birthCalendar = Calendar.getInstance();
        Calendar todayCalendar = Calendar.getInstance();


        birthCalendar.setTime(birthDate);
        todayCalendar.setTime(todayDate);


        int age = todayCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);


        if (todayCalendar.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }
}
