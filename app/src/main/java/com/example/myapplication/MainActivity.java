package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    RequestQueue queue;
    String URL = "https://apiclinica.000webhostapp.com/api/";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response.toString());
                try {
                    JSONObject object=new JSONObject(response);
                    JSONArray array=object.getJSONArray("data");
                    for(int i=0;i<array.length();i++){
                        JSONObject object1=array.getJSONObject(i);
                        String prueba = object1.getString("Nombre");
                        String prueba2 = object1.getString("Descripcion");
                        System.out.println("Probando" + prueba);
                        Toast.makeText(MainActivity.this, "Nombre: " + prueba, Toast.LENGTH_SHORT).show();

                        textView.setText("Descripcion:" + prueba + "\n" + "Correo:" + prueba2)  ;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());

            }
        });

        queue.add(request);
    }

}


