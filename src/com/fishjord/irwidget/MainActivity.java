package com.fishjord.irwidget;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.fishjord.irwidget.ir.IRDAService;
import com.fishjord.irwidget.ir.IRDAServiceFactory;
import com.fishjord.irwidget.ir.IRService;
import com.fishjord.irwidget.ir.codes.CodeManager;
import com.fishjord.irwidget.ir.codes.IRButton;
import com.fishjord.irwidget.ir.codes.IRCommand;
import com.fishjord.irwidget.ir.codes.Manufacturer;

public class MainActivity extends Activity {
	private CodeManager codeManager;
	private IRService service;
	private Manufacturer manufacturer;
	/* for dynamic menu creation... */
	private static final int MENU_SETTINGS = 0;
	private static final int MENU_ABOUT = 1;
	private static final int MENU_LICENSE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

	}

	@Override
	protected void onStart() {
		super.onStart();
		try {
			codeManager = CodeManager.getInstance(this.getApplicationContext());
			// service = new DummyIRService();
			service = IRDAServiceFactory.produce(this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		List<String> manufacturers = codeManager.getManufacturers();
		manufacturer = CodeManager.getInstance().getManufacturer(
				manufacturers.get(0));

		Spinner spinner = (Spinner) findViewById(R.id.manufacturer);
		Log.d(this.getClass().getCanonicalName(), "Spinner: " + spinner);

		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, manufacturers);
		spinnerArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spinner.setAdapter(spinnerArrayAdapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				updateSelectedManufacturer((String) parent
						.getItemAtPosition(pos));
				Log.i(this.getClass().getCanonicalName(), "Item selected: "
						+ manufacturer);
			}

			public void onNothingSelected(AdapterView<?> parent) {
				// manufacturer = (String)parent.getItemAtPosition(0);
				Log.i(this.getClass().getCanonicalName(), "No selected: "
						+ manufacturer);
			}

		});
	}

	public void updateSelectedManufacturer(String manny) {
		manufacturer = CodeManager.getInstance().getManufacturer(manny);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.ButtonLayout);
		layout.removeAllViewsInLayout();

		int id = 0;
		int numButtons = 0;

		for (IRButton button : manufacturer.getButtons()) {
			final IRButton thisButton = button;
			// Button newButton = new Button(this);
			// hm, ignores the colors?
			Button newButton = new Button(new ContextThemeWrapper(
					MainActivity.this, R.style.btnStyleOrange));
			newButton.setText(thisButton.getDisplay());

			newButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View view) {
					IRCommand irCommand = thisButton.getCommand();
					Log.d(this.getClass().getCanonicalName(),
							thisButton.getName() + " pushed, sending "
									+ irCommand);
					service.sendCommand(irCommand);
				}
			});

			RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			if (numButtons == 0) {
				relativeParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				relativeParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			} else {
				if (numButtons % 4 == 0) {
					relativeParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
					relativeParams.addRule(RelativeLayout.BELOW, id);
				} else {
					relativeParams.addRule(RelativeLayout.RIGHT_OF, id);
					relativeParams.addRule(RelativeLayout.ALIGN_BOTTOM, id);
				}
			}
			numButtons++;
			id++;
			newButton.setId(id);

			// http://www.mindfreakerstuff.com/2012/09/50-useful-android-custom-button-style-set-1/
			/*
			 * int style = (Integer) null; if(numButtons % 2 == 0)
			 * style=R.style.btnStyleOrange; else
			 * style=R.style.btnStyleBlackpearl;
			 */
			newButton.setLayoutParams(relativeParams);

			layout.addView(newButton);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		// menu.add(0, MENU_ABOUT, 0, "About").setIcon(R.drawable.ic_launcher);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Intents must be placed in the AndroidManifest.xml file ...
		int id = item.getItemId();
		if (id == R.id.action_settings)
			startActivity(new Intent(this, Settings.class));
		else if (id == R.id.action_about)
			startActivity(new Intent(this, About.class));
		else if (id == R.id.action_license)
			startActivity(new Intent(this, License.class));
		else
			return false;

		return true;
	}
}
