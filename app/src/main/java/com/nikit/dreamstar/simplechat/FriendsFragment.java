package com.nikit.dreamstar.simplechat;

import android.view.View;

import com.c2call.sdk.pub.core.C2CallSdk;
import com.c2call.sdk.pub.core.StartType;
import com.c2call.sdk.pub.db.data.SCFriendData;
import com.c2call.sdk.pub.fragments.SCFriendsFragment;
import com.c2call.sdk.pub.gui.core.common.SCActivityResultDispatcher;
import com.c2call.sdk.pub.gui.core.controller.SCViewDescription;
import com.c2call.sdk.pub.gui.friendlistitem.controller.IFriendListItemController;
import com.c2call.sdk.pub.gui.friendlistitem.controller.IFriendListItemControllerFactory;
import com.c2call.sdk.pub.gui.friendlistitem.controller.SCFriendListItemController;
import com.c2call.sdk.pub.gui.friendlistitem.controller.SCFriendListItemControllerFactory;
import com.c2call.sdk.pub.gui.friends.controller.IFriendsController;
import com.c2call.sdk.pub.gui.friends.controller.SCFriendsController;

public class FriendsFragment extends SCFriendsFragment
{
    public static FriendsFragment create()
    {
        return new FriendsFragment();
    }

    /**
     * An instance of this factory will be passed the controller of this fragment.
     * It is used to create sub-controllers for each item of the friend list
     */
    public static class FriendItemControllerFactory extends SCFriendListItemControllerFactory
    {

        public FriendItemControllerFactory(SCActivityResultDispatcher resultDispatcher)
        {
            super(resultDispatcher);
        }

        @Override
        public IFriendListItemController onCreateController(View v, SCViewDescription vd, SCFriendData data)
        {
            //
            // If the custom controller gets more complex you should 
            // move this to a named class. For this simple example an anonymous class
            // is sufficient.
            //
            return new SCFriendListItemController(v, vd, data)
            {
                @Override
                public void onMainViewClick(View view)
                {
                    //
                    // If the user clicks this item, then open the chat view 
                    //

                    String userid = getData().getId();
                    C2CallSdk.startControl().openBoard(getContext(), null,  R.layout.sc_board, userid, StartType.Activity);
                }
            };
        }
    }

    @Override
    protected IFriendsController onCreateController(View v, SCViewDescription vd)
    {

        IFriendListItemControllerFactory factory = new FriendItemControllerFactory(null);
        SCViewDescription itemVd = C2CallSdk.instance().getVD().friendListItem();
        
        return new SCFriendsController(v, vd, itemVd, factory, null);
    }
}
