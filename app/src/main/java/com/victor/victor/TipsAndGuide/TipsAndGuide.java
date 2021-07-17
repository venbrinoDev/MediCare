package com.victor.victor.TipsAndGuide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.victor.victor.App.Base.Activity.BaseActivity;
import com.victor.victor.R;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.Random;

import im.delight.android.webview.AdvancedWebView;

public class TipsAndGuide extends BaseActivity implements AdvancedWebView.Listener {

    private AdvancedWebView mWebView;
    private DilatingDotsProgressBar progressBar;
    private TextView laaodding_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.push_in_from_right, R.anim.push_out_to_left);

        setContentView(R.layout.tips_and_guide_activity);

        laaodding_content = findViewById(R.id.loading_content);
        laaodding_content.setVisibility(View.VISIBLE);

        progressBar = findViewById(R.id.progress);
        progressBar.showNow();
        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.setMixedContentAllowed(false);
        mWebView.loadUrl(getLink());

        // ...
    }


    public String getLink(){
       Random random = new Random();
       boolean value = random.nextBoolean();
       String[] links ={"https://www.healthline.com/health/pregnancy/safe-exercise-third-trimester#mom-and-baby-health","https://www.healthline.com/nutrition/13-foods-to-eat-when-pregnant#fish-liver-oil"};

       if (value){
           return links[0];
       }else {
           return  links[1];
       }
    }



    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mWebView.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) { return; }
        // ...
        super.onBackPressed();
    }


    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        progressBar.hideNow();
        laaodding_content.setVisibility(View.GONE);
    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        progressBar.hideNow();
        laaodding_content.setText("An Error Occurred "+" \n "+description);
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) { }

    @Override
    public void onExternalPageRequest(String url) { }

}