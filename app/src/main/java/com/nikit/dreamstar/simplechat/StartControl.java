package com.nikit.dreamstar.simplechat;

import android.app.Activity;
import android.content.Intent;

import com.c2call.sdk.pub.core.SCStartControl;

public class StartControl
        extends SCStartControl
{
    @Override
    public boolean openMain(Activity activity)
    {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        return true;
    }
}