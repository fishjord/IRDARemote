package com.fishjord.irwidget.ir;

import java.lang.reflect.*;

import com.fishjord.irwidget.ir.codes.IRCommand;

import android.app.Activity;
import android.widget.Toast;

public class IRDAService implements IRService {
	private Method sendMethod;
	private Object irdaService;

	public IRDAService(Activity activity) {
		irdaService = activity.getApplicationContext().getSystemService("irda");
		Class c = null;
		try {
			c = irdaService.getClass();
		} catch (Exception e) {
			Toast.makeText(activity.getApplicationContext(),
					"Error: Can't get the IrDA service!", Toast.LENGTH_LONG)
				.show();
			irdaService = new DummyIRService();
			c = DummyIRService.class;
		}
		Class p[] = { String.class };
		try {
			sendMethod = c.getMethod("write_irsend", p);
		} catch (NullPointerException e) {
			Toast.makeText(activity.getApplicationContext(),
					"Error sending IR command!", Toast.LENGTH_LONG).show();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendCommand(IRCommand command) {
		try {
			sendMethod.invoke(irdaService, command.toString());
		} catch (NullPointerException e) {
			//ignore
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
