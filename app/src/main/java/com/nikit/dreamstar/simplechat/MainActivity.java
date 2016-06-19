package com.nikit.dreamstar.simplechat;


import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;

import com.c2call.sdk.pub.activities.SCFriendsFragmentActivity;
import com.c2call.sdk.pub.core.C2CallSdk;
import com.c2call.sdk.pub.core.StartType;
import com.c2call.sdk.pub.facade.SCCoreFacade;


public class MainActivity
        extends SCFriendsFragmentActivity
{

    private AsyncTask<?, ?, ?> _logoutTask;


    @Override
    protected Fragment onCreateFragment()
    {
        return new FriendsFragment();
    }

    @Override
    protected void onDestroy()
    {
        if (_logoutTask != null) {
            _logoutTask.cancel(false);
            _logoutTask = null;
        }

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.menu_profile:
                C2CallSdk.startControl().openProfile(this, null, R.layout.sc_edit_profile, StartType.Activity);
                return true;
            case R.id.menu_board:
                C2CallSdk.startControl().openBoard(this, null, R.layout.sc_board, null, StartType.Activity);
                return true;
            case R.id.menu_exit:
                _logoutTask = new AsyncTask<Void, Void, Void>()
                {
                    @Override
                    protected Void doInBackground(Void... params)
                    {
                        SCCoreFacade.instance().logout();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result)
                    {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);

                        MainActivity.this.finish();
                    }
                }.execute();
                return true;
            default:
                return super.onMenuItemSelected(featureId, item);
        }
    }
}