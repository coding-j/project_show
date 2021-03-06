package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(User user) {
        return jdbcTemplate.update("insert into user(userName,password,authority) values(?,?,2)",user.getUserName(),user.getPassword());
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("update user set password=? where userName=?",user.getPassword(),user.getUserName());
    }

    @Override
    public int deleteByName(String userName) {
        return jdbcTemplate.update("delete from user where userName=?",userName);
    }

    @Override
    public User queryUserResourceByName(String userName) {
//        System.out.println("user:"+userName);
//        String sql = "select * from user where name=" + "'"+ userName + "'";
        String sql = "select * from user where userName=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        try {
            User user = jdbcTemplate.queryForObject(sql,rowMapper,userName);
//        System.out.println("database:"+user.getPassword());
            if(null != user){
                System.out.println(user.getUserName());
                System.out.println(user.getPassword());
                return user;
            }else {
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int passwordEdit(String password, int teacherId) {
        return jdbcTemplate.update("update user set password=? where userId=?",password,teacherId);
    }

    @Override
    public int queryUserIdByName(String userName) {
        String sql = "select userId from user where userName="+"'"+userName+"'";
        int teacherId = jdbcTemplate.queryForObject(sql,Integer.class);
        return teacherId;
    }
}
