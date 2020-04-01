package com.css.gitapi.util.httputil;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/30 16:35
 */
public class HttpGetWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "GET";

    public HttpGetWithBody(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    public HttpGetWithBody(final URI uri) {
        super();
        setURI(uri);
    }

    public HttpGetWithBody() {
        super();
    }

    public String getMethod() {
        return METHOD_NAME;
    }
}
