package com.nicruo.androidworkshop;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

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

                    new AsyncTask<Void, Void, String>() {

                        @Override
                        protected String doInBackground(Void... voids) {
                            try {
                                URL pokeapiEndpoint = null;
                                pokeapiEndpoint = new URL("https://pokeapi.co/api/v2/berry/2/");
                                HttpsURLConnection myConnection = (HttpsURLConnection) pokeapiEndpoint.openConnection();

                                int responseCode = myConnection.getResponseCode();

                                InputStream responseBody = myConnection.getInputStream();
                                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");

                                JsonObject root = new JsonParser().parse(responseBodyReader).getAsJsonObject();


                                return root.get("flavors").getAsJsonArray().get(0).getAsJsonObject().get("flavor").getAsJsonObject().get("name").getAsString();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }


                        @Override
                        protected void onPostExecute(String value) {
                            super.onPostExecute(value);
                            loginButton.setText(value);
                            /*Intent intent = new Intent(context, HomeActivity.class);
                            intent.putExtra("name", "nome");
                            startActivity(intent);*/
                        }
                    }.execute();





                }
            }
        });

    }
}
