package com.bfec.dsdemo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bfec.dsdemo.model.MUser;

@Repository("muserMapper")
public interface MUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MUser record);

    int insertSelective(MUser record);

    MUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MUser record);

    int updateByPrimaryKey(MUser record);
    
    List<MUser> getAll();
}