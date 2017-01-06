package com.bfec.dsdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfec.dsdemo.dao.MUserMapper;
import com.bfec.dsdemo.dynamicds.annotation.DataSource;
import com.bfec.dsdemo.model.MUser;
import com.bfec.dsdemo.service.MUserService;

@Transactional
@Service("muserService")
@DataSource("dataSource3")
public class MUserServiceImpl implements MUserService{

	@Autowired
	private MUserMapper muserMapper;

	@Override
	public List<MUser> getAll() {
		return muserMapper.getAll();
	}

	@Override
	@DataSource("dataSource2")
	public int insert(MUser muser) throws Exception {
		return muserMapper.insert(muser);
	}

	@Override
	public int update(MUser muser) {
		return muserMapper.updateByPrimaryKey(muser);
	}

	@Override
	public int delete(Integer id) {
		return muserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public MUser selectByPrimaryKey(Integer id) {
		return muserMapper.selectByPrimaryKey(id);
	}

}
