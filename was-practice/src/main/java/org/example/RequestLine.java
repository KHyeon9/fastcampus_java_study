package org.example;

import java.util.Objects;

public class RequestLine {
    private final String method; // GET
    private final String urlPath; // /calculate

    private QueryStrings queryStrings; // operand1=11&operator=*&operand2=55

    /**
     * GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
     */
    public RequestLine(String requestLine) {
        // GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
        String[] tokkens = requestLine.split(" ");
        this.method = tokkens[0];

        String[] urlPathTokkens = tokkens[1].split("\\?");
        this.urlPath = urlPathTokkens[0];

        if (urlPathTokkens.length == 2) {
            this.queryStrings = new QueryStrings(urlPathTokkens[1]);
        }

    }

    public RequestLine(String method, String urlPath, String quertString) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryStrings = new QueryStrings(quertString);
    }

    public boolean isGetRequest() {
        return this.method.equals("GET");
    }

    public boolean matchPath(String requestPath) {
        return this.urlPath.equals(requestPath);
    }

    public QueryStrings getQueryStrings() {
        return this.queryStrings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryStrings, that.queryStrings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, queryStrings);
    }
}
