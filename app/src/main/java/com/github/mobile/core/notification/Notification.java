package com.github.mobile.core.notification;

import java.io.Serializable;

import org.eclipse.egit.github.core.Repository;

public class Notification implements Serializable {

    private static final long serialVersionUID = -8371449932145139954L;

    public class NotificationSubject implements Serializable {

        private static final long serialVersionUID = 1042577705089718464L;

        private String title;

        private String url;

        private String latestCommentUrl;

        private String type;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getLatestCommentUrl() {
            return latestCommentUrl;
        }

        public void setLatestCommentUrl(String latestCommentUrl) {
            this.latestCommentUrl = latestCommentUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    private String id;

    private Repository repository;

    private NotificationSubject notificationSubject;

    private boolean unread;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public NotificationSubject getNotificationSubject() {
        return notificationSubject;
    }

    public void setNotificationSubject(NotificationSubject notificationSubject) {
        this.notificationSubject = notificationSubject;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }
}
