package com.example.module2_toeic.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.module2_toeic.models.CategoryModel;
import com.example.module2_toeic.models.TopicModel;

import java.util.ArrayList;
import java.util.HashMap;
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

    public List<CategoryModel> getListCategory(List<TopicModel> topicModels) {
        List<CategoryModel> categoryModelList = new ArrayList<>();
        for (int i = 0; i < topicModels.size(); i = i + 5) {
            CategoryModel categoryModel = new CategoryModel(
                    topicModels.get(i).getCategory(),
                    topicModels.get(i).getColor());
            categoryModelList.add(categoryModel);
        }
        Log.d(TAG, "getListCategory: " + categoryModelList);
        return categoryModelList;
    }

    public HashMap<String, List<TopicModel>> getHashMapTopic(List<TopicModel> topicModels,
                                                             List<CategoryModel> categoryModels) {
        HashMap<String, List<TopicModel>> hashMap = new HashMap<>();

        //add 10 list
        for (int i = 0; i < categoryModels.size(); i++) {
            //i = 9
            int positionTopic = i * 5; //45
            // == list.add(T)
            // hashmap.put(key - category name, value - list: 5 topic in category)
            hashMap.put(
                    categoryModels.get(i).getName(),
                    topicModels.subList(positionTopic, positionTopic + 5)); //45 -> 50
            Log.d(TAG, "getHashMapTopic: ----" + categoryModels.get(i).getName());
            Log.d(TAG, "getHashMapTopic: " + hashMap.get(categoryModels.get(i).getName()));
        }

        return hashMap;
    }
}
