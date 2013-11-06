package com.github.mobile;

import android.app.Activity;
import android.app.Application;

import dagger.ObjectGraph;

/**
 *
 */
public class GitHubApplication extends Application {

    public static GitHubApplication from(Activity activity) {
        return (GitHubApplication) activity.getApplication();
    }

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(new GitHubModule(), new ServicesModule());
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }
}
