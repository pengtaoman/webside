package com.webside.business.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webside.base.baseservice.impl.AbstractService;
import com.webside.business.mapper.FacilityEntityMapper;
import com.webside.business.model.FacilityEntity;
import com.webside.business.service.FacilityService;
import com.webside.exception.ServiceException;
@Service("facilityService")
public class FacilityServiceImpl extends AbstractService<FacilityEntity, String> implements FacilityService {
	@Autowired
	private FacilityEntityMapper facilityEntityMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(facilityEntityMapper);
	}


	@Override
	public List<FacilityEntity> findResourcesByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FacilityEntity> findResourcesMenuByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FacilityEntity> queryTreeGridListByPage(Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FacilityEntity> queryResourceList(Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteBatchByIds(List<String> facilityIds) {
		try
		{
			facilityEntityMapper.deleteBatchByIds(facilityIds);
			return true;
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
		
	}
	@Override
	public boolean updateFacilityStateById(FacilityEntity record) {
		try
		{
			int back = facilityEntityMapper.updateFacilityStateById(record);
			if ( back>=1 ){
				return true;
			}
			return false;
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
		
	}

	@Override
	public int updateByPrimaryKeySelective(FacilityEntity record) {
		return facilityEntityMapper.updateByPrimaryKeySelective(record);
	}
	
	public List<String> selectFactoryId(Map<String, Object> parameter){
		return facilityEntityMapper.selectFactoryId(parameter);
	}
	
	public int insertBatch(List<FacilityEntity> list) {
		
		int total = list.size();
		int sum = 0;
		int pos = 0;
		for(; (pos + BATCH_ONCE) < total; pos+=BATCH_ONCE) {
			sum += facilityEntityMapper.insertBatch(list.subList(pos, pos + BATCH_ONCE));
		} 
		sum += facilityEntityMapper.insertBatch(list.subList(pos, total));
		
		return sum;
	}


	@Override
	public List<FacilityEntity> queryInquriyListByPage(Map<String, Object> parameter) {
			return facilityEntityMapper.queryInquriyListByPage(parameter);
	}
}

