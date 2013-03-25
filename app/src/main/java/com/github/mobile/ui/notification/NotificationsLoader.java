package com.github.mobile.ui.notification;

import android.content.Context;
import android.util.Log;

import com.github.mobile.ThrowableLoader;
import com.github.mobile.core.notification.Notification;
import com.github.mobile.core.notification.NotificationService;
import com.google.inject.Inject;

import java.util.List;

public class NotificationsLoader extends ThrowableLoader<List<Notification>> {

    private static final String TAG = "NotificationsLoader";

    @Inject
    private NotificationService service;

    /**
     * Create loader for context and seeded with initial data
     *
     * @param context
     * @param data
     */
    public NotificationsLoader(Context context,
        List<Notification> data) {
        super(context, data);
    }

    @Override
    public List<Notification> loadData() throws Exception {
        List<Notification> notifications = service.getNotifications(false, false, null);
        Log.v(TAG, "notifications size : " + notifications.size());
        for (Notification notification : notifications) {
            Log.v(TAG, "name = " + notification.getNotificationSubject());
        }

        return notifications;
    }
}
