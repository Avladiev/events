package ru.csc.eventsclose.filter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.httpclient.methods.GetMethod;
import ru.csc.eventsclose.model.DataDestination;
import ru.csc.eventsclose.model.DataSource;
import ru.csc.eventsclose.model.DownloadData;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * author Vladiev Aleksey (avladiev2@gmail.com)
 */
public class SampleFilter implements Filter<JsonObject> {
    private static final String MEMBER_TYPE = "type";
    private static final String MEMBER_GID = "gid";
    private static final String MEMBER_CITY = "city";

    private static final String VALUE_TYPE = "event";
    private static final int VALUE_CITY = 2;

    private static final DownloadData DOWNLOAD_DATA = new DownloadData();


    @Override
    public boolean filter(final JsonObject element) {

        final JsonElement jsonElement = element.get(MEMBER_TYPE);
        if (jsonElement != null) {
            final String type =jsonElement.getAsString();
            if (type.equalsIgnoreCase(VALUE_TYPE)) {
                final String gid = element.get(MEMBER_GID).getAsString();
                return checkPlace(gid);
            }
        }
        return false;
    }

    private boolean checkPlace(final String gid) {
        final DataSource<GetMethod> dataSource = new DataSource<GetMethod>() {
            @Override
            public Iterator<GetMethod> iterator() {
                return new ItrSource(gid);
            }
        };

        final DataDestinationImpl destination = new DataDestinationImpl();

        try {
            DOWNLOAD_DATA.download(dataSource, destination);
        } catch (IOException e) {
            return false;//todo
        }

        final JsonObject jsonObject = destination.jsonObject;
        final int city = jsonObject.get(MEMBER_CITY).getAsInt();

        return city == VALUE_CITY;
    }


    private class DataDestinationImpl implements DataDestination<String> {
        private JsonObject jsonObject;

        @Override
        public void put(final String result) {
            jsonObject = new JsonParser().parse(result).getAsJsonObject().get("response").getAsJsonArray().get(0).getAsJsonObject();
        }
    }

    private class ItrSource implements Iterator<GetMethod> {
        private boolean hasNest = true;
        private final String gid;

        private ItrSource(final String gid) {
            this.gid = gid;
        }

        @Override
        public boolean hasNext() {
            return hasNest;
        }

        @Override
        public GetMethod next() {
            if (hasNest) {
                hasNest = false;
                return new GetMethod("https://api.vk.com/method/groups.getById?gids=" + gid + "&fields=place,city,country");
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
