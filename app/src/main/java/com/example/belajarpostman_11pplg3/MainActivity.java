package com.example.belajarpostman_11pplg3;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        new AsyncTask<String, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = new ProgressDialog(MainActivity.this);
                loading.setMessage("Tunggu sebentar...");
                loading.setCancelable(false);
                loading.show();
            }

            @Override
            protected String doInBackground(String... strings) {
                String uName = strings[0];
                String pWord = strings[1];

                try {
                    URL url = new URL("https://mediadwi.com/api/latihan/login");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    String request = "username=" + uName +
                            "&password=" + pWord;

                    OutputStream os = connection.getOutputStream();
                    os.write(request.getBytes(StandardCharsets.UTF_8));
                    os.flush();
                    os.close();

                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(connection.getInputStream())
                    );

                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }

                    br.close();
                    return response.toString();

                } catch (Exception e) {
                    return "ERROR: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loading.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    Boolean status = jsonObject.getBoolean("status");
                    String message = jsonObject.getString("message");

                    Toast.makeText(MainActivity.this, "Hasil login: " + message, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error: " + result, Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(username, password);
    }
}