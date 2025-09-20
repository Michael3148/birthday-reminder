package com.example.birthdayreminder;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class second extends Dialog {
    private final String message;
    private final MainActivity mainActivity;
    Button deletefinalname;
    Button edittextbutton;

    public second(@NonNull Context context, String message, MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edittextbutton = findViewById(R.id.editfinalname);
        deletefinalname = findViewById(R.id.deletefinalname);

        deletefinalname.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainActivity.delete();
                //delete();
                dismiss();
            }
        });
        edittextbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainActivity.edit();
                //edit();
                dismiss();
            }
        });
    }

    @SuppressLint("GestureBackNavigation")
    @Override
    public void onBackPressed() {
        // Your custom action
        dismiss();
        // If you want to close the activity afterwards:
        super.onBackPressed();

    }
}