
package com.example.willproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NearByConsultants extends AppCompatActivity {

    //MapActivtiy mapActivtiy;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Listitem> listitems;
    private  static final int REQUEST_CODE = 101;
    FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_consultants);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listitems = new ArrayList<>();
//           loadRecyclerviewData();


        final double letValue = getIntent().getDoubleExtra("latitude", 12.12);
        final double longValue = getIntent().getDoubleExtra("longitude", 14.13);


        final String URL_DATA = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + letValue + "," + longValue + "&radius=4500&type=financial%20consultants&keyword=financial%20consultants&key=AIzaSyBmEX_q5Lq1h7oE4hA6FYcHJqhdFOxQSeg";

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LoadingData.....");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();
                try {
                    Toast.makeText(getApplicationContext(),longValue+"  "+letValue,Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.getJSONArray("results");

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject o = array.getJSONObject(i);
                        Listitem item = new Listitem(o.getString("name"), o.getString("rating"), o.getString("icon"));
                        Log.d("tag", String.valueOf(item));
                        listitems.add(item);
                    }
                    adapter = new MyAdapter(listitems, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
//                progressDialog.dismiss();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
