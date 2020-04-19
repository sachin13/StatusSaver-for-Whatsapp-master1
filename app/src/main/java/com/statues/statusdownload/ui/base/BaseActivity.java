package com.statues.statusdownload.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.statues.statusdownload.TheApplication;

public class BaseActivity extends AppCompatActivity {



    public TheApplication getTheApplication() {
        return ((TheApplication) getApplication());
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }
}