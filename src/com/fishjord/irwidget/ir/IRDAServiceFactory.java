package com.fishjord.irwidget.ir;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class IRDAServiceFactory {

    public static IRService produce(Activity activity){

        if(activity.getApplicationContext().getSystemService("irda") != null){
            Log.i(IRDAServiceFactory.class.getCanonicalName(), "Using irda service");
            return new IRDAService(activity);
        }

        if(activity.getApplicationContext().getSystemService("consumer_ir") != null){
            Log.i(IRDAServiceFactory.class.getCanonicalName(), "Using consumer_ir service");
            return new ConsumerIrService(activity);
        }

        Toast.makeText(
                activity.getApplicationContext(),
                "Error: Can't get the IrDA service nor the Consumer_Ir service! Defaulting to log only.", Toast.LENGTH_LONG
        ).show();

        Log.i(IRDAServiceFactory.class.getCanonicalName(), "Using LOG service");
        return new OnlyLogIRService();
    }

}
