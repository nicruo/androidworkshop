package com.nicruo.androidworkshop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.loginButton);
        nameEditText = findViewById(R.id.nameEditText);

        final Context context = this;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameEditText.getText().toString().equals("nome")) {
                    loginButton.setText(R.string.loaded);
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putExtra("name", "nome");
                    startActivity(intent);
                }
            }
        });

    }
}
