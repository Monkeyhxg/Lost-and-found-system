package com.gui.springbootdemo.dao;

import com.gui.springbootdemo.pojo.LostUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName LostUsersDao
 * @Description TODO
 * @Author MI
 * @Date 2019/12/25 23:24
 * @Version 1.0
 */
public interface LostUsersDao extends JpaRepository<LostUsers,Integer> {

    List<LostUsers> findAll();

    LostUsers findById(int id);

    LostUsers save(LostUsers lostUsers);

    void delete(LostUsers lostUsers);

    LostUsers findByPhone(String phone);

}
