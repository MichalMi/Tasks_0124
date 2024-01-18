package com.gitlab.rmarzec.model;

public class YTTile {
    String title;
    String channel;
    String length;

    public YTTile(String title, String channelName, String duration) {
        this.title = title;
        this.channel = channelName;
        this.length = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
