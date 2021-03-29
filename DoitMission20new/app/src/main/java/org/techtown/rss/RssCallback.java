package org.techtown.rss;

public interface RssCallback {
    public void addItem(RssItem item);
    public void returnToDetailActivity(RssItem item);
}
