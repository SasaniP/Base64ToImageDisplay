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

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ImageView imageView;
    TextView Text;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        mRecyclerView = findViewById(R.id.recycler_view);
        Text = findViewById(R.id.Text);


        String searchUrl = "https://www.google.com/search?q=cars" ;


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(searchUrl).get();
                    Elements links = doc.select("cite");
                    //Text.setText(links.size());
                    Elements img = doc.select("img");

                    final HashSet<String> linkSet = new HashSet<>();
                    final ArrayList<Bitmap> imageSet = new ArrayList<>();

                    for (int j = 0; j < links.size(); j++) {
                        String text = links.get(j).text();
                        if (text.contains("›")) {
                            text = text.replace(" › ", "/");
                            text = text.replace(" ", "");
                        }
                        linkSet.add(text);
                        ArrayList<String> linkList = new ArrayList<>(linkSet);

                        String base64String = img.get(j).attr("src");
                        String base64Image = base64String.split(",")[1];
                        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imageSet.add(decodedByte);

                        ArrayList<Bitmap> imageList = new ArrayList<>(imageSet);




                        MainActivity.this.runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                mLayoutManager=new LinearLayoutManager(MainActivity.this);
                                mAdapter=new MainAdapter(linkList, imageList);
                                mRecyclerView.setLayoutManager(mLayoutManager);
                                mRecyclerView.setAdapter(mAdapter);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();



        //String base64String = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAIAAACQkWg2AAAABnRSTlMAAAAAAABupgeRAAAALUlEQVR4AWNAA8bZM4EInYsJMPVgqiasB6GacoAwGBciSwPlnhlVTf+IIxsAAOw5Up2jHocxAAAAAElFTkSuQmCC";
        //String base64Image = base64String.split(",")[1];
        //byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        //Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        //imageView.setImageBitmap(decodedByte);



    }

}