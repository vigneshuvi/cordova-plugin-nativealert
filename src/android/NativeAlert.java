package com.cordova.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;


/**
 * This class NativeAlert a string called from JavaScript.
 */
public class NativeAlert extends CordovaPlugin {
    private final Context context;

    public NativeAlert(Context context) {
        this.context = context;
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("showAlert")) {
            if (args.length() > 0) {
                String jsonString = args.getString(0);
                JSONObject json = new JSONObject(jsonString);
                String title = (json.getString("title") != null) ? json.getString("title") : "Alert";
                String message = (json.getString("message") != null) ? json.getString("message") : "";
                String ok = (json.getString("okButton") != null) ? json.getString("okButton") : "Ok";
                String cancel = json.getString("cancelButton");
                cordova.getThreadPool().execute(new Runnable() {
                    public void run() { // Thread-safe.
                        show(title, message, ok, cancel, callbackContext);
                    }
                });
                return true;
            }
        }
        sendPluginResult(null, callbackContext);
        return false;
    }

    private void show(String title, String message, String ok, String cancel, CallbackContext callbackContext) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sendPluginResult(ok, callbackContext);
                        dialog.dismiss();
                    }
                });
        if(cancel != null) {
            alertDialog.setNegativeButton(cancel,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            sendPluginResult(cancel, callbackContext);
                            dialog.dismiss();
                        }
                    });
        }
        alertDialog.show();
    }


    private void sendPluginResult(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Alert Argument was null");
        }
    }
}