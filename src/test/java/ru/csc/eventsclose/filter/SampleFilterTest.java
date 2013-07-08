package ru.csc.eventsclose.filter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import junit.framework.TestCase;
import org.junit.Test;


/**
 * author Vladiev Aleksey (avladiev2@gmail.com)
 */


public class SampleFilterTest extends TestCase {

    private static final String SAMPLE_JSON1 = " {\n" +
            "gid: 33439877,\n" +
            "name: 'I like music',\n" +
            "screen_name: 'nnusic',\n" +
            "is_closed: 0,\n" +
            "is_admin: 0,\n" +
            "is_member: 0,\n" +
            "type: 'page',\n" +
            "photo: 'http://cs5906.vk.me/g33439877/e_e8bffd23.jpg',\n" +
            "photo_medium: 'http://cs5906.vk.me/g33439877/d_4a264351.jpg',\n" +
            "photo_big: 'http://cs5906.vk.me/g33439877/a_b8a93ec9.jpg'\n" +
            "}";

    private static final String SAMPLE_JSON2 = "{\n" +
            "gid: 21722746,\n" +
            "name: 'ПУТЕВКИ ВСЕМ, ГОРЯЩИЕ ТУРЫ и СКИДКИ на туризм вс',\n" +
            "screen_name: 'club21722746',\n" +
            "is_closed: 0,\n" +
            "is_admin: 0,\n" +
            "is_member: 0,\n" +
            "type: 'event',\n" +
            "photo: 'http://cs9257.vk.me/g21722746/c_97883540.jpg',\n" +
            "photo_medium: 'http://cs9257.vk.me/g21722746/b_3e836f7e.jpg',\n" +
            "photo_big: 'http://cs9257.vk.me/g21722746/a_fc0f49c0.jpg'\n" +
            "}";


    private static final String SAMPLE_JSON3 = "{\n" +
            "gid: 32682289,\n" +
            "name: 'SCORPIONS.Встреча.29 октября 2013 СПБ! ЛЕДОВЫЙ!',\n" +
            "screen_name: 'scorpionsspb',\n" +
            "is_closed: 0,\n" +
            "is_admin: 0,\n" +
            "is_member: 0,\n" +
            "type: 'event',\n" +
            "photo: 'http://cs405820.vk.me/v405820934/80d2/AG2XAOwuWeI.jpg',\n" +
            "photo_medium: 'http://cs405820.vk.me/v405820934/80d1/IfIihYNXF6o.jpg',\n" +
            "photo_big: 'http://cs405820.vk.me/v405820934/80cf/ft_pMRQOW5I.jpg'\n" +
            "}";

    private final static JsonParser PARSER = new JsonParser();

    @Test
    public void test1() {
        final Filter<JsonObject> filter = new SampleFilter();
        final boolean ans = filter.filter(PARSER.parse(SAMPLE_JSON1).getAsJsonObject());
        assertFalse(ans);
    }


    @Test
    public void test2() {
        final Filter<JsonObject> filter = new SampleFilter();
        final boolean ans = filter.filter(PARSER.parse(SAMPLE_JSON2).getAsJsonObject());
        assertFalse(ans);
    }


    @Test
    public void test3() {
        final Filter<JsonObject> filter = new SampleFilter();
        final boolean ans = filter.filter(PARSER.parse(SAMPLE_JSON3).getAsJsonObject());
        assertTrue(ans);
    }

}
