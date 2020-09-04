package com.example.assignment4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String intentAction = intent.getAction();
        if (intentAction != null) {
            String toastMessage = "unknown intent action";
            switch (intentAction){
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power connected!";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power disconnected!";
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    int random = intent.getIntExtra("random", 0);
                    toastMessage = "Custom Broadcast Received" + " " + random * random;
                    break;
                case Intent.ACTION_HEADSET_PLUG:
                    toastMessage = "Headset ";
                    if (intent.getIntExtra("state", -1) == 0) {
                        toastMessage += "unplugged ";
                    } else if (intent.getIntExtra("state", -1) == 1) {
                        toastMessage += "plugged ";
                        if (intent.getIntExtra("microphone", -1) == 1) {
                            toastMessage += "with mic ";
                        } else if (intent.getIntExtra("state", -1) == 0) {
                            toastMessage += "without mic ";
                        }
                    }
                    break;
            }

            //Display the toast.
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
