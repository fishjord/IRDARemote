package com.fishjord.irwidget.ir;

import android.util.Log;

import com.fishjord.irwidget.ir.codes.IRCommand;

public class OnlyLogIRService implements IRService {

	@Override
	public void sendCommand(IRCommand command) {
		Log.d(this.getClass().getCanonicalName(), command.toString());
	}

}
