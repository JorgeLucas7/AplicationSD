package com.example.applicationsd;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etSobrenome;
    private EditText etEmail;
    private TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.nome);
        etSobrenome = findViewById(R.id.sobrenome);
        etEmail = findViewById(R.id.email);
        tvResponse = findViewById(R.id.tvResponse);
    }

    public void enviarDados(View view){
        new HTTPReqTask().execute(etNome.getText().toString(), etSobrenome.getText().toString(), etEmail.getText().toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        tvResponse.setText(tvResponse.getText() + "\n" + event);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
