package com.example.demo;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class Demo
{
    private static Logger log = getLogger(Demo.class);

    public static void main(String[] args)
    {
        List<String> lists = new ArrayList<String>();

        lists.add("1");
        lists.add("2");

        lists.forEach(str->{
            System.out.println(lists.indexOf(str));
        });
    }

}
