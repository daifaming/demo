package com.example.demo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User
{
    private String id;

    private String customerId;

    private String customerName;

    private String passwd;

    private String createTime;

}
