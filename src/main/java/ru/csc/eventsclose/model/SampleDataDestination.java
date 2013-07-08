package ru.csc.eventsclose.model;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.csc.eventsclose.filter.Filter;

import java.util.Map;

/**
 * author Vladiev Aleksey (avladiev2@gmail.com)
 */
public class SampleDataDestination implements DataDestination<String> {
    private static final String RESPONSE = "response";
    private final JsonParser jsonParser = new JsonParser();

    private final Filter<JsonObject> jsonObjectFilter;


    public SampleDataDestination(final Filter<JsonObject> jsonObjectFilter) {
        this.jsonObjectFilter = jsonObjectFilter;
    }

    @Override
    public void put(final String result) {
        JsonElement jsonElement = jsonParser.parse(result);

        /*parse response
     * https://api.vk.com/method/groups.search?q=%D0%B2%D1%81&city=2&
       access_token=58f02c77da09a6161bc398fb2bd630f334eadd77ab9647621da90e21390a8b7e81d23f7fd3985b0b8be09
     * */
        if (jsonElement.isJsonObject()) {
            final JsonObject jsonObject = jsonElement.getAsJsonObject();
            for (final Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(RESPONSE)) {
                    jsonElement = entry.getValue();
                    if (jsonElement.isJsonArray()) {
                        final JsonArray jsonArray = jsonElement.getAsJsonArray();
                        for (final JsonElement jE : jsonArray) {
                            if (jE.isJsonObject()) {
                                final JsonObject jO = jE.getAsJsonObject();
                                if (jsonObjectFilter.filter(jO)) {
                                    doSomething(jO);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void doSomething(final JsonObject jO) {
        System.out.println(jO);
    }
}
