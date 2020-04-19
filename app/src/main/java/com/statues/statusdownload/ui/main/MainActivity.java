package com.statues.statusdownload.ui.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.statues.statusdownload.R;
import com.statues.statusdownload.ui.base.BaseActivity;
import com.statues.statusdownload.ui.main.recentscreen.RecentPicsFragment;
import com.statues.statusdownload.ui.main.saved.SavedPicsFragment;
import com.statues.statusdownload.util.DialogFactory;
import com.statues.statusdownload.util.PermissionUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView{

    private static final int PERMISSION_REQUEST_CODE_EXT_STORAGE = 10;
    @Inject
    MainPresenter presenter;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    CustomPagerAdapter pagerAdapter;
    private static final int PERMISSION_REQUEST_CODE = 1;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTheApplication().getAppComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Setup toolbar



        // Attach presenter
        presenter.attachView(this);


        presenter.setLoadingAnimation(true);

        // Load images
        if (!PermissionUtil.hasPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestPermission();
        }else {
            presenter.loadRecentAndSavedPics();
        }

        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission())
            {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission1(); // Code for permission
            }
        }




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out   Status Downloader Android App at: https://play.google.com/store/apps/details?id=com.whatsapp.statusdownload");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);


            }
        });




    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission1() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(MainActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private void requestPermission() {

        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionUtil.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE_EXT_STORAGE);
    }

    @Override
    public void displayWelcomeMessage(String msg) {
    }

    @Override
    public void displayLoadingAnimation(boolean status) {
        if (status) {
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayRecentAndSavedPics() {
        presenter.setLoadingAnimation(false);

        // Setup tabs
        pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE_EXT_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                presenter.loadRecentAndSavedPics();
            }else{
                // Permission denied, show rational
                if (PermissionUtil.shouldShowRational(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    DialogFactory
                            .createSimpleOkErrorDialog(this, "Access required", "Permission to access local files is required for the app to perform as intended.",
                                    new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    requestPermission();
                                }
                            })
                            .show();
                }else{
                    // Exit maybe?
                }
            }
        }
    }

    public class CustomPagerAdapter extends FragmentStatePagerAdapter {

        private String[] tabTitles = new String[]{"Recent", "Download"};

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0: return RecentPicsFragment.newInstance();
                case 1: return SavedPicsFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }




















}
