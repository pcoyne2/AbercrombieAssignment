package com.pcoyne.abercrombie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.pcoyne.abercrombie.adapters.MyAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<List<ExampleModel>>{
    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter = new MyAdapter(this, new ArrayList<ExampleModel>());
        recyclerView.setAdapter(adapter);

        Retrofit retrofit=
                new Retrofit.Builder()
                        .baseUrl("https://www.abercrombie.com") // I was getting acess denied errors put file on s3 bucket
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        AbercrombieAPI so=
                retrofit.create(AbercrombieAPI.class);

        so.examples().enqueue(this);
    }

    @Override
    public void onResponse(Call<List<ExampleModel>> call, Response<List<ExampleModel>> response) {
        if(response.isSuccessful()) {
            for (int i = 0; i < response.body().size(); i++) {
                Log.d("TAG", response.body().get(i).getTitle());
            }
            adapter.updateList(response.body());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<List<ExampleModel>> call, Throwable t) {
        Log.d(TAG, t.toString());
    }
}
