package com.example.astro;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class collectiond {
    @SerializedName("collection")
    private collec collection;

    public collec getCollectio() {
        return collection;
    }

    public class collec{
        @SerializedName("items")
        private List<item> item;

        public List<item> getItem() {
            return item;
        }

        public class item{
            @SerializedName("data")
            private List<data> data;

            public List<data> getData() {
                return data;
            }

            public class data{
                @SerializedName("description")
                private String description;

                @SerializedName("media_type")
                private String media_type;

                @SerializedName("title")
                private String title;

                @SerializedName("nasa_id")
                private String nasa_id;

                public String getDescription() {
                    return description;
                }

                public String getMedia_type() {
                    return media_type;
                }

                public String getTitle() {
                    return title;
                }

                public String getNasa_id() {
                    return nasa_id;
                }
            }

        }

    }
}
