package com.codefuel.ringtonemaker.Models;

/**
 * Created by USER on 12-10-2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyBanner {

    @SerializedName("banner")
    @Expose
    private List<Banner> banner = null;

    public List<Banner> getBanner() {
        return banner;
    }

    public void setBanner(List<Banner> banner) {
        this.banner = banner;
    }

}