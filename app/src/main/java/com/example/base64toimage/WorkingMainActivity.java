package com.example.base64toimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
/*
public class WorkingMainActivity {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ImageView imageView;
    TextView Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        mRecyclerView = findViewById(R.id.recycler_view);
        Text = findViewById(R.id.Text);


        String searchUrl = "https://www.google.com/search?q=buy+mens+shirts+in+sri+lanka";

        //TODO make a string of 64 bit, then an array, then print the array, in a textView


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(searchUrl).get();
                    Element image = doc.select("img.XNo5Ab").first();
                    String base64String = image.absUrl("src");

                    //String base64String = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAIAAACQkWg2AAAABnRSTlMAAAAAAABupgeRAAAALUlEQVR4AWNAA8bZM4EInYsJMPVgqiasB6GacoAwGBciSwPlnhlVTf+IIxsAAOw5Up2jHocxAAAAAElFTkSuQmCC";
                    String base64Image = base64String.split(",")[1];
                    byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    Text.setText(base64String);
                    imageView.setImageBitmap(decodedByte);

                } catch (Exception e) {
                    e.printStackTrace();
                    //Text.setText("gfg");
                }
            }
        });

        thread.start();
    }
}*/
