package com.foobar.simplerss;

/**
 * Created by tom on 5/21/14.
 */
public class FeedEntry
{
    private String title;
    private String description;
    private String url;

    public String toString()
    {
        return title;
    }

    public String getUrl()
    {
        return url;
    }

    public String getDescription()
    {
        return description;
    }
}
