package com.ryucant.testcodingandroid_indodana.response;

import com.google.gson.annotations.SerializedName;
import com.ryucant.testcodingandroid_indodana.model.SearchUsersData;

import java.util.List;

public class SearchUsers {

    @SerializedName("items")
    private List<SearchUsersData> searchUsersData;

    public List<SearchUsersData> getSearchUsersData() {
        return searchUsersData;
    }

    public void setModelSearchUser(List<SearchUsersData> modelSearchData) {
        this.searchUsersData = modelSearchData;
    }

}
