package com.example.birthdayreminder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView plusicon;
    ImageView menuicon;
    EditText name;
    Button save;
    TextView givenname;
    private Toast backToast;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        plusicon = findViewById(R.id.plusimageicon);
        menuicon = findViewById(R.id.menuimageicon);
        name = findViewById(R.id.editText);
        save = findViewById(R.id.addbutton);
        givenname = findViewById(R.id.resultText);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputname = name.getText().toString().trim();
                if (!inputname.isEmpty()) {
                    givenname.setText(inputname);
                    givenname.setVisibility(View.VISIBLE);
                    name.setVisibility(View.GONE);
                    save.setVisibility(View.GONE);
                } else if (inputname.isEmpty()) {
                    name.setError("Enter a name");
                }
            }
        });
        givenname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                second resultdialogue = new second(MainActivity.this,"",MainActivity.this);
                resultdialogue.setCancelable(true);
                resultdialogue.show();
                return true;
            }
        });
        givenname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void delete() {
        ViewGroup parent=(ViewGroup) givenname.getParent();
        ViewGroup parent2=(ViewGroup) name.getParent();
        parent2.removeView(name);
        parent.removeView(givenname);

        View includedLayout = findViewById(R.id.relativeLayoutfirst);
        ViewGroup parent3 = (ViewGroup) includedLayout.getParent();

        parent3.removeView(includedLayout);
    }

    public void edit() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);

        givenname.setVisibility(View.GONE);
        name.setVisibility(View.VISIBLE);
        save.setVisibility(View.VISIBLE);
        name.requestFocus();
    }
    @SuppressLint("GestureBackNavigation")
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            // If pressed within 2 seconds, exit the app
            if (backToast != null) {
                backToast.cancel();
            }
            super.onBackPressed();   // finish the activity
            return;
        } else {
            // First press → show message
            backToast = Toast.makeText(getBaseContext(), "Press again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}