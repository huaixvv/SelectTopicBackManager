package com.topicmanager.result;

import lombok.Data;
import java.util.List;

@Data
public class ThesisResult {

    public ThesisResult(List list, Integer count) {
        this.list = list;
        this.count = count;
    }

    private List list;
    private Integer count;
}
