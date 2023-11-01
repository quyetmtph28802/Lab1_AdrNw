package com.example.lab1.Lab1_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab1.R;

public class MainActivityLab1_4 extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTime;
    private Button btnRun;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab14);
        edtTime = findViewById(R.id.edtTime);
        btnRun = findViewById(R.id.btnRun);
        txtResult = findViewById(R.id.txtResult);
        btnRun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRun){
            AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner(txtResult, edtTime, this);
            String sleepTime = edtTime.getText().toString();
            asyncTaskRunner.execute(sleepTime);
        }
    }
}