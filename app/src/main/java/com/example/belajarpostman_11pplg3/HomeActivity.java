package com.example.belajarpostman_11pplg3;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rvdatauser;
    ArrayList<UserModel> listUser;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvdatauser = findViewById(R.id.rvdatauser);
        listUser = new ArrayList<>();

        userAdapter = new UserAdapter(HomeActivity.this, listUser);
        rvdatauser.setLayoutManager(new LinearLayoutManager(this));
        rvdatauser.setAdapter(userAdapter);

        getUsers();
    }

    private void getUsers() {
        new AsyncTask<Void, Void, String>() {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = new ProgressDialog(HomeActivity.this);
                loading.setMessage("loading data user");
                loading.setCancelable(false);
                loading.show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("https://jsonplaceholder.typicode.com/users");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    br.close();
                    return response.toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "";
            }

            @Override
            protected void onPostExecute(String respon) {
                super.onPostExecute(respon);
                loading.dismiss();

                try {
                    JSONArray jsonArrayUser = new JSONArray(respon);
                    for (int i = 0; i < jsonArrayUser.length(); i++) {
                        JSONObject jsonObject = jsonArrayUser.getJSONObject(i);

                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String username = jsonObject.getString("username");
                        String email = jsonObject.getString("email");
                        String phone = jsonObject.getString("phone");
                        String website = jsonObject.getString("website");

                        JSONObject addressObj = jsonObject.getJSONObject("address");
                        String fullAddress = addressObj.getString("street") + ", " + addressObj.getString("city");

                        JSONObject companyObj = jsonObject.getJSONObject("company");
                        String companyName = companyObj.getString("name");

                        UserModel userModel = new UserModel(
                                id, name, username, email, fullAddress, phone, website, companyName
                        );

                        listUser.add(userModel);
                    }

                    userAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }
}