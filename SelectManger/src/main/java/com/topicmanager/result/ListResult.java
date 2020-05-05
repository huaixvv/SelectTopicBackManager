package com.topicmanager.result;

import lombok.Data;

import java.util.List;

@Data
public class ListResult {

    public ListResult(List list, Integer count) {
        this.list = list;
        this.count = count;
    }

    private List list;
    private Integer count;
}
