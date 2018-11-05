package com.softhinkers.softwiki.repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by adarshmaurya on 03/11/18.
 * www.softhinkers.com all rights reserved
 */
class ArticleDatabaseOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "ArticlesDatabase.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        // define the tables in this database
        db?.createTable("Favorites", true,
            "id" to INTEGER + PRIMARY_KEY,
            "title" to TEXT,
            "url" to TEXT,
            "thumbnailJson" to TEXT)

        db?.createTable("History", true,
            "id" to INTEGER + PRIMARY_KEY,
            "title" to TEXT,
            "url" to TEXT,
            "thumbnailJson" to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // use to upgrade the schema of the table if needed
    }

}