package com.example.indicator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.service.controls.actions.BooleanAction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.indicator.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'indicator' library on application startup.
    static {
        System.loadLibrary("indicator");
    }

    private ActivityMainBinding binding;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI(5));

    }

    public void onMyButtonClick(View view)
    {
        // выводим сообщение


        Validator validator = new Validator();
        Boolean isCorrectEnterText = validator.isCorrectText();

        SenderRequest senderRequest = new SenderRequest();
        try {
            senderRequest.doInBackground();
            Toast.makeText(this, "Хорошо", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Плохо", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        if(isCorrectEnterText) {
            Toast.makeText(this, getTextInputField(R.id.textInputLayout_user_name), Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * A native method that is implemented by the 'indicator' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI(int a);

    private String getTextInputField(int Id){
        TextInputLayout textInputLayout = findViewById(Id);
        String text = String.valueOf(textInputLayout.getEditText().getText());
        return text;
    }
}