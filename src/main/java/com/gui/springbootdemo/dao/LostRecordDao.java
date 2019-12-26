package com.gui.springbootdemo.dao;

import com.gui.springbootdemo.pojo.LostRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LostRecordDao extends JpaRepository<LostRecord,Integer> {


    List<LostRecord> findAll();

    LostRecord findById(int id);

    LostRecord save(LostRecord lostRecord);

    void delete(LostRecord lostRecord);

    List<LostRecord> findAllByLostUserId(int lostUserId);

    List<LostRecord> findAllByStatus(int status);
}
