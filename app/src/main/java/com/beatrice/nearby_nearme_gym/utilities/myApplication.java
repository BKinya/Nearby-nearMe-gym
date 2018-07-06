package com.beatrice.nearby_nearme_gym.utilities;

import android.app.Application;
import android.content.Context;

public class myApplication extends Application {

    private static Context context;

    public void oncreate(){
            context = this.getApplicationContext();
    }

    public static Context getAppContext(){
        return  context;
    }
}
