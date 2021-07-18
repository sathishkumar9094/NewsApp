package com.making.newsapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.making.newsapp.R;
import com.making.newsapp.customclass.CustomAppCompatActivity;
import com.squareup.picasso.Picasso;

public class NewsDetailedActivity extends CustomAppCompatActivity {

    final public static int FRAGMENT_NEWSDETAILS = 1;

    String title, desc, content, imageUrl, url, publishedAt;
    TextView titleTv, descTv, contentTv, dateTv;
    ImageView newsIv;
    Button readNewsBtn;
    String dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detailed);

        getIntents();
        initializeViews();
        setUpValues();
        setUpListeners();


    }

    public void getIntents() {

        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
        content = getIntent().getStringExtra("content");
        imageUrl = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");
        publishedAt = getIntent().getStringExtra("publishedAt");

        dates=publishedAt;

    }

    public void initializeViews() {
        titleTv = findViewById(R.id.idTvTitle);
        descTv = findViewById(R.id.idTvSubDesc);
        contentTv = findViewById(R.id.idTvContent);
        newsIv = findViewById(R.id.idIvNews);
        readNewsBtn = findViewById(R.id.idBtnReadNews);
        dateTv = findViewById(R.id.dateTv);


    }

    public void setUpValues() {


        titleTv.setText(title);
        descTv.setText(desc);
        contentTv.setText(content);
        dateTv.setText(dates);

        Picasso.with(getApplicationContext()).load(imageUrl).into(newsIv);


    }

    public void setUpListeners() {

        readNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }


}


