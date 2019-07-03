package com.fanava.rally.Utils.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {
    public Helper( Context context) {

        super( context, "RallyDB", null, 1);



        //first init the db libraries with the context
        //  Database_path = "/data/data/" + context.getPackageName() + "/databases/";
        // SQLiteDatabase.loadLibs(mContext);

        //mDataBase = SQLiteDatabase.openDatabase(mPath, "123",null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        //   mDatabase = com.sqlitecrypt.database.SQLiteDatabase.openDatabase(Database_path, "3776338@@", null, com.sqlitecrypt.database.SQLiteDatabase.OPEN_READWRITE);



    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
