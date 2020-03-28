package com.example.module2_toeic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.module2_toeic.R;
import com.example.module2_toeic.models.TopicModel;

public class StudyActivity extends AppCompatActivity {
    private static final String TAG = "StudyActivity";

    public static String KEY_TOPIC = "key_topic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        TopicModel topicModel = (TopicModel) getIntent().getSerializableExtra(KEY_TOPIC);
        Log.d(TAG, "onCreate: " + topicModel.getName());
    }
}
