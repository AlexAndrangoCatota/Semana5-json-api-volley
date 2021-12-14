package com.example.semana5_json_api_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button button;
    //Semana 5
    TextView lblMensaje1;
    RequestQueue requestQueue;
    private static final String URL1 = "https://gorest.co.in/public/v1/users";

//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

   //     initUI();
        initUI1();
        jsonObjectRequest();

//        button.setOnClickListener((view) ->  {
//            startActivity(new Intent(MainActivity.this, validarLogin.class));
//        });

    }
//    private void initUI(){
//        button = findViewById(R.id.button);
//    }
    private void initUI1() { lblMensaje1 = findViewById(R.id.lblMensaje1);
    }



    private void jsonObjectRequest(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            int size = jsonArray.length();
                            for (int i = 0; i<size; i++){
                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                String ID = jsonObject.getString("id");
                                lblMensaje1.append("ID: "+ID+"\n");

                                String name = jsonObject.getString("name");
                                lblMensaje1.append("NAME: "+name+"\n");

                                String email = jsonObject.getString("email");
                                lblMensaje1.append("EMAIL: "+email+"\n");

                                String gender = jsonObject.getString("gender");
                                lblMensaje1.append("GENERO: "+gender+"\n");

                                String status = jsonObject.getString("status");
                                lblMensaje1.append("ESTADO: "+status+"\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }


}