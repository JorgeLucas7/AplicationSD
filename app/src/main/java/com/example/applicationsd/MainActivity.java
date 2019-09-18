package com.example.applicationsd;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nEt;
    private EditText sEt;
    private EditText eEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nEt = findViewById(R.id.nome);
        sEt = findViewById(R.id.sobrenome);
        eEt = findViewById(R.id.email);
    }

    public void enviarDados(View view){
        new HTTPReqTask().execute(nEt.getText().toString(), sEt.getText().toString(), eEt.getText().toString());
    }
}
