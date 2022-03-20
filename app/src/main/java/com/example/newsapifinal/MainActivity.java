package com.example.newsapifinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapifinal.ApiServices.NewsService;
import com.example.newsapifinal.ApiServices.RetrofitClientService;
import com.example.newsapifinal.Models.Article;
import com.example.newsapifinal.Models.News;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView key = findViewById(R.id.key);
        TextView date = findViewById(R.id.date);
        Button search = findViewById(R.id.search_button);
        NewsService newsService = RetrofitClientService.getRetrofitInstance().create(NewsService.class);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!key.getText().equals("") && !date.getText().equals("")) {
                    Call<News> call = newsService.getNews(key.getText().toString(),date.getText().toString(),NewsService.API_KEY);
                call.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        if(response.body()!=null)
                            if(response.body().getStatus().equals("ok"))
                                generateDataList(response.body().getArticles());
                        else Toast.makeText(
                                MainActivity.this,
                                    "An Error Occured please try again", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "An Error Occured please try again", Toast.LENGTH_SHORT).show();

                    }
                });

                }

            }
        });




    }

    private void generateDataList(List<Article> articles) {
        recyclerView = findViewById(R.id.recycleview);
        customAdapter = new CustomAdapter(articles,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customAdapter);

    }


}