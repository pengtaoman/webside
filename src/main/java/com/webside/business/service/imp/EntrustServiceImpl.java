package com.webside.business.service.imp;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webside.business.mapper.EnforceEntrustEntityMapper;
import com.webside.business.mapper.EnforceEntrustFacilityEntityMapper;
import com.webside.business.mapper.TaskEntityMapper;
import com.webside.business.mapper.TaskFacilityEntityMapper;
import com.webside.business.model.EnforceEntrustEntity;
import com.webside.business.model.EnforceEntrustEntityExample;
import com.webside.business.model.EnforceEntrustFacilityEntity;
import com.webside.business.model.EnforceEntrustFacilityEntityExample;
import com.webside.business.model.TaskEntity;
import com.webside.business.model.TaskFacilityEntity;
import com.webside.business.model.TaskFacilityEntityExample;
import com.webside.business.service.EntrustService;
import com.webside.exception.ServiceException;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.util.BasePK;

@Service("entrustService")
public class EntrustServiceImpl implements EntrustService {
	@Autowired
	private TaskEntityMapper taskEntityMapper;

	@Autowired
	private TaskFacilityEntityMapper taskFacilityEntityMapper;
	
	@Autowired
	private EnforceEntrustEntityMapper enforceEntrustEntityMapper;


	@Autowired
	private EnforceEntrustFacilityEntityMapper enforceEntrustFacilityEntityMapper;
	
	@Override
	public int saveTask(TaskEntity record) {
		return taskEntityMapper.insert(record);
	}
	@Override
	public int saveExpectPrice(String expectPrice, String taskId, String facilityId){
		EnforceEntrustFacilityEntity record = new EnforceEntrustFacilityEntity();
		record.setExpectPrice(new BigDecimal(expectPrice));
		EnforceEntrustFacilityEntityExample example = new EnforceEntrustFacilityEntityExample();
		example.createCriteria().andTaskIdEqualTo(taskId).andFacilityIdEqualTo(facilityId);
		return enforceEntrustFacilityEntityMapper.updateByExampleSelective(record, example);
	}
	@Override
	public int saveEnforceEntrust(EnforceEntrustEntity record, String type){
		if("add".equals(type)){
			return enforceEntrustEntityMapper.insertSelective(record);
		} else {
			return enforceEntrustEntityMapper.updateByPrimaryKeySelective(record);
		}
		
	}
	@Override
	public List<TaskEntity> queryTaskListByPage(Map<String, Object> parameter) {
		return taskEntityMapper.queryListByPage(parameter);
	}

	@Override
	@Transactional
	public boolean saveTaskDetail(String facilityIds, String taskId) {
		String [] facilityIdsArr = facilityIds.split(",");
		TaskFacilityEntityExample taskFacilityEntityExample = new TaskFacilityEntityExample();
		taskFacilityEntityExample.createCriteria().andTaskIdEqualTo(taskId).andFacilityIdIn(Arrays.asList(facilityIdsArr));
		taskFacilityEntityMapper.deleteByExample(taskFacilityEntityExample);

		facilityIds = facilityIds.substring(0, facilityIds.length() - 1);
		String[] facilityIdArr = facilityIds.split(",");
		for (String facilityId : facilityIdArr) {
			TaskFacilityEntity taskFacilityEntity = new TaskFacilityEntity();
			taskFacilityEntity.setId(BasePK.getPK());
			taskFacilityEntity.setFacilityId(facilityId);
			taskFacilityEntity.setTaskId(taskId);
			taskFacilityEntityMapper.insert(taskFacilityEntity);
		}
		return true;
	}

	@Override
	public List<TaskFacilityEntity> queryTaskFacilityListByPage(Map<String, Object> parameter) {
		return taskFacilityEntityMapper.queryListByPage(parameter);
	}
	
	public int updateTask(String taskId, String ifValid){
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setId(taskId);
		taskEntity.setOperator(ShiroAuthenticationManager.getUserAccountName());
		taskEntity.setIfValid(ifValid);
		taskEntity.setUpdateTime(new Date());
		return taskEntityMapper.updateByPrimaryKeySelective(taskEntity);
	}
	
	public int saveEntrustStatus(String entrustid){
		EnforceEntrustEntity enforceEntrustEntity = new EnforceEntrustEntity();
		enforceEntrustEntity.setIfOver("1");
		enforceEntrustEntity.setId(entrustid);
		return enforceEntrustEntityMapper.updateByPrimaryKeySelective(enforceEntrustEntity);
	}
	
	public int updateEntrustFicilityTestResult(String entrustId, String facilityId, String testResult){
		EnforceEntrustFacilityEntity entity = new EnforceEntrustFacilityEntity();
		entity.setTestResult(testResult);
		EnforceEntrustFacilityEntityExample example = new EnforceEntrustFacilityEntityExample();
		example.createCriteria().andEntrustIdEqualTo(entrustId).andFacilityIdEqualTo(facilityId);
		return enforceEntrustFacilityEntityMapper.updateByExampleSelective(entity, example);		
	}

	@Override
	public boolean deleteTaskByIds(List<String> ids) {
		try {
			taskEntityMapper.deleteBatchById(ids);
			return true;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public boolean deleteTaskFacility(String id){
		try {
			taskFacilityEntityMapper.deleteByPrimaryKey(id);
			return true;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	
	@Override
	@Transactional
	public boolean deleteEntrustByIds(List<String> ids) {
		try {
			enforceEntrustEntityMapper.deleteEntrustByIds(ids);
			enforceEntrustFacilityEntityMapper.deleteEntrustFacilityByIds(ids);
			return true;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public TaskEntity selectTaskById(String id) {
		return taskEntityMapper.selectByPrimaryKey(id);
	}
	public List<EnforceEntrustEntity> selectEnforceEntrustListByTaskId(String taskId){
		EnforceEntrustEntityExample example = new EnforceEntrustEntityExample();
		example.createCriteria().andTaskIdEqualTo(taskId);
		return enforceEntrustEntityMapper.selectByExample(example);
	} 
	public List<Map> facilityListByPage(Map<String, Object> parameter){
		return enforceEntrustEntityMapper.facilityListByPage(parameter);
	}
	public List<Map> entrustFacilityListByPage(Map<String, Object> parameter){
		return enforceEntrustEntityMapper.entrustFacilityListByPage(parameter);
	}
	public List<Map> taskEntrustList(Map<String, Object> parameter){
		return enforceEntrustEntityMapper.taskEntrustList(parameter);
	}
	@Override
	@Transactional
	public boolean saveFacilitysToEntrust(String facilityIds, String taskId, String entrustId,String expectPrices, int cid){
		HashMap paramMap = new HashMap();
		paramMap.put("facilityIds", facilityIds.split(","));
		paramMap.put("taskId", taskId);
		paramMap.put("entrustId", entrustId);
		enforceEntrustFacilityEntityMapper.deleteByTaskIdAndFacilityIds(paramMap);
		String [] facilityIdArr = facilityIds.split(",");
		String [] expectPriceArr = expectPrices.split(",");
		for(int i=0; i<facilityIdArr.length; i++){
			EnforceEntrustFacilityEntity record = new EnforceEntrustFacilityEntity();
			record.setId(BasePK.getPK());
			record.setCid(cid);
			record.setEntrustId(entrustId);
			if (expectPriceArr.length==0||null==expectPriceArr[i] ||"".equals(expectPriceArr[i])){
				record.setExpectPrice(new BigDecimal(0));
			} else {
				record.setExpectPrice(new BigDecimal(expectPriceArr[i]));
			}
			
			record.setFacilityId(facilityIdArr[i]);
			record.setTaskId(taskId);
			record.setTestResult("0");
			enforceEntrustFacilityEntityMapper.insert(record);
		}
		return true;
	}
	public List<Map> inquiryList(Map pmap){
		return enforceEntrustEntityMapper.inquiryList(pmap);
	}
	
	public EnforceEntrustEntity selectEnforceEntrustById(String id){
		return enforceEntrustEntityMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public  List<EnforceEntrustEntity> selectEnforceEntrust(Map<String, Object> parameter){
		return enforceEntrustEntityMapper.selectEnforceEntrust(parameter);
	}
}
