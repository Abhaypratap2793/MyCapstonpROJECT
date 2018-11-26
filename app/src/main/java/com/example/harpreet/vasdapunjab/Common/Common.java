package com.example.harpreet.vasdapunjab.Common;

import com.example.harpreet.vasdapunjab.Model.Question;
import com.example.harpreet.vasdapunjab.Model.User;

import java.util.ArrayList;
import java.util.List;

public class Common  {

    public static String categoryId;
    public static User currentUser;
    public static List<Question> questionList = new ArrayList<>();

    public Common() {
    }

    public static String getCategoryId() {
        return categoryId;
    }

    public static void setCategoryId(String categoryId) {
        Common.categoryId = categoryId;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Common.currentUser = currentUser;
    }

    public static List<Question> getQuestionList() {
        return questionList;
    }

    public static void setQuestionList(List<Question> questionList) {
        Common.questionList = questionList;
    }
}
