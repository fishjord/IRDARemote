package com.fishjord.irwidget.ir;

import android.app.Activity;
import android.hardware.ConsumerIrManager;
import com.fishjord.irwidget.ir.IRDAService;
import com.fishjord.irwidget.ir.codes.IRCommand;

public class ConsumerIrService implements IRService{

    private final ConsumerIrManager consumerIr;

    public ConsumerIrService(Activity activity){
        consumerIr = (android.hardware.ConsumerIrManager) activity.getApplicationContext().getSystemService("consumer_ir");
    }

    @Override
    public void sendCommand(IRCommand command) {
        consumerIr.transmit(command.getFrequency(), command.getOnOffs());
    }
}
