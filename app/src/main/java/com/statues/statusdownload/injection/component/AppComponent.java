package com.statues.statusdownload.injection.component;


import com.statues.statusdownload.data.local.FileHelper;
import com.statues.statusdownload.injection.module.AppModule;
import com.statues.statusdownload.ui.imageslider.ImageSliderActivity;
import com.statues.statusdownload.ui.imageslider.imagedetails.ImageDetailsFragment;
import com.statues.statusdownload.ui.main.MainActivity;
import com.statues.statusdownload.ui.main.recentscreen.RecentPicsFragment;
import com.statues.statusdownload.ui.main.saved.SavedPicsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by shaz on 14/2/17.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(RecentPicsFragment fragment);
    void inject(SavedPicsFragment fragment);
    void inject(ImageSliderActivity activity);
    void inject(ImageDetailsFragment fragment);
    FileHelper fileHelper();
}
