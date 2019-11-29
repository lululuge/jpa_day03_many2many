package com.luge.test;

import com.luge.dao.RoleDao;
import com.luge.dao.UserDao;
import com.luge.domain.Role;
import com.luge.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestMany2Many {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 测试保存功能
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd() {
        User user = new User();
        user.setUserName("陆昝");
        user.setAge(23);
        Role role = new Role();
        role.setRoleName("CEO");
        user.getRoles().add(role);
        role.getUsers().add(user);
        userDao.save(user);
        roleDao.save(role);
    }

    /**
     * 测试查询
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testFind() {
        User user = userDao.findOne(4l);
        System.out.println(user);
        System.out.println(user.getRoles());
        Role role = roleDao.findOne(4l);
        System.out.println(role);
        System.out.println(role.getUsers());
    }

    /**
     * 测试级联添加（保存一个用户的同时保存该用户关联的角色）
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeAdd() {
        User user = new User();
        user.setUserName("杜兰特");
        user.setAge(30);
        Role role = new Role();
        role.setRoleName("包工头");
        user.getRoles().add(role);
        userDao.save(user);
    }

    /**
     * 测试级联删除（删除一个用户的同时删除该用户关联的角色）
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeDelete() {
        userDao.delete(4l);
    }
}
