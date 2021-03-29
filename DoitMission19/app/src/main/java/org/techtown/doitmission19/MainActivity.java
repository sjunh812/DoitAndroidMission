package org.techtown.doitmission19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String LOG = "MainActivity";

    private EditText editText;
    private TextView textView;
    private WebView webView;
    private WebSettings settings;

    public static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(this);
        }

        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.textView);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlStr = editText.getText().toString();
                request(urlStr);
            }
        });

        webView = (WebView)findViewById(R.id.webView);
        settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    private void request(String urlStr) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                urlStr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
                        Log.d(LOG, "응답 성공!!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(LOG, "에러 발생 : " + error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };

        request.setShouldCache(false);
        requestQueue.add(request);
    }

    private void processResponse(String response) {
        //Gson gson = new Gson();
        println(response);
        startWebView(response);
    }

    private void println(String data) {
        textView.append(data + "\n");
    }

    private void startWebView(String data) {
        //webView.loadData(data, "text/html", "UTF8");
        webView.loadUrl(editText.getText().toString());
    }
}