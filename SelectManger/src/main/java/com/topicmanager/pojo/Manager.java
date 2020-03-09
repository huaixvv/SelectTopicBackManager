package com.topicmanager.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Table(name = "manager")
public class Manager {

    @Id
    private Integer managerId;
    private String managerName;
    private String loginName;
    private String managerPwd;
}
