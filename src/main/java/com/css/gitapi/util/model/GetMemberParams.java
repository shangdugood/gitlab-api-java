package com.css.gitapi.util.model;

import java.util.ArrayList;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/14 9:14
 */
public class GetMemberParams {
    /**
     * A query string to search for members
     */
    private String search;
    /**
     * Filter the results on the given user IDs
     */
    private ArrayList<Integer> user_ids;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public ArrayList<Integer> getUser_ids() {
        return user_ids;
    }

    public void setUser_ids(ArrayList<Integer> user_ids) {
        this.user_ids = user_ids;
    }
}
