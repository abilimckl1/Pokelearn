package com.ntec.pokelearn;

import android.content.Context;
import android.widget.Toast;

public class SingleToast {

    private static Toast mToast;
	
	//if toast were invoked many times, previous toast will be cancelled
	//show current requested toast
    public static void show(Context context, String text, int duration) {
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(context, text, duration);
        mToast.show();
    }
}