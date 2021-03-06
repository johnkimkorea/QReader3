package com.evgeniykim.qreader3.data.orm

import android.content.Context
import android.database.Cursor

interface InterfaceORM<T> {

    fun cursorToObject(cursor: Cursor) : T
    fun add(context: Context, t: T)
    fun clearAll(context: Context)
    fun getAll(context: Context) : List<T>
}