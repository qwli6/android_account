package org.lqwit.android.account.db;

/**
 * Created by liqiwen on 2017/11/2.
 */

import android.provider.BaseColumns;

/**
 * The contract userd for the db to save the fundflow locally.
 */
public final class PersistenceContract {

    //To prevent someone from accidentally instantiating the contract class,
    //give it an empty constructor
    private PersistenceContract(){

    }

    public static abstract class FundFlowEntry implements BaseColumns{
        public static final String TABLE_NAME = "fundflows";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
    }

    public static abstract class TypeEntry implements BaseColumns{
        public static final String TABLE_NAME = "account_type";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PIC_NAME = "pic_name";
        public static final String COLUMN_NAME_TYPE = "type";
    }
}
