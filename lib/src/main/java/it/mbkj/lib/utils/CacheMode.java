package it.mbkj.lib.utils;

public enum CacheMode {
    DEFAULT,
    NO_CACHE,
    REQUEST_FAILED_READ_CACHE,
    IF_NONE_CACHE_REQUEST,
    FIRST_CACHE_THEN_REQUEST;

    private CacheMode() {
    }
}
