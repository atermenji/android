package com.github.mobile.core.notification;

import static com.github.mobile.core.notification.IGitHubNotificationConstants.SEGMENT_NOTIFICATIONS;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.IResourceProvider;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.service.GitHubService;

public class NotificationService extends GitHubService {

    private static class NotificationContainer implements
            IResourceProvider<Notification> {

        private List<Notification> notifications;

        /**
         * @see org.eclipse.egit.github.core.IResourceProvider#getResources()
         */
        public List<Notification> getResources() {
            return notifications;
        }
    }

    public NotificationService() {
        super();
    }

    public NotificationService(GitHubClient client) {
        super(client);
    }

    public List<Notification> getNotifications(final boolean all, final boolean participating, final String time)
            throws IOException {

        StringBuilder uri = new StringBuilder(SEGMENT_NOTIFICATIONS);

        PagedRequest<Notification> request = createPagedRequest();

        request.setUri(uri);
        request.setType(NotificationContainer.class);
        return getAll(request);
    }


//    final String encodedQuery = URLEncoder.encode(query, CHARSET_UTF8)
//        .replace("+", "%20") //$NON-NLS-1$ //$NON-NLS-2$
//        .replace(".", "%2E"); //$NON-NLS-1$ //$NON-NLS-2$
//    uri.append('/').append(encodedQuery);
//
//    PagedRequest<SearchRepository> request = createPagedRequest();
//
//    Map<String, String> params = new HashMap<String, String>(2, 1);
//    if (language != null && language.length() > 0)
//        params.put(PARAM_LANGUAGE, language);
//    if (startPage > 0)
//        params.put(PARAM_START_PAGE, Integer.toString(startPage));
//    if (!params.isEmpty())
//        request.setParams(params);
//
//    request.setUri(uri);
//    request.setType(RepositoryContainer.class);
//    return getAll(request);
//}
}
