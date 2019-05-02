package com.codefuel.ringtonemaker.Dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codefuel.ringtonemaker.R;

public class AdsViewDialogFragment extends DialogFragment {

    private String url;

   public static AdsViewDialogFragment newInstance(String url) {

        AdsViewDialogFragment adsViewDialogFragment = new AdsViewDialogFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("url", url);
        adsViewDialogFragment.setArguments(args);

        return adsViewDialogFragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.url = getArguments().getString("url");
    }

    @Override
    public void onResume() {
        super.onResume();

        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_view_ads, container, false);


        ImageView ivAds = v.findViewById(R.id.ivAds);
        if (getActivity() != null) {
            Glide.with(getActivity()).load(this.url).into(ivAds);
        }


        return v;
    }
}
