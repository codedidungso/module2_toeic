package com.example.module2_toeic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    private static final String TAG = "DatabaseUtils";

    private final String TABLE_TOPIC = "tbl_topic";
    private final String TABLE_WORD = "tbl_word";

    private SQLiteDatabase sqLiteDatabase;
    private MyDatabase myDatabase;

    //Singleton
    private static DatabaseUtils databaseUtils;

    public DatabaseUtils(Context context) {
        myDatabase = new MyDatabase(context);
    }

    public static DatabaseUtils getInstance(Context context) {
        if (databaseUtils == null) {
            databaseUtils = new DatabaseUtils(context);
        }
        return databaseUtils;
    }

    public List<TopicModel> getListTopic() {
        sqLiteDatabase = myDatabase.getReadableDatabase();
        List<TopicModel> topicModels = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_TOPIC, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            //read data
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String category = cursor.getString(4);
            String color = cursor.getString(5);
            String lastTime = cursor.getString(6);

            TopicModel topicModel = new TopicModel(id, name, category, color, lastTime);
            topicModels.add(topicModel);
            //next
            cursor.moveToNext();
        }

        Log.d(TAG, "getListTopic: " + topicModels);

        return topicModels;
    }
}
