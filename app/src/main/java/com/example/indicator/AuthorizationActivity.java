package com.example.indicator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.controls.actions.BooleanAction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.indicator.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AuthorizationActivity extends AppCompatActivity {

    // Used to load the 'indicator' library on application startup.

    private ActivityMainBinding binding;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onMyButtonClick(View view)
    {
        String text = getTextInptiField(R.id.outlinedTextField);
        String password = getTextInptiField(R.id.outlinedTextField2);
        ArrayList<String> passing = new ArrayList<String>();
        passing.add(text);
        passing.add(password);

        new WebServiceGet().execute(passing);
    }

    private String getTextInptiField(int idField){
        TextInputLayout textInputLayout = findViewById(idField);
        return String.valueOf(textInputLayout.getEditText().getText());
    }
}