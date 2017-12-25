package com.example.demo.repository;

import com.example.demo.domain.User;
import com.example.demo.tools.PatternConstant;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class UserRepository
{
    // 日志
    private static Logger log = getLogger(UserRepository.class);

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Object> findAll()
    {
        String sql = buildSql();
        log.info("query user sql is :{}", sql);
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);

        List<Object> users = new ArrayList<Object>();
        List<Object> usersCopy = new ArrayList<Object>();
        User userHead = new User();
        userHead.setId("主键");
        userHead.setCustomerId("客户ID");
        userHead.setCustomerName("客户名称");
        userHead.setPasswd("密码");
        userHead.setCreateTime("创建时间");
        usersCopy.add(userHead);
        results.forEach((Map<String, Object> map) ->
        {
            User user = new User();
            user.setId(String.valueOf(map.get("id")));
            user.setCustomerId((String) map.get("customerId"));
            user.setCustomerName((String) map.get("customerName"));
            user.setPasswd((String) map.get("passwd"));
            Date date = (Date) map.get("createTime");
            SimpleDateFormat sdf = new SimpleDateFormat(PatternConstant.yyyy_NN_dd_HH_mm_ss);
            user.setCreateTime(sdf.format(date));
            Object obj = (Object) user;
            users.add(user);
        });
        usersCopy.addAll(users);
        return usersCopy;
    }

    private String buildSql()
    {
        return "select * from t_test_tb1";
    }
}
