package com.eoi.springwebsecurity.websockets.messages;

/**
 * The type Greeting.
 */
public class Greeting {

    private String content;

    /**
     * Instantiates a new Greeting.
     */
    public Greeting() {
    }

    /**
     * Instantiates a new Greeting.
     *
     * @param content the content
     */
    public Greeting(String content) {
        this.content = content;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }

}
