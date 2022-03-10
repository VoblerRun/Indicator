package com.example.indicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

public class HeadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        Bundle arguments = getIntent().getExtras();
        String token = arguments.get("token").toString();
        TextView textViewToChange = (TextView) findViewById(R.id.tokenView);
        textViewToChange.setText(token);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.container, CoreFragment.class, null)
                .commit();
    }
}