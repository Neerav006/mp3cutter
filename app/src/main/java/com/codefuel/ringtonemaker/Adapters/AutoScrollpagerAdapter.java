package com.codefuel.ringtonemaker.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.bumptech.glide.Glide;
import com.codefuel.ringtonemaker.R;

import java.util.ArrayList;

public class AutoScrollpagerAdapter extends LoopingPagerAdapter<String> {
    private Context context;
    private ArrayList<String> imgList;
    public AutoScrollpagerAdapter(Context context, ArrayList<String> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
        this.context = context;
        this.imgList = itemList;
    }

    @SuppressLint("InflateParams")
    @Override
    protected View inflateView() {
        return LayoutInflater.from(context).inflate(R.layout.item_pager,null,false);
    }


    @Override
    protected void bindView(View convertView, int listPosition) {
        ImageView imageView = convertView.findViewById(R.id.ivLogo);
        Glide.with(context).load(imgList.get(listPosition)).into(imageView);
    }
}
