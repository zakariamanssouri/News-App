package com.example.newsapifinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapifinal.Models.Article;
import com.example.newsapifinal.Models.News;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private List<Article> articles;
    private Context context;
    private RelativeLayout item;

    public CustomAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new CustomViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.title.setText(articles.get(position).getTitle());
        holder.author.setText(articles.get(position).getAuthor());
        holder.publishedAt.setText(articles.get(position).getPublishedAt().toString());
        holder.content =articles.get(position).getContent();
        holder.imageUrl = articles.get(position).getImageurl();

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(articles.get(position).getImageurl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        private View mview;
        TextView title;
        TextView author;
        TextView publishedAt;
        private ImageView image;
        private String content;
        private String imageUrl;
        private RelativeLayout item;

        public CustomViewHolder(@NonNull View view, Context context) {
            super(view);
            this.mview = view;
            title = mview.findViewById(R.id.title);
            author = mview.findViewById(R.id.author);
            publishedAt = mview.findViewById(R.id.publishedDate);
            image = mview.findViewById(R.id.imageNew);


            mview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newsdetailactiviy = new Intent(context, NewsDetailsActivity.class);
                    newsdetailactiviy.putExtra("content",content);
                    newsdetailactiviy.putExtra("title",title.getText());
                    newsdetailactiviy.putExtra("imageurl",imageUrl);
                    context.startActivity(newsdetailactiviy);
                }
            });
        }


    }


}
