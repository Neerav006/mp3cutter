package com.codefuel.ringtonemaker.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.LinearLayout;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.asksira.loopingviewpager.LoopingViewPager;
import com.codefuel.ringtonemaker.Adapters.AutoScrollpagerAdapter;
import com.codefuel.ringtonemaker.Dialogs.AdsViewDialogFragment;
import com.codefuel.ringtonemaker.Models.Banner;
import com.codefuel.ringtonemaker.Models.MyBanner;
import com.codefuel.ringtonemaker.R;
import com.codefuel.ringtonemaker.ViewPagerClickListener;
import com.codefuel.ringtonemaker.common.RetrofitClient;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LoopingViewPager loopingViewPager;
    private GetBanner getBanner;
    private ArrayList<String> XMENArray = new ArrayList<>();
    private AdView mAdView;
    private LinearLayout llRingToneCreator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llRingToneCreator = findViewById(R.id.llRingToneCreator);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(MainActivity.this, "ca-app-pub-3940256099942544~3347511713");


        mAdView = findViewById(R.id.bannerAd);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Home");

        loopingViewPager = findViewById(R.id.vpAds);
        getBanner = RetrofitClient.getClient("http://dnote.xyz/advertise/").create(GetBanner.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RingdroidSelectActivity.class);
                startActivity(i);

            }
        });

        llRingToneCreator.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, RingdroidSelectActivity.class);
            startActivity(i);
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        /**
         *   Banner api call
         */

        getBanner.getBanner().enqueue(new Callback<MyBanner>() {
            @Override
            public void onResponse(@NonNull Call<MyBanner> call, @NonNull Response<MyBanner> response) {

                if (response.isSuccessful()) {


                    assert response.body() != null;
                    ArrayList<Banner> bannerArrayList = (ArrayList<Banner>) response.body().getBanner();

                    if (bannerArrayList != null && bannerArrayList.size() > 0) {

                        for (int i = 0; i < bannerArrayList.size(); i++) {
                            XMENArray.add("http://dnote.xyz/advertise/".concat("banner/").concat(bannerArrayList.get(i).getImage()));
                        }


                        final AutoScrollpagerAdapter autoScrollpagerAdapter =
                                new AutoScrollpagerAdapter((url, webSite) -> {

                                    if (webSite != null && webSite.startsWith("http")) {

                                        Intent i = new Intent(Intent.ACTION_VIEW);
                                        i.setData(Uri.parse(webSite));
                                        startActivity(i);

                                    }


//                                    // call the dialog fragment
//                                    AdsViewDialogFragment adsViewDialogFragment = AdsViewDialogFragment
//                                            .newInstance(url);
//                                    adsViewDialogFragment.show(getSupportFragmentManager(), "dialog");


                                }, MainActivity.this, bannerArrayList, true);


                        loopingViewPager.setAdapter(autoScrollpagerAdapter);
                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<MyBanner> call, @NonNull Throwable t) {

            }
        });


        /**
         *  End banner api call
         */

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about_us) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this, AboutDevActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * Get Banner from server
     */

    interface GetBanner {

        @POST("Banner/bannerlistapi/")
        Call<MyBanner> getBanner();

    }

}
