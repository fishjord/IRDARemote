package com.fishjord.irwidget;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

public class About extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WebView wv = new WebView(this);
		String Pack = getResources().getString(R.string.app_name);
		String text = "<html>" + "<h2>" + this.getClass().getSimpleName() + " " + Pack + " for Android</h2>"
				+ "<p>" + "	" + Pack
				+ " for Android is a heavily modified port of ..." + "</p>"
				+ "<p>" + "	The " + Pack + "..." + "</p>" + "</body></html>";
		wv.loadData(text, "text/html", "utf-8");
		setContentView(wv);
	}
}
