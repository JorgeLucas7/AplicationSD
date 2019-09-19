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
    private EditText etSexo;
    private EditText etIdade;
    private TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.nome);
        etSexo = findViewById(R.id.sexo);
        etIdade = findViewById(R.id.idade);
        tvResponse = findViewById(R.id.tvResponse);
    }

    public void enviarDados(View view){
            new HTTPReqTask().execute(etNome.getText().toString(), etSexo.getText().toString(), etIdade.getText().toString());
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
