/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.mobile;

import android.content.Context;

import com.github.mobile.accounts.AccountClient;
import com.github.mobile.accounts.AccountScope;
import com.github.mobile.accounts.GitHubAccount;
import com.github.mobile.core.commit.CommitStore;
import com.github.mobile.core.gist.GistStore;
import com.github.mobile.core.issue.IssueStore;
import com.github.mobile.persistence.OrganizationRepositories;
import com.github.mobile.sync.SyncCampaign;
import com.github.mobile.ui.code.RepositoryCodeFragment;
import com.github.mobile.ui.comment.CreateCommentActivity;
import com.github.mobile.ui.comment.RenderedCommentFragment;
import com.github.mobile.ui.commit.CommitCompareListFragment;
import com.github.mobile.ui.commit.CommitCompareViewActivity;
import com.github.mobile.ui.commit.CommitDiffListFragment;
import com.github.mobile.ui.commit.CommitFileViewActivity;
import com.github.mobile.ui.commit.CommitListFragment;
import com.github.mobile.ui.commit.CommitViewActivity;
import com.github.mobile.ui.gist.GistFileFragment;
import com.github.mobile.ui.gist.GistFilesViewActivity;
import com.github.mobile.ui.gist.GistFragment;
import com.github.mobile.ui.gist.GistsViewActivity;
import com.github.mobile.ui.gist.MyGistsFragment;
import com.github.mobile.ui.gist.PublicGistsFragment;
import com.github.mobile.ui.gist.StarredGistsFragment;
import com.github.mobile.ui.issue.AssigneeDialogFragment;
import com.github.mobile.ui.issue.DashboardIssueFragment;
import com.github.mobile.ui.issue.EditIssueActivity;
import com.github.mobile.ui.issue.EditIssuesFilterActivity;
import com.github.mobile.ui.issue.FilterListFragment;
import com.github.mobile.ui.issue.FiltersViewActivity;
import com.github.mobile.ui.issue.IssueBrowseActivity;
import com.github.mobile.ui.issue.IssueFragment;
import com.github.mobile.ui.issue.IssueSearchActivity;
import com.github.mobile.ui.issue.IssuesFragment;
import com.github.mobile.ui.issue.IssuesViewActivity;
import com.github.mobile.ui.issue.LabelsDialogFragment;
import com.github.mobile.ui.issue.SearchIssueListFragment;
import com.github.mobile.ui.ref.BranchFileViewActivity;
import com.github.mobile.ui.repo.RepositoryContributorsActivity;
import com.github.mobile.ui.repo.RepositoryContributorsFragment;
import com.github.mobile.ui.repo.RepositoryListFragment;
import com.github.mobile.ui.repo.RepositoryViewActivity;
import com.github.mobile.ui.repo.UserRepositoryListFragment;
import com.github.mobile.ui.search.SearchRepositoryListFragment;
import com.github.mobile.ui.search.SearchUserListFragment;
import com.github.mobile.ui.user.HomeActivity;
import com.github.mobile.ui.user.MembersFragment;
import com.github.mobile.ui.user.MyFollowersFragment;
import com.github.mobile.ui.user.MyFollowingFragment;
import com.github.mobile.ui.user.OrganizationNewsFragment;
import com.github.mobile.ui.user.UserCreatedNewsFragment;
import com.github.mobile.ui.user.UserFollowersFragment;
import com.github.mobile.ui.user.UserReceivedNewsFragment;
import com.github.mobile.ui.user.UserViewActivity;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import javax.inject.Named;
import javax.inject.Provider;
import java.io.File;
import java.lang.ref.WeakReference;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.GistService;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.PullRequestService;

import dagger.Module;
import dagger.Provides;

/**
 * Main module provide services and clients
 */
@Module(
    injects = {
        HomeActivity.class,
        UserViewActivity.class,
        RepositoryContributorsActivity.class,
        RepositoryViewActivity.class,
        BranchFileViewActivity.class,
        CreateCommentActivity.class,
        IssueBrowseActivity.class,
        IssueSearchActivity.class,
        IssuesViewActivity.class,
        EditIssueActivity.class,
        EditIssuesFilterActivity.class,
        FiltersViewActivity.class,
        GistFilesViewActivity.class,
        GistsViewActivity.class,
        CommitCompareViewActivity.class,
        CommitFileViewActivity.class,
        CommitViewActivity.class,
        UserFollowersFragment.class,
        UserFollowersFragment.class,
        UserReceivedNewsFragment.class,
        UserCreatedNewsFragment.class,
        MyFollowersFragment.class,
        MyFollowingFragment.class,
        MembersFragment.class,
        OrganizationNewsFragment.class,
        SearchRepositoryListFragment.class,
        SearchUserListFragment.class,
        RepositoryContributorsFragment.class,
        RepositoryListFragment.class,
        UserRepositoryListFragment.class,
        IssueFragment.class,
        IssuesFragment.class,
        SearchIssueListFragment.class,
        FilterListFragment.class,
        DashboardIssueFragment.class,
        AssigneeDialogFragment.class,
        GistFileFragment.class,
        GistFragment.class,
        MyGistsFragment.class,
        PublicGistsFragment.class,
        StarredGistsFragment.class,
        CommitCompareListFragment.class,
        CommitDiffListFragment.class,
        CommitListFragment.class,
        RenderedCommentFragment.class,
        RepositoryCodeFragment.class
    }
)
public class GitHubModule {

    private WeakReference<IssueStore> issues;

    private WeakReference<GistStore> gists;

    private WeakReference<CommitStore> commits;

//    @Override
//    protected void configure() {
//        install(new ServicesModule());
//        install(new FactoryModuleBuilder().build(SyncCampaign.Factory.class));
//        install(new FactoryModuleBuilder()
//                .build(OrganizationRepositories.Factory.class));
//        install(AccountScope.module());
//    }

    @Provides
    GitHubClient client(Provider<GitHubAccount> accountProvider) {
        return new AccountClient(accountProvider);
    }

    @Provides
    @Named("cacheDir")
    File cacheDir(Context context) {
        return new File(context.getFilesDir(), "cache");
    }

    @Provides
    IssueStore issueStore(IssueService issueService,
            PullRequestService pullService) {
        IssueStore store = issues != null ? issues.get() : null;
        if (store == null) {
            store = new IssueStore(issueService, pullService);
            issues = new WeakReference<IssueStore>(store);
        }
        return store;
    }

    @Provides
    GistStore gistStore(GistService service) {
        GistStore store = gists != null ? gists.get() : null;
        if (store == null) {
            store = new GistStore(service);
            gists = new WeakReference<GistStore>(store);
        }
        return store;
    }

    @Provides
    CommitStore commitStore(CommitService service) {
        CommitStore store = commits != null ? commits.get() : null;
        if (store == null) {
            store = new CommitStore(service);
            commits = new WeakReference<CommitStore>(store);
        }
        return store;
    }
}
