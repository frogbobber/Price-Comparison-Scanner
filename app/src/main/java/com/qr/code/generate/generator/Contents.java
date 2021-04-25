package com.qr.code.generate.generator;

import android.provider.ContactsContract;

public final class Contents {
    private Contents() {
    }

    public static final class Type {

        public static final String UNDEFINED = "UNDEFINED";

        public static final String TEXT = "TEXT_TYPE";

        public static final String EMAIL = "EMAIL_TYPE";

        public static final String PHONE = "PHONE_TYPE";

        public static final String SMS = "SMS_TYPE";
        public static final String MMS = "MMS_TYPE";
        public static final String WEB_URL = "URL_TYPE";
        public static final String WIFI = "WIFI_TYPE";
        public static final String Me_Card = "MeCard_TYPE";
        public static final String V_Card = "VCard_TYPE";
        public static final String Market = "Market_TYPE";
        public static final String Biz_Card = "BizCard_TYPE";


        public static final String CONTACT = "CONTACT_TYPE";


        public static final String LOCATION = "LOCATION_TYPE";

        private Type() {
        }
    }

    public static final String URL_KEY = "URL_KEY";

    public static final String NOTE_KEY = "NOTE_KEY";


    public static final String[] PHONE_KEYS = {
            ContactsContract.Intents.Insert.PHONE, ContactsContract.Intents.Insert.SECONDARY_PHONE,
            ContactsContract.Intents.Insert.TERTIARY_PHONE
    };

    public static final String[] PHONE_TYPE_KEYS = {
            ContactsContract.Intents.Insert.PHONE_TYPE,
            ContactsContract.Intents.Insert.SECONDARY_PHONE_TYPE,
            ContactsContract.Intents.Insert.TERTIARY_PHONE_TYPE
    };

    public static final String[] EMAIL_KEYS = {
            ContactsContract.Intents.Insert.EMAIL, ContactsContract.Intents.Insert.SECONDARY_EMAIL,
            ContactsContract.Intents.Insert.TERTIARY_EMAIL
    };

    public static final String[] EMAIL_TYPE_KEYS = {
            ContactsContract.Intents.Insert.EMAIL_TYPE,
            ContactsContract.Intents.Insert.SECONDARY_EMAIL_TYPE,
            ContactsContract.Intents.Insert.TERTIARY_EMAIL_TYPE
    };
}
