package com.example.minwoo.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Minwoo on 2016. 5. 12..
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c){
        mContext = c;
    }
    public int getCount(){
        return mThumbIds.length;
    }
    public Object getItem(int position){
        return null;
    }
    public long getItemId(int position){
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85,85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);

        }else{
            imageView = (ImageView)convertView;

        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
    private Integer[] mThumbIds = {
            R.drawable.sample_1,R.drawable.sample_2,
            R.drawable.sample_3,R.drawable.sample_4,
    };
}
