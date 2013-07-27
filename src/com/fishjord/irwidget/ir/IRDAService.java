package com.fishjord.irwidget.ir;

import java.lang.reflect.Method;

import com.fishjord.irwidget.ir.codes.IRCommand;

import android.app.Activity;

public class IRDAService implements IRService {
	private Method sendMethod;
	private Object irdaService;
	
	public IRDAService(Activity activity) {
		irdaService = activity.getApplicationContext().getSystemService("irda");
		Class c = irdaService.getClass();
		Class p[] = { String.class };
		try {
			sendMethod = c.getMethod("write_irsend", p);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void sendCommand(IRCommand command) {
		try {
			sendMethod.invoke(irdaService, command.toString());
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
