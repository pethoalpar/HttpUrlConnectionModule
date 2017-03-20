package com.example.pethoalpar.httprequestexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import pethoalpar.com.alphttpclient.HttpCall;
import pethoalpar.com.alphttpclient.HttpRequest;

public class MainActivity extends AppCompatActivity {

    public static final String ADDRESS = "http://192.168.0.102:7676";
    private TextView textView;
    private Button loginButton;
    private Button logoutButton;
    private Button addButton;
    private Button getButton;

    private TextView modelTextView;
    private TextView fuelTextView;
    private TextView yearTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) this.findViewById(R.id.textView);

        loginButton = (Button) this.findViewById(R.id.buttonLogin);
        logoutButton = (Button) this.findViewById(R.id.buttonLogout);
        addButton = (Button) this.findViewById(R.id.buttonAdd);
        getButton = (Button) this.findViewById(R.id.buttonGet);

        modelTextView = (TextView) this.findViewById(R.id.editTextModel);
        fuelTextView = (TextView) this.findViewById(R.id.editTextFuel);
        yearTextView = (TextView) this.findViewById(R.id.editTextYear);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpCall httpCall = new HttpCall();
                httpCall.setMethodtype(HttpCall.POST);
                httpCall.setUrl(ADDRESS + "/login");
                HashMap<String,String> params = new HashMap<>();
                JSONObject json = new JSONObject();
                try {
                    json.put("userName","demo");
                    json.put("password","demo");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                params.put("",json.toString());
                httpCall.setParams(params);
                new HttpRequest(){
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                    }
                }.execute(httpCall);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpCall httpCall= new HttpCall();
                httpCall.setMethodtype(HttpCall.POST);
                httpCall.setUrl(ADDRESS + "/data/add");
                HashMap<String,String> params = new HashMap<>();
                params.put("",new Gson().toJson(new Car(modelTextView.getText().toString(),fuelTextView.getText().toString(),Integer.parseInt(yearTextView.getText().toString()))));
                httpCall.setParams(params);
                new HttpRequest(){
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        Toast.makeText(getApplicationContext(),"Add:"+response,Toast.LENGTH_SHORT).show();
                    }
                }.execute(httpCall);
            }
        });

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpCall httpCall = new HttpCall();
                httpCall.setMethodtype(HttpCall.GET);
                httpCall.setUrl(ADDRESS + "/data/getAllCars");
                new HttpRequest(){
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                    }
                }.execute(httpCall);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpCall httpCall = new HttpCall();
                httpCall.setMethodtype(HttpCall.POST);
                httpCall.setUrl(ADDRESS + "/logout");
                new HttpRequest(){
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        Toast.makeText(getApplicationContext(),"Logout:"+response,Toast.LENGTH_SHORT).show();
                    }
                }.execute(httpCall);
            }
        });
    }

}
