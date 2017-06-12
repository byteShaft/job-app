package com.byteshaft.jobapp.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class AppGlobals extends Application {

    private static Context sContext;
    public static final String SERVER_IP = " http://46.101.72.82:8000/";
    public static final String BASE_URL = String.format("%sapi/", SERVER_IP);
    public static final String KEY_TOKEN = "token";
    public static final String KEY_USER_ACTIVE = "user_active";
    public static final String KEY_USER_NAME = "full_name";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_PROFILE_ID = "id";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_DOC_SPECIALITY = "speciality";
    public static final String KEY_DOC_ID = "identity_document";
    public static final String KEY_COLLEGE_ID = "college_id";
    public static final String KEY_DATE_OF_BIRTH = "dob";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_IMAGE_URL = "photo";
    public static final String SERVER_PHOTO_URL = "server_photo_url";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PHONE_NUMBER_PRIMARY = "phone_number_primary";
    public static final String KEY_PHONE_NUMBER_SECONDARY = "phone_number_secondary";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ACCOUNT_TYPE = "account_type";
    public static final String KEY_USER_ID = "id";
    public static final String KEY_AFFILIATE_CLINIC = "affiliate_clinic";
    public static final String KEY_CHAT_STATUS = "available_to_chat";
    public static final String KEY_INSURANCE_CARRIER = "insurance_carrier";
    public static final String KEY_AFFILIATE_CLINIC_ID = "affiliate_clinic";
    public static final String KEY_EMERGENCY_CONTACT = "emergency_contact";
    public static final String KEY_SUBSCRIPTION_TYPE = "subscription_plan";
    public static final String KEY_CONSULTATION_TIME = "consultation_time";
    public static final String KEY_REVIEW_STARS = "review_stars";
    public static final String KEY_SHOW_NEWS = "show_news";
    public static final String KEY_SHOW_NOTIFICATION = "show_notification";
    public static final String KEY_STATE = "state";
    public static final String KEY_CITY = "city";
    public static final String KEY_USER = "user";

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }

    public static SharedPreferences getPreferenceManager() {
        return getContext().getSharedPreferences("shared_prefs", MODE_PRIVATE);
    }

    public static void saveDataToSharedPreferences(String key, String value) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static String getStringFromSharedPreferences(String key) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getString(key, "");
    }


    public static void saveUserActive(boolean value) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean(AppGlobals.KEY_USER_ACTIVE, value).apply();
    }

    public static boolean isUserActive() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getBoolean(AppGlobals.KEY_USER_ACTIVE, false);
    }

    public static void loginState(boolean type) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean(KEY_LOGIN, type).apply();
    }

    public static boolean isLogin() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getBoolean(KEY_LOGIN, false);
    }

    public static void clearSettings() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().clear().apply();
    }

    public static void alertDialog(Activity activity, String title, String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
