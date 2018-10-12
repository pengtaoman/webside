package com.webside.user.service;

import java.util.List;
import java.util.Map;

import com.webside.base.basemodel.PairName;
import com.webside.exception.ServiceException;
import com.webside.user.model.UserEntity;

public interface UserService {

	public List<UserEntity> queryListByPage(Map<String, Object> parameter);

	public UserEntity findByName(String accountName);
	
	public int insert(UserEntity userEntity, String password);
	
	public UserEntity findById(Long id);

	public int update(UserEntity userEntity);
	
	public int updateOnly(UserEntity userEntity) throws ServiceException;
	
	public int updatePassword(UserEntity userEntity, String password) throws ServiceException;
    
    public int deleteBatchById(List<Long> userIds);
    
    public int lockBatchById(List<Long> userIds);

    public int unLockBatchById(List<Long> userIds);

	public int updateCompany(Long id, String name);

	public int updateCompanyByUser(Long rootId, Long id, String name);

	public int updateUserRole(UserEntity entity);

	public List<UserEntity> queryListAll(Map<String, Object> params);
	
	public List<PairName> queryNames(Map<String, Object> params);

	public List<Long> findUIdByCompanyId(Long cid);

	public int deleteByCompanyId(Long cid);
    
}