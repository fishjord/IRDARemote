package com.fishjord.irwidget;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

public class License extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WebView wv = new WebView(this);
		String Pack = getResources().getString(R.string.app_name);
		String License = getResources().getString(R.string.app_license_bsd);
		String text = "<html>" + "<h2>" + this.getClass().getSimpleName() + " for " + Pack + " on Android</h2>"
				+ "<p>" + "	" + License
				+ "</p>" + "</body></html>";
		wv.loadData(text, "text/html; charset=UTF-8", null);
		setContentView(wv);
	}
}
