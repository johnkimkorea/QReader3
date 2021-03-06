package com.evgeniykim.qreader3.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.evgeniykim.qreader3.data.orm.FavoriteORM
import com.evgeniykim.qreader3.data.orm.HistoryORM
import com.evgeniykim.qreader3.domain.History

class DatabaseWrapper(context: Context): SQLiteOpenHelper(context, "qr-code.db", null, 1) {

    private val TAG = javaClass.name
    private val DB_NAME = "qr-code.db"
    private val DB_VERSION = 1

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(HistoryORM().SQL_CREATE_TABLE)
//        db?.execSQL(FavoriteORM().SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(HistoryORM().SQL_DROP_TABLE)
//        db?.execSQL(FavoriteORM().SQL_DROP_TABLE)
    }



}