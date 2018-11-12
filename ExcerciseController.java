package com.example.user.chemistry24.controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.chemistry24.database.DBOpenHelper;
import com.example.user.chemistry24.models.Question;

import java.util.ArrayList;

public class ExcerciseController {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static ExcerciseController instance;

    private ExcerciseController(Context context) {
        this.openHelper = new DBOpenHelper(context);
    }

    public static ExcerciseController getInstance(Context context) {
        if(instance == null)
            instance = new ExcerciseController(context);

        return instance;
    }

    public void open() {
        this.database = openHelper.getReadableDatabase();
    }

    public void close() {
        if(database != null) this.database.close();
    }

    public ArrayList<Question> getTopicQuestion(int idTopic) {
        ArrayList<Question> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM TopicQuestion WHERE idTopic = "+idTopic,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
             Question item = new Question(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),cursor.getInt(8),"");
            list.add(item);
            cursor.moveToNext();
        }

        cursor.close();
        return list;
    }
}

