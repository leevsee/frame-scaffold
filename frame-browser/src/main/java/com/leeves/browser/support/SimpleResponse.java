package com.leeves.browser.support;

/**
 * Description:
 * Package: com.leeves.browser.support
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
public class SimpleResponse {

    public SimpleResponse(Object content) {
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
