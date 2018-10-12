package com.webside.user.service;

import java.util.List;
import java.util.Map;

import com.webside.base.basemodel.PairName;
import com.webside.user.model.CompanyEntity;
import com.webside.user.model.KvEntity;

public interface CompanyService {

	public List<CompanyEntity> queryListByPage(Map<String, Object> parameter);

	public CompanyEntity findByName(String name);
	
	public int insertOne(CompanyEntity entity);
	
	public CompanyEntity findById(Long id);

	public int update(CompanyEntity entity);
	
	public int updateStatus(Long id, String val, String reason);
	
	public int updateRemark(Long id, String val);
	
	public int updateCert(Long id, String val);
    
    public int deleteBatchById(List<Long> userIds);
    
    public int deleteById(Long id);

	public int updateStatusBatch(List<Long> ids, String status, String reason);

	public List<KvEntity> queryListKeyValue(Map<String, Object> param);

	public List<PairName> queryNames(Map<String, Object> params);
	
	public List<Long> queryCompanyIdByCname(Map<String, Object> param);
	
}