package com.github.mobile.ui.notification;

import android.os.Bundle;
import android.support.v4.content.Loader;

import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
import com.github.mobile.core.notification.Notification;
import com.github.mobile.ui.ItemListFragment;

import java.util.List;

public class NotificationsListFragment extends ItemListFragment<Notification> {

    @Override
    protected int getErrorMessage(Exception exception) {
        return 0;
    }

    @Override
    protected SingleTypeAdapter<Notification> createAdapter(List<Notification> items) {
        return null;
    }

    @Override
    public Loader<List<Notification>> onCreateLoader(int id, Bundle args) {
        return new NotificationsLoader(getActivity(), items);
    }
}
