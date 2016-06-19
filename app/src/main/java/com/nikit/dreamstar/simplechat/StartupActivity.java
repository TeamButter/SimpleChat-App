package com.nikit.dreamstar.simplechat;

import android.app.Activity;
import android.content.Intent;

import com.c2call.sdk.pub.core.C2CallSdk;
import com.c2call.sdk.pub.core.SCExtraData;
import com.c2call.sdk.pub.facade.SCCoreFacade;

public class StartupActivity
        extends Activity
{
    @Override
    protected void onResume()
    {
        super.onResume();
        if (isConnected()) {
            startMainActivity();
            return;
        }
        startLoginActivity();
    }

    private boolean isConnected()
    {
        return SCCoreFacade.instance().isConnectedToService() &&
               SCCoreFacade.instance().isSessionValid();
    }

    private void startMainActivity()
    {
        C2CallSdk.instance().getStartControl().openMain(this);
        finish();
    }

    private void startLoginActivity()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(SCExtraData.BaseFragmentData.EXTRA_DATA_LAYOUT,
                R.layout.sc_login);
        this.startActivity(intent);
        finish();
    }
}
