package com.statues.statusdownload.ui.imageslider;


import com.statues.statusdownload.data.model.ImageModel;
import com.statues.statusdownload.ui.base.MvpView;

import java.util.List;

/**
 * Created by shaz on 14/2/17.
 */

public interface ImageSliderView extends MvpView {
    void displayLoadingAnimation(boolean status);
    void displayImageSlider(List<ImageModel> mediaItems, int imageToDisplayPosition);
}
