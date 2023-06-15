package com.example.base64toimage;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);


        String searchUrl = "https://www.google.com/search?q=cars";

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    ArrayList<String> nameList = new ArrayList<>();
                    ArrayList<Bitmap> iconList = new ArrayList<>();
                    ArrayList<String> linkList = new ArrayList<>();

                    Document doc = Jsoup.connect(searchUrl).timeout(0).get();

                    Elements names = doc.select("span.VuuXrf");
                    Elements images = doc.select("img.XNo5Ab");
                    Elements links = doc.select("cite");

                    for (int i = 0; i < images.size(); i++) {
                        String base64String = images.get(i).absUrl("src");
                        String base64Image = base64String.split(",")[1];
                        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        iconList.add(decodedByte);
                        int j = i * 2;
                        String name = names.get(j).text();
                        nameList.add(name);
                        String link = links.get(j).text();
                        linkList.add(link);

                        ArrayList<String> nameSet = new ArrayList<>(nameList);
                        ArrayList<Bitmap> iconSet = new ArrayList<>(iconList);
                        ArrayList<String> linkSet = new ArrayList<>(linkList);

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mLayoutManager = new LinearLayoutManager(MainActivity.this);
                                mAdapter = new MainAdapter(nameSet, iconSet, linkSet);
                                mRecyclerView.setLayoutManager(mLayoutManager);
                                mRecyclerView.setAdapter(mAdapter);
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}