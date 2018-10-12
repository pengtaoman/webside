package com.webside.business.service;

import java.util.List;
import java.util.Map;

import com.webside.base.basemodel.PairName;
import com.webside.base.baseservice.BaseService;
import com.webside.business.model.CompanyAuthEntity;

public interface CompanyAuthService extends BaseService<CompanyAuthEntity, Integer>{

	public List<PairName> queryNames(Map<String, Object> params);

	public List<Long> queryUserIdByCompanyId(Long companyId); 
	
	public List<Long> queryCompanyIdByUserId(Long userId);    
}