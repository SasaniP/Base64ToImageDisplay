package com.example.base64toimage;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<String> mContacts;
    ArrayList<Bitmap> mImages;
    ArrayList<String> mLinks;


    public MainAdapter(ArrayList<String> contacts, ArrayList<Bitmap> images, ArrayList<String> links) {
        mContacts = contacts;
        mImages = images;
        mLinks = links;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        holder.mFullName.setText (mContacts.get(position));
        holder.mImageIcon.setImageBitmap (mImages.get(position));
        holder.mLink.setText (mLinks.get(position));
    }
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mFullName;
        public ImageView mImageIcon;
        public TextView mLink;

        public ViewHolder(View itemView) {
            super(itemView);
            mFullName = itemView.findViewById(R.id.full_name);
            mImageIcon = itemView.findViewById(R.id.image_icon);
            mLink = itemView.findViewById(R.id.link_text);
        }
    }
}

