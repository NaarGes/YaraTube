package com.example.asus.yaratube.ui.login.phone.LoginCode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.asus.yaratube.util.Util;

public class SMSReceiver extends BroadcastReceiver {

    // this listener will do the magic of throwing the extracted OTP to all the bound views
    private static LoginCodeContract.OTPListener otpListener;
    Boolean isSenderTarget;
    String verificationCode;


    // this function is triggered when each time a new SMS is received on device
    @Override
    public void onReceive(Context context, Intent intent) {

        SmsMessage smsMessage;
        String sender;
        String messageBody;

        Bundle data = intent.getExtras();

        Object[] pdus = (Object[]) data.get("pdus");

        for (Object pdu : pdus) {
            smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
            sender = smsMessage.getDisplayOriginatingAddress();
            Log.d("sender", "onReceive: " + sender);

            isSenderTarget = sender.equals(Util.OTP_SENDER_NUMBER);  //Just to fetch otp sent from server

            messageBody = smsMessage.getMessageBody();
            Log.d("sms", "onReceive: " + messageBody);

            verificationCode = messageBody.replaceAll("[^0-9]", "");
            Log.d("otp", "onReceive: " + verificationCode);

            //Pass on the text to our listener.
            if (isSenderTarget) {
                otpListener.onOTPReceived(verificationCode);  // attach value to interface object
            }
        }
    }

    public static void bindListener(LoginCodeContract.OTPListener listener) {
        otpListener = listener;
    }

    public static void unbindListener() {
        otpListener = null;
    }
}