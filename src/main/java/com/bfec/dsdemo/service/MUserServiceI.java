package com.bfec.dsdemo.service;

import java.util.List;

import com.bfec.dsdemo.model.MUser;

public interface MUserServiceI {

	List<MUser> getAll();
	
	MUser selectByPrimaryKey(Integer id);
	
    int insert(MUser muser) throws Exception;
    
    int update(MUser muser);
    
    int delete(Integer id);
}
