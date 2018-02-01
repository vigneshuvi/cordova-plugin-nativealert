package com.plugin.nativealert;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeAlert extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("showAlert")) { //

           cordova.getThreadPool().execute(new Runnable() {
            
                public void run() {  
                    // Thread-safe.
                    if (leaguecountArray.length()  > 0) {
                        String jsonString = data.getString(0);
                        JSONObject jsonObj = new JSONObject(jsonString);
                        String title = (json.getString("title") != null) ? json.getString("title") : "Alert";
                        String message = (json.getString("message") != null) ? json.getString("message") : "";
                        String ok = (json.getString("okButton") != null) ? json.getString("okButton") : "Ok";
                        String cancel = json.getString("cancelButton");
                        this.show(title, message, ok, cancel, callbackContext);
                    } else {
                        this.callback(null, callbackContext);
                    }
                }

            });
            return true;
        } else {
            return false;

        }
    }

    private void show(String title, String message, String ok, String cancel, CallbackContext callbackContext) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, ok,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                     this.callback(ok, callbackContext);
                    dialog.dismiss();
                }
            });
        if(cancel != null) {
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                         this.callback(cancel, callbackContext);
                        dialog.dismiss();
                    }
            });
        }
        alertDialog.show();
    }

    // Validate the message and send callback accordingly.
    private void callback(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) { 
            callbackContext.success(message);
        } else {
            callbackContext.error("Echo Argument was null");
        }
    }
}
