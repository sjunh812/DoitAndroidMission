package org.techtown.rss;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;

public class RssToXml extends AsyncTask<String, RssItem, String> {
    private RssCallback callback;
    private Context context;

    public RssToXml(Context context) {
        this.context = context;

        if(context instanceof RssCallback) {
            callback = (RssCallback)context;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // 스레드 부분
    @Override
    protected String doInBackground(String... strings) {
        String urlStr = strings[0];

        try {
            URL url = new URL(urlStr);

            InputStream inStream = url.openStream();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(inStream, "utf-8");

            int eventType = parser.getEventType();
            RssItem item = null;
            String tag = null;

            while(eventType != XmlPullParser.END_DOCUMENT) {
                switch(eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();

                        if(tag.equals("item")) {
                            item = new RssItem();
                        } else if(tag.equals("title")) {
                            parser.next();
                            if(item != null) {
                                item.title = parser.getText();
                            }
                        } else if(tag.equals("description")) {
                            parser.next();
                            if(item != null) {
                                item.description = parser.getText();
                            }
                        } else if(tag.equals("link")) {
                            parser.next();
                            if(item != null) {
                                item.link = parser.getText();
                            }
                        } else if(tag.equals("author")) {
                            parser.next();
                            if(item != null) {
                                item.author = parser.getText();
                            }
                        } else if(tag.equals("pubDate")) {
                            parser.next();
                            if(item != null) {
                                item.pubDate = parser.getText();
                            }
                        }

                        break;

                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tag = parser.getName();

                        if(tag.equals("item")) {
                            publishProgress(item);
                            item = null;
                        }

                        break;
                }
                eventType = parser.next();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "파싱성공!";
    }

    // 업데이트 부분
    @Override
    protected void onProgressUpdate(RssItem... values) {
        super.onProgressUpdate(values);
        callback.addItem(values[0]);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
}
