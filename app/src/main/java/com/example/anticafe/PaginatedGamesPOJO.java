package com.example.anticafe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaginatedGamesPOJO {
//    @SerializedName("count")
    private int count;

//    @SerializedName("next")
    private String next;

//    @SerializedName("previous")
    private String  previous;

    private List<GamePOJO> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<GamePOJO> getResults() {
        return results;
    }

    public void setResults(List<GamePOJO> results) {
        this.results = results;
    }
}
