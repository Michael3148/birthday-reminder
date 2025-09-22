package com.example.birthdayreminder;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class clicked extends AppCompatActivity {
    TextView birthdaysname,selecteddate,countdown;
    Button selectdate,save;
    Calendar selectedCalendar;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clicked);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        birthdaysname=findViewById(R.id.birthdayholdername);
        selecteddate=findViewById(R.id.textView7);
        countdown=findViewById(R.id.textView9);
        selectdate=findViewById(R.id.button2);
        save=findViewById(R.id.button4);



        String userbirthdayname = getIntent().getStringExtra("Getname");
        birthdaysname.setText(userbirthdayname);


        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Create DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        clicked.this,
                        (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                            selectedCalendar = Calendar.getInstance();
                            selectedCalendar.set(selectedYear, selectedMonth, selectedDay, 0, 0, 0);

                            String date = selectedDay + "(DD) /" + (selectedMonth + 1) + "(MM) /" + selectedYear + "(YY)";
                            selecteddate.setText(date);

                            startCountdown();
                        },
                        year, month, day
                );

                datePickerDialog.show();
                datePickerDialog.setCancelable(false);
            }
        });
    }
    private void startCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        Calendar now = Calendar.getInstance();

        // If the selected date has already passed this year → set to next year
        if (selectedCalendar.before(now)) {
            selectedCalendar.add(Calendar.YEAR, 1);
        }

        long targetMillis = selectedCalendar.getTimeInMillis();
        long nowMillis = now.getTimeInMillis();
        long diffMillis = targetMillis - nowMillis;

        countDownTimer = new CountDownTimer(diffMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60;
                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60;
                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 24;
                long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);

                // Approximate months and years
                long years = days / 365;
                long months = (days % 365) / 30;
                long remainingDays = (days % 365) % 30;

                countdown.setText(String.format(Locale.getDefault(),
                        "%d years, %d months, %d days, \n (%02d:%02d:%02d)",
                        years, months, remainingDays, hours, minutes, seconds));
            }

            @Override
            public void onFinish() {
                countdown.setText("The day has arrived!");
            }
        };

        countDownTimer.start();
    }
}