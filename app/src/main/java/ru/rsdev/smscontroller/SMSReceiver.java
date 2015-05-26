package ru.rsdev.smscontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class SMSReceiver extends BroadcastReceiver {

    private static final String queryString = "@echo";
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    public void onReceive(Context _context, Intent _intent) {
        if (_intent.getAction().equals(SMS_RECEIVED)) {
            SmsManager sms = SmsManager.getDefault();
            Bundle bundle = _intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++)
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                for (SmsMessage message : messages) {
                    String msg = message.getMessageBody();
                    String to = message.getOriginatingAddress();


                    Toast.makeText(_context,"Пришло новое сообщение с текстом " +
                            msg,
                            Toast.LENGTH_LONG).show();

                    /*
                    if (msg.toLowerCase().startsWith(queryString)) {
                        String out = msg.substring(queryString.length());
                        sms.sendTextMessage(to, null, out, null, null);
                    }
                    */
                }
            }
        }
    }

}
