package com.statues.statusdownload.ui.base;

import android.support.v4.app.Fragment;

import com.statues.statusdownload.TheApplication;

public class BaseFragment extends Fragment {

    public TheApplication getTheApplication() {
        return ((TheApplication) getActivity().getApplication());
    }

}