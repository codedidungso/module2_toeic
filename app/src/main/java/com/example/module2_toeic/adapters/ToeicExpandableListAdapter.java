package com.example.module2_toeic.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.module2_toeic.R;
import com.example.module2_toeic.activities.StudyActivity;
import com.example.module2_toeic.models.CategoryModel;
import com.example.module2_toeic.models.TopicModel;

import java.util.HashMap;
import java.util.List;

public class ToeicExpandableListAdapter extends BaseExpandableListAdapter {

    List<CategoryModel> categoryModels;
    HashMap<String, List<TopicModel>> topicModelHashMap;

    public ToeicExpandableListAdapter(List<CategoryModel> categoryModels, HashMap<String, List<TopicModel>> topicModelHashMap) {
        this.categoryModels = categoryModels;
        this.topicModelHashMap = topicModelHashMap;
    }

    @Override
    public int getGroupCount() {
        return categoryModels.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return topicModelHashMap.get(categoryModels
                .get(groupPosition).getName()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoryModels.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return topicModelHashMap.get(categoryModels.get(groupPosition).getName()).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_category, parent, false);

        CategoryModel categoryModel = (CategoryModel) getGroup(groupPosition);

        TextView tvCategory = convertView.findViewById(R.id.tv_category);
        TextView tvTopics = convertView.findViewById(R.id.tv_topics);
        CardView cvCategory = convertView.findViewById(R.id.cv_category);

        cvCategory.setCardBackgroundColor(Color.parseColor(categoryModel.getColor()));
        tvCategory.setText(categoryModel.getName());

        String topicNames = "";
        for (int i = 0; i < 5; i++) {
            topicNames += topicModelHashMap
                    .get(categoryModel.getName()).get(i).getName();
            if (i != 4) {
                topicNames += ", ";
            }
        }
        tvTopics.setText(topicNames);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_topic, parent, false);

        final TopicModel topicModel = (TopicModel) getChild(groupPosition, childPosition);

        TextView tvName = convertView.findViewById(R.id.tv_topic);
        tvName.setText(topicModel.getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), StudyActivity.class);
                intent.putExtra(StudyActivity.KEY_TOPIC, topicModel);
                parent.getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
