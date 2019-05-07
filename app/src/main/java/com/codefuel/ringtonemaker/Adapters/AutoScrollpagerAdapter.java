package com.codefuel.ringtonemaker.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.bumptech.glide.Glide;
import com.codefuel.ringtonemaker.Models.Banner;
import com.codefuel.ringtonemaker.R;
import com.codefuel.ringtonemaker.ViewPagerClickListener;

import java.util.ArrayList;

public class AutoScrollpagerAdapter extends LoopingPagerAdapter<Banner> {
    private Context context;
    private ArrayList<Banner> imgList;
    private ViewPagerClickListener viewPagerClickListener;
    public AutoScrollpagerAdapter(ViewPagerClickListener viewPagerClickListener, Context context, ArrayList<Banner> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
        this.context = context;
        this.imgList = itemList;
        this.viewPagerClickListener = viewPagerClickListener;
    }

    @SuppressLint("InflateParams")
    @Override
    protected View inflateView() {
        return LayoutInflater.from(context).inflate(R.layout.item_pager,null,false);
    }


    @Override
    protected void bindView(View convertView, int listPosition) {

        convertView.setOnClickListener(v -> {

             if(viewPagerClickListener!=null){
                 viewPagerClickListener.onPageClicked(imgList.get(listPosition).getImage(),imgList.get(listPosition).getUrl());
             }
        });

        ImageView imageView = convertView.findViewById(R.id.ivLogo);
        Glide.with(context).load("http://dnote.xyz/advertise/".concat("banner/").concat(imgList.get(listPosition).getImage())).into(imageView);
    }
}
