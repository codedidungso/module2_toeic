package com.example.module2_toeic;

public class TopicModel {
    int id;
    String name;
    String category;
    String color;
    String lastTime;

    public TopicModel(int id, String name, String category, String color, String lastTime) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.color = color;
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "TopicModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
