package com.example.cacheit;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsListener extends BroadcastReceiver {
    DatabaseHelper db;
    String cc1, cc2, cc3, cc4;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                Log.d("smsMessage", smsMessage.toString());
                String messageBody = smsMessage.getMessageBody();
                smsMessage.getOriginatingAddress();
                Log.d("SMS received ", messageBody);
                Pattern regExSender = Pattern.compile("[a-zA-Z0-9]{2}-[a-zA-Z0-9]{6}");
                Pattern regExamount
                        = Pattern.compile("(?i)(?:(?:RS|INR|MRP)\\.?\\s?)(\\d+(:?\\,\\d+)?(\\,\\d+)?(\\.\\d{1,2})?)");
                Pattern regExName
                        = Pattern.compile("(?i)(?:\\sat\\s|in\\*)([A-Za-z0-9]*\\s?-?\\s?[A-Za-z0-9]*\\s?-?\\.?)");
                Pattern regExCard
                        = Pattern.compile("(?i)(?:\\smade on|ur|made a\\s|in\\*)([A-Za-z]*\\s?-?\\s[A-Za-z]*\\s?-?\\s[A-Za-z]*\\s?-?)");
                SharedPreferences prfs = context.getSharedPreferences("UserAccount", Context.MODE_PRIVATE);
                String name = prfs.getString("name", null);
                if (prfs.contains("cc1")) {
                    cc1 = prfs.getString("cc1", null);
                    Log.d("cc1", cc1);
                }
                if (prfs.contains("cc2")) {
                    cc2 = prfs.getString("cc2", null);
                    Log.d("cc2", cc2);
                }
                if (prfs.contains("cc3")) {
                    cc3 = prfs.getString("cc3", null);
                    Log.d("cc3", cc3);
                }
                if (prfs.contains("cc4")) {
                    cc4 = prfs.getString("cc4", null);
                    Log.d("cc4", cc4);
                }

                Matcher m = regExamount.matcher(messageBody);
                Matcher muser = regExName.matcher(messageBody);
                if (cc1 != null && messageBody.contains(cc1) && m.find() && muser.find()) {
                    String amount = null;
                    try {
                        Log.e("amount_value= ", "" + m.group(0));
                        amount = (m.group(0).replaceAll("inr", ""));
                        amount = amount.replaceAll("rs", "");
                        amount = amount.replaceAll("Rs.", "");
                        amount = amount.replaceAll("Rs", "");
                        amount = amount.replaceAll("inr", "");
                        amount = amount.replaceAll(" ", "");
                        amount = amount.replaceAll(",", "");
                        Log.d("amount", amount);
                        Toast.makeText(context, amount, Toast.LENGTH_SHORT).show();
                        Log.e("User Name: ", "" + muser.group(0));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                    User user = new User();
                    user.setAmount(amount);
                    user.setCard(cc1);
                    user.setPlace(muser.group(0));
                    Log.d("UserInRealm", user.
                            getDate() + " " + user.getAmount() + " " + user.getCard());
                    databaseHelper.addUser(user);
                }
                if (cc2 != null && messageBody.contains(cc2) && m.find() && muser.find()) {
                    String amount = null;
                    try {
                        Log.e("amount_value= ", "" + m.group(0));
                        amount = (m.group(0).replaceAll("inr", ""));
                        amount = amount.replaceAll("rs", "");
                        amount = amount.replaceAll("inr", "");
                        amount = amount.replaceAll(" ", "");
                        amount = amount.replaceAll(",", "");
                        Log.d("amount", amount);
                        Toast.makeText(context, amount, Toast.LENGTH_SHORT).show();
                        Log.e("User Name: ", "" + muser.group(0));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                    User user = new User();
                    user.setAmount(amount);
                    user.setCard(cc2);
                    user.setPlace(muser.group(0));
                    Log.d("UserInRealm", user.
                            getDate() + " " + user.getAmount() + " " + user.getCard());
                    databaseHelper.addUser(user);
                }
                if (cc3 != null && messageBody.contains(cc3) && m.find() && muser.find()) {
                    String amount = null;
                    try {
                        Log.e("amount_value= ", "" + m.group(0));
                        amount = (m.group(0).replaceAll("inr", ""));
                        amount = amount.replaceAll("rs", "");
                        amount = amount.replaceAll("inr", "");
                        amount = amount.replaceAll(" ", "");
                        amount = amount.replaceAll(",", "");
                        Log.d("amount", amount);
                        Toast.makeText(context, amount, Toast.LENGTH_SHORT).show();
                        Log.e("User Name: ", "" + muser.group(0));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                    User user = new User();
                    user.setAmount(amount);
                    user.setCard(cc3);
                    user.setPlace(muser.group(0));
                    Log.d("UserInRealm", user.
                            getDate() + " " + user.getAmount() + " " + user.getCard());
                    databaseHelper.addUser(user);
                }
                if (cc4 != null && messageBody.contains(cc4) && m.find() && muser.find()) {
                    String amount = null;
                    try {
                        Log.e("amount_value= ", "" + m.group(0));
                        amount = (m.group(0).replaceAll("inr", ""));
                        amount = amount.replaceAll("rs", "");
                        amount = amount.replaceAll("inr", "");
                        amount = amount.replaceAll(" ", "");
                        amount = amount.replaceAll(",", "");
                        Log.d("amount", amount);
                        Toast.makeText(context, amount, Toast.LENGTH_SHORT).show();
                        Log.e("User Name: ", "" + muser.group(0));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                    User user = new User();
                    user.setAmount(amount);
                    user.setCard(cc4);
                    user.setPlace(muser.group(0));
                    Log.d("UserInRealm", user.
                            getDate() + " " + user.getAmount() + " " + user.getCard());
                    databaseHelper.addUser(user);
                }
               /* Matcher msender = regExSender.matcher(smsMessage.getOriginatingAddress());
                if (msender.find()) {
                    try {
                        Log.e("sender= ", "" + msender.group(0));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/
                // Find instance of pattern matches

               /* String type;
                if (messageBody.contains("food")) {
                    type = "Food";
                } else if (messageBody.contains("clothes")) {
                    type = "Clothes";
                } else {
                    type = "Entertainment";
                }
                db = new DatabaseHelper(context);
                User user = new User();
                user.setAmount("66");
                user.setCard("8769");
                user.setType(type);
                db.addUser(user);*/


               /* Matcher mcard = regExCard.matcher(messageBody);
                if (mcard.find()) {
                    try {
                        Log.e("Card No: ", "" + mcard.group(0));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/


            }
  /*      ContentResolver contentResolver = context.getContentResolver();
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = contentResolver.query(uri, null, null, null);
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.d("cursorName", cursor.getColumnName(i).toString());
        }
        cursor.close();*/
        }
    }
}