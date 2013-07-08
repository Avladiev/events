package ru.csc.eventsclose.model;

import org.apache.commons.httpclient.methods.GetMethod;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * author Vladiev Aleksey (avladiev2@gmail.com)
 */
public class SampleDataSource implements DataSource<GetMethod> {
    private static final String SITE = "https://api.vk.com/method/groups.search";
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String SITE_CHARSET = "UTF-8";
    private static final String[] SUBSTRING_Q = {"встреча", "в", "на", "за", "кино", "игра"};
    private static final String[] SUBSTRING_Q_ENCODE;
    private static final String ACCESS_TOKEN = "58f02c77da09a6161bc398fb2bd630f334eadd77ab9647621da90e21390a8b7e81d23f7fd3985b0b8be09";


    static {
        SUBSTRING_Q_ENCODE = new String[SUBSTRING_Q.length];
        try {
            for (int i = 0; i < SUBSTRING_Q.length; ++i) {
                SUBSTRING_Q_ENCODE[i] = URLEncoder.encode(SUBSTRING_Q[i], SITE_CHARSET);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterator<GetMethod> iterator() {
        return new GetMethodIterator();
    }


    private class GetMethodIterator implements Iterator<GetMethod> {
        private int curIndex = 0;


        @Override
        public boolean hasNext() {
            return curIndex < SUBSTRING_Q_ENCODE.length;
        }

        @Override
        public GetMethod next() {
            if (hasNext()) {
                  return new GetMethod(SITE+"?q="+ SUBSTRING_Q_ENCODE[curIndex++]+"&count="+1000+"&access_token="+ ACCESS_TOKEN);
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
