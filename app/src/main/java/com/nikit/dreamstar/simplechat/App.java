package com.nikit.dreamstar.simplechat;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.c2call.lib.androidlog.Ln;
import com.c2call.sdk.pub.affiliate.AffiliateCredentials;
import com.c2call.sdk.pub.core.C2CallSdk;

public class App
        extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();

        //
        // You get these values when creating an app in the C2Call developer area
        // https://www.c2call.com/developer-area.html
        //
        String affid = "CA24AD5A154B4440015";
        String secret = "23ece832b3935ab7b4b6082948991025";

        final AffiliateCredentials credentials = new AffiliateCredentials(affid, getPackageName(), secret);

        C2CallSdk.instance().init(this, credentials);

        //
        // Set our custom start control connect ur phone
        //
        C2CallSdk.instance().setStartControl(new StartControl());


        Ln.enableLogging(true);
    }

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);


        MultiDex.install(this);
    }
}
