package com.sky.idea.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
public class CRUDController {
    //    注入jdbc模板
    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/query")
    public List<Map<String, Object>> query() {
        String sql = "select p.id,p.productName,p.quantity from warehouse.product as p";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/add")
    public int add() {
        String sql = "insert into warehouse.product (id,productName,quantity) values(?,?,?)";
        Object[] objects = new Object[3];
        objects[0] = 5;
        objects[1] = "test";
        objects[2] = 99;
        return jdbcTemplate.update(sql, objects);
    }

    @GetMapping("/update")
    public int delete() {
        String sql = "update product set productName=?,quantity=? where id=4";
        Object[] objects = new Object[2];
        objects[0] = "aaa";
        objects[1] = 99;
        return jdbcTemplate.update(sql, objects);
    }

    @GetMapping("/delete")
    public int update() {
        String sql = "delete from product where id=5";
        return jdbcTemplate.update(sql);
    }

}
