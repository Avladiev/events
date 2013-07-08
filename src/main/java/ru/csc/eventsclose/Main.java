package ru.csc.eventsclose;


import ru.csc.eventsclose.filter.SampleFilter;
import ru.csc.eventsclose.model.SampleDataDestination;
import ru.csc.eventsclose.model.SampleDataSource;
import ru.csc.eventsclose.model.DownloadData;

import java.io.IOException;

/**
 * author Vladiev Aleksey (avladiev2@gmail.com)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final DownloadData downloadData = new DownloadData();
        downloadData.download(new SampleDataSource(), new SampleDataDestination(new SampleFilter()));
    }
}
