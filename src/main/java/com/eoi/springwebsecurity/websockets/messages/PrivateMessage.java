package com.eoi.springwebsecurity.websockets.messages;

/**
 * The type Private message.
 */
public class PrivateMessage {

    private String text;

    private String to;

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Gets to.
     *
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets to.
     *
     * @param to the to
     */
    public void setTo(String to) {
        this.to = to;
    }

}
