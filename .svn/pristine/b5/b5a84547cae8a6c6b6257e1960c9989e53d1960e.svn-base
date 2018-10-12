package com.webside.business.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webside.base.baseservice.impl.AbstractService;
import com.webside.business.mapper.ExamineOrganMapper;
import com.webside.business.model.ExamineOrganEntity;
import com.webside.business.service.ExamineOrganService;

@Service("examineOrganService")
public class ExamineOraganServiceImpl extends AbstractService<ExamineOrganEntity, Long> implements ExamineOrganService{

	@Autowired
	private ExamineOrganMapper examineOrganMapper;			
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(examineOrganMapper);
	}
}
