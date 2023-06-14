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
    ImageView imageView;
    TextView Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        mRecyclerView = findViewById(R.id.recycler_view);
        Text = findViewById(R.id.Text);


        String searchUrl = "https://www.google.com/search?q=cars" ;

        //TODO make a string of 64 bit, then an array, then print the array, in a textView


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    ArrayList<String> nameList = new ArrayList<>();
                    ArrayList<Bitmap> iconList = new ArrayList<>();

                    Document doc = Jsoup.connect(searchUrl).timeout(0).get();
                    Elements images = doc.select("img.XNo5Ab");

                    for (int i = 0; i < images.size(); i++) {
                        String base64String = images.get(i).absUrl("src");
                        String base64Image = base64String.split(",")[1];
                        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        iconList.add(decodedByte);
                        nameList.add(base64String);
                        ArrayList<String> nameSet = new ArrayList<>(nameList);
                        ArrayList<Bitmap> iconSet = new ArrayList<>(iconList);

                        MainActivity.this.runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                mLayoutManager=new LinearLayoutManager(MainActivity.this);
                                mAdapter=new MainAdapter(nameSet, iconSet);
                                mRecyclerView.setLayoutManager(mLayoutManager);
                                mRecyclerView.setAdapter(mAdapter);
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Text.setText("gfg");
                }
            }
        });

        thread.start();



        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<String> linkList = new ArrayList<>();

                    Document doc = Jsoup.connect(searchUrl).timeout(0).get();
                    Element image = doc.select("img.XNo5Ab").first();
                    String base64String = image.absUrl("src");
                    //Text.setText(base64String);
                    //String base64String = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAIAAACQkWg2AAAABnRSTlMAAAAAAABupgeRAAAALUlEQVR4AWNAA8bZM4EInYsJMPVgqiasB6GacoAwGBciSwPlnhlVTf+IIxsAAOw5Up2jHocxAAAAAElFTkSuQmCC";
                    String base64Image = base64String.split(",")[1];
                    byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    //Text.setText(base64String);
                    imageView.setImageBitmap(decodedByte);
                    //imageView.setImageBitmap(decodedByte);




                    for (int j = 0; j < elements.size(); j++) {
                        String text = elements.get(j).text();
                        linkList.add(text);


                    }
                    for( String l : linkList){
                        Text.setText(l);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    String base64String = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAIAAACQkWg2AAAABnRSTlMAAAAAAABupgeRAAAALUlEQVR4AWNAA8bZM4EInYsJMPVgqiasB6GacoAwGBciSwPlnhlVTf+IIxsAAOw5Up2jHocxAAAAAElFTkSuQmCC";
                    String base64Image = base64String.split(",")[1];
                    byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    imageView.setImageBitmap(decodedByte);
                }

            }

        }).start();
*/


    }
}


/*

new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(searchUrl).get();
                    Elements links = doc.select("cite");
                    //Text.setText(links.size());
                    Elements img = doc.select("img");

                    //final HashSet<String> linkSet = new HashSet<>();
                    final ArrayList<Bitmap> imageSet = new ArrayList<>();

                    for (int j = 0; j < img.size(); j++) {
                        String text = img.get(j).text();
                        Text.setText(text);
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

        */