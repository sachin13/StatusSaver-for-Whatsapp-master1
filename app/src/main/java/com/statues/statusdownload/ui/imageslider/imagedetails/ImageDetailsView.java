package com.statues.statusdownload.ui.imageslider.imagedetails;


import com.statues.statusdownload.ui.base.MvpView;

/**
 * Created by shaz on 14/2/17.
 */

public interface ImageDetailsView extends MvpView {
    void displayLoadingAnimation(boolean status);
    void displayImageSavedMsg();
    void displayDeleteSuccessMsg();
}
