package com.statues.statusdownload.ui.imageslider.imagedetails;

import com.statues.statusdownload.data.local.FileHelper;
import com.statues.statusdownload.data.model.ImageModel;
import com.statues.statusdownload.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by shaz on 14/2/17.
 */

public class ImageDetailsPresenter extends BasePresenter<ImageDetailsView> {

    private static final String TAG = ImageDetailsPresenter.class.getSimpleName();
    private final FileHelper fileHelper;

    @Inject
    public ImageDetailsPresenter(FileHelper fileHelper) {
        this.fileHelper = fileHelper;
    }

    void setLoadingAnimation(boolean status) {
        getMvpView().displayLoadingAnimation(status);
    }

    void saveMedia(ImageModel imageModel) {
        boolean status = fileHelper.saveMediaToLocalDir(imageModel);
        if (status) {
            getMvpView().displayImageSavedMsg();
        }
    }

    void deleteLocalImage(ImageModel imageModel) {
        boolean status = fileHelper.deleteImageFromLocalDir(imageModel);
        if (status) {
            getMvpView().displayDeleteSuccessMsg();
        }
    }

}
