package com.github.mobile.ui.notification;

import android.os.Bundle;

import com.github.mobile.R;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;

public class NotificationsViewActivity extends RoboSherlockFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notifications);
    }
}
