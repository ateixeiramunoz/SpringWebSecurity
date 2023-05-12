package com.eoi.springwebsecurity.websockets.messages;


import lombok.Getter;
import lombok.Setter;

/**
 * The type Private message.
 */
@Getter
@Setter
/**
 * The type Private message.
 */
public class PrivateMessage {

    private String notificationID;

    private String text;

    private String to;

    private String from;

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


    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets from.
     *
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets from.
     *
     * @param from the from
     */
    public void setFrom(String from) {
        this.from = from;
    }
}
