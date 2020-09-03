package com.example.astro;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class obj {
    @SerializedName("collection")
    private Collection collection;

    public Collection getCollectio() {
        return collection;
    }

    public class Collection{
        @SerializedName("items")
        private List<items> item;

        public List<items> getItem() {
            return item;
        }

        public class items {

            @SerializedName("href")
            String href;

            public String getHref() {
                return href;
            }
        }
    }
}
