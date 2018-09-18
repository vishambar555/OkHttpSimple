package com.example.vishambar.webserviceusingokhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private OkHttpClient okHttpClient;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

public void hitApiByGson(View view){
    model = new Model("vish@gmail.com", "blacblac", "12345");
    okHttpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build();
//okHttpClient=new OkHttpClient();

    Request request = new Request.Builder()
            .url("http://todolistmobileapp-env.ap-south-1.elasticbeanstalk.com/webapi/authors")
            .addHeader("Content-Type", "application/json")
            .post(RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(model)))
            .build();


    okHttpClient.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onResponse(Call call, final Response response) throws IOException {
              final Model  model = new Gson().fromJson(response.body().string(), Model.class);
            Log.d("fjkf", "fjdk");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, model.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    });
}
    public void hitApiByJsonParsing(View view) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("authorEmailId", "vish@gmail.com");
            jsonObject.put("authorName", "vishambar");
            jsonObject.put("authorPassword", "12345");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
//okHttpClient=new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://todolistmobileapp-env.ap-south-1.elasticbeanstalk.com/webapi/authors")
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString()))

                .build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String value = response.body().string();
//                model = new Gson().fromJson(response.body().string(), Model.class);
                Log.d("fjkf", "fjdk");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}
