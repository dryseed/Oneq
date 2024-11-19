package com.example.oneq10.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

object DatabaseProvider {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context:Context): AppDatabase{
        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "Oneq_database"
            ).addCallback(object : RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase){
                    super.onCreate(db)
                    executeInitSql(context,db)
                }
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    val shouldReinitialize = readConfigFromXml(context)
                    if (shouldReinitialize) {
                        executeInitSql(context, db)
                    }
                }
            })
            .fallbackToDestructiveMigration()
            .build()
            INSTANCE = instance
            instance
        }
    }

    private fun executeInitSql(context: Context, db: SupportSQLiteDatabase) {
        val sql = context.assets.open("init.sql").bufferedReader().use { it.readText() }
        sql.split(";").forEach { statement ->
            if (statement.isNotBlank()) {
                db.execSQL(statement.trim())
            }
        }
    }

    private fun readConfigFromXml(context: Context): Boolean {
        val assetManager = context.assets
        val inputStream = assetManager.open("appConfig.xml")
        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        parser.setInput(inputStream, null)
        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && parser.name == "bool") {
                val name = parser.getAttributeValue(null, "name")
                if (name == "sqlReload") {
                    return parser.nextText().toBoolean()
                }
            }
            eventType = parser.next()
        }
        return false
    }

}