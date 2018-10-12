package com.webside.business.service;

import java.util.List;
import java.util.Map;

import com.webside.business.model.FacilityEntity;

public interface  FacilityService {
	/**
	 * 自定义方法
	 * 获取用户ID对应的资源信息
	 * @param userId
	 * @return
	 */
	public List<FacilityEntity> findResourcesByUserId(int userId);

	/**
	 * 自定义方法
	 * 获取用户ID对应的资源菜单信息
	 * @param userId
	 * @return
	 */
	public List<FacilityEntity> findResourcesMenuByUserId(int userId);
	
	public List<FacilityEntity> queryListByPage(Map<String, Object> parameter);
	
	public List<FacilityEntity> queryInquriyListByPage(Map<String, Object> parameter);
	
	public List<FacilityEntity> queryTreeGridListByPage(Map<String, Object> parameter);
	
	public FacilityEntity findByName(String name);
	
	public FacilityEntity findById(String id);

	public int update(FacilityEntity facilityEntity);
    
    public List<FacilityEntity> queryResourceList(Map<String, Object> parameter);
    
    public int insert(FacilityEntity facilityEntity);
    public int insertBatch(List<FacilityEntity> list);
    
    public List<String> selectFactoryId(Map<String, Object> parameter);
    
    public int count(Map<String, Object> parameter);
    
    public boolean deleteBatchByIds(List<String> facilityIds);
    
    public boolean updateFacilityStateById(FacilityEntity record);
    
    int updateByPrimaryKeySelective(FacilityEntity record);
}
