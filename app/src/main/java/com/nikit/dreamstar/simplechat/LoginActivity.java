package com.nikit.dreamstar.simplechat;

import android.app.Fragment;
import com.c2call.sdk.pub.activities.SCLoginFragmentActivity;

public class LoginActivity
        extends SCLoginFragmentActivity
{
    protected Fragment onCreateFragment()
    {
        return LoginFragment.create();
    }
}