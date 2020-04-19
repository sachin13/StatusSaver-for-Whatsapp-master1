package com.statues.statusdownload.ui.main;


import com.statues.statusdownload.ui.base.MvpView;

/**
 * Created by shaz on 14/2/17.
 */

public interface MainView extends MvpView {
    void displayWelcomeMessage(String msg);
    void displayLoadingAnimation(boolean status);
    void displayRecentAndSavedPics();
}
