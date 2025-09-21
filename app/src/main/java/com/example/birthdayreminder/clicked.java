package com.example.birthdayreminder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class clicked extends AppCompatActivity {
    TextView birthdaysname,date,countdown;
    Button selectdate,save,forgetdate,countdownbutton;
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
        date=findViewById(R.id.textView7);
        countdown=findViewById(R.id.textView9);
        selectdate=findViewById(R.id.button2);
        save=findViewById(R.id.button4);

        String userbirthdayname = getIntent().getStringExtra("Getname");
        birthdaysname.setText(userbirthdayname);
    }
}