package com.github.vvinogra.iconfinderandroid.data.network;

public class Constants {
    public static final class Endpoint {
        public static final String SEARCH_ICONS = "icons/search";
        public static final String LIST_STYLES = "styles";
        public static final String LIST_CATEGORIES = "categories";
    }

    public static final class SEARCH_ICONS_PARAMS {
        public static final String QUERY = "query";
        public static final String COUNT = "count";
        public static final String OFFSET = "offset";
    }

    public static final class LIST_STYLES_PARAMS {
        public static final String COUNT = "count";
        public static final String AFTER = "after";
    }

    public static final class LIST_CATEGORIES_PARAMS {
        public static final String COUNT = "count";
        public static final String AFTER = "after";
    }
}
