package com.webside.business.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webside.business.model.EnforceEntrustEntity;
import com.webside.business.model.TaskEntity;
import com.webside.business.model.TaskFacilityEntity;

public interface  EntrustService {
	public int saveTask(TaskEntity record);
	public int saveExpectPrice(String expectPrice, String taskId,String facilityId); 
	public int saveEnforceEntrust(EnforceEntrustEntity record, String type);
	public List<TaskEntity> queryTaskListByPage(Map<String, Object> parameter);
	public List<TaskFacilityEntity> queryTaskFacilityListByPage(Map<String, Object> parameter);
	public boolean saveTaskDetail(String facilityIds, String taskId);
	public int updateTask(String taskId, String ifValid);
	public int saveEntrustStatus(String entrustid);
	public int updateEntrustFicilityTestResult(String entrustId, String facilityId, String testResult);
	public boolean deleteTaskByIds(List<String> ids);
	public boolean deleteTaskFacility(String id);
	public TaskEntity selectTaskById(String id);
	public EnforceEntrustEntity selectEnforceEntrustById(String id);
	public List<EnforceEntrustEntity>  selectEnforceEntrust(Map<String, Object> parameter);
	public List<EnforceEntrustEntity> selectEnforceEntrustListByTaskId(String taskId);
	public List<Map> facilityListByPage(Map<String, Object> parameter);
	public boolean saveFacilitysToEntrust(String facilityIds, String ringtaskId, String entrustId, String expectPrices, int cid);
	public boolean deleteEntrustByIds(List<String> ids);
	public List<Map> entrustFacilityListByPage(Map<String, Object> parameter);
	public List<Map> inquiryList(Map pmap);
	public List<Map> taskEntrustList(Map<String, Object> parameter);
}
