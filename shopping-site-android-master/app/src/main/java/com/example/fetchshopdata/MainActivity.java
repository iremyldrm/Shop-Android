package com.example.fetchshopdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


   // private TextView mTextViewResult;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonParse =findViewById(R.id.button_parse);
        mQueue = Volley.newRequestQueue(this);
        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });

    }


    private void jsonParse(){
        String url = "https://shopapp2.azurewebsites.net/api/items";
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type category = new TypeToken<List<Item>>(){}.getType();
                final Gson gson = new Gson();
                String res = response.toString();
                List<Item> items = gson.fromJson(res,category);
//
                final ListView listView = (ListView) findViewById(R.id.listView);

                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), items );
                listView.setAdapter(adapter);



                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> a,
                                            View v, int position, long id) {
                        Item item = (Item) a.getItemAtPosition(position);
                        Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
                        intent.putExtra("com.example.fetchshopdata.Item", item);
                        startActivity(intent);
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
        Button buttonParse =findViewById(R.id.button_parse);

        buttonParse.setVisibility(View.GONE);

    }
}
