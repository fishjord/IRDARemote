package com.fishjord.irwidget;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

public class Settings extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		WebView wv = new WebView(this);
		String Pack = getResources().getString(R.string.app_name);
		String text = "<html>" + "<h2>" + this.getClass().getSimpleName() + " " + Pack + " for Android</h2>"
				+ "<p>" + "TODO?" + "</body></html>";
		wv.loadData(text, "text/html", "utf-8");
		setContentView(wv);
	}
}
