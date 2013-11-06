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

import com.github.mobile.core.search.SearchUserService;
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

import java.io.IOException;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CollaboratorService;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.DataService;
import org.eclipse.egit.github.core.service.EventService;
import org.eclipse.egit.github.core.service.GistService;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.LabelService;
import org.eclipse.egit.github.core.service.MarkdownService;
import org.eclipse.egit.github.core.service.MilestoneService;
import org.eclipse.egit.github.core.service.OrganizationService;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;
import org.eclipse.egit.github.core.service.WatcherService;

import dagger.Module;
import dagger.Provides;

/**
 * Provide GitHub-API related services
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
public class ServicesModule {

    @Provides
    IssueService issueService(GitHubClient client) {
        return new IssueService(client);
    }

    @Provides
    PullRequestService pullRequestService(GitHubClient client) {
        return new PullRequestService(client);
    }

    @Provides
    UserService userService(GitHubClient client) {
        return new UserService(client);
    }

    @Provides
    SearchUserService searchUserService(GitHubClient client) {
        return new SearchUserService(client);
    }

    @Provides
    GistService gistService(GitHubClient client) {
        return new GistService(client);
    }

    @Provides
    OrganizationService orgService(GitHubClient client) {
        return new OrganizationService(client);
    }

    @Provides
    RepositoryService repoService(GitHubClient client) {
        return new RepositoryService(client);
    }

    @Provides
    User currentUser(UserService userService) throws IOException {
        return userService.getUser();
    }

    @Provides
    CollaboratorService collaboratorService(GitHubClient client) {
        return new CollaboratorService(client);
    }

    @Provides
    MilestoneService milestoneService(GitHubClient client) {
        return new MilestoneService(client);
    }

    @Provides
    LabelService labelService(GitHubClient client) {
        return new LabelService(client);
    }

    @Provides
    EventService eventService(GitHubClient client) {
        return new EventService(client);
    }

    @Provides
    WatcherService watcherService(GitHubClient client) {
        return new WatcherService(client);
    }

    @Provides
    CommitService commitService(GitHubClient client) {
        return new CommitService(client);
    }

    @Provides
    DataService dataService(GitHubClient client) {
        return new DataService(client);
    }

    @Provides
    MarkdownService markdownService(GitHubClient client) {
        return new MarkdownService(client);
    }

    @Provides
    ContentsService contentsService(GitHubClient client) {
        return new ContentsService(client);
    }
}
