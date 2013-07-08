package ru.csc.eventsclose.model;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * author Vladiev Aleksey (avladiev2@gmail.com)
 */
public class DownloadData {

    public void download(final DataSource<GetMethod> source, final DataDestination<String> destination) throws IOException {
        final HttpClient httpClient = new HttpClient();
        for (final GetMethod method : source) {
            final int result = httpClient.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                destination.put(method.getResponseBodyAsString());
            }
        }
    }
}
