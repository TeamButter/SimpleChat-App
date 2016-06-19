package com.nikit.dreamstar.simplechat;

import android.os.Bundle;
import android.view.View;

import com.c2call.sdk.pub.core.C2CallSdk;
import com.c2call.sdk.pub.core.StartType;
import com.c2call.sdk.pub.fragments.SCLoginFragment;

public class LoginFragment
        extends SCLoginFragment
{
    public static LoginFragment create()
    {
        return new LoginFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        
        final View registerButton =  getActivity().findViewById(R.id.sc_login_btn_register);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                C2CallSdk.startControl().openRegister(getActivity(), null, R.layout.sc_register, null, StartType.FragmentActivity);
            }
        });
    }
}