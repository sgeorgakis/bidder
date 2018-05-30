package com.bluebannana.bidder.service;

public interface RestService {

    /**
     * Make GET REST request and cast the response
     * to the specified object type
     *
     * @param url the url to make the request
     * @param object the class to cast the response
     * @param variables path variables
     * @param <T> the class to cast
     * @return the response
     */
    <T> T getRequest(String url, Class<T> object, Object... variables);
}
