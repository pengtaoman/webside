package com.webside.business.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webside.base.baseservice.impl.AbstractService;
import com.webside.business.mapper.CompanyAuthMapper;
import com.webside.business.model.CompanyAuthEntity;
import com.webside.business.service.CompanyAuthService;

@Service("companyAuthService")
public class CompanyAuthServiceImpl extends AbstractService<CompanyAuthEntity, Integer> implements CompanyAuthService{

	@Autowired
	private CompanyAuthMapper companyAuthMapper;			
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(companyAuthMapper);
	}

	@Override
	public List<Long> queryUserIdByCompanyId(Long companyId) {
		return companyAuthMapper.queryUserIdByCompanyId(companyId);
	}

	@Override
	public List<Long> queryCompanyIdByUserId(Long userId) {
		return companyAuthMapper.queryCompanyIdByUserId(userId);
	}
}
