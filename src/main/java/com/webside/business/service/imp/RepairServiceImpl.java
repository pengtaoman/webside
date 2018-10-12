package com.webside.business.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webside.business.mapper.FacilityEntityMapper;
import com.webside.business.mapper.RepairEntityMapper;
import com.webside.business.model.FacilityEntity;
import com.webside.business.model.RepairEntity;
import com.webside.business.model.RepairEntityExample;
import com.webside.business.service.RepairService;
@Service("repairService")
public class RepairServiceImpl implements RepairService {
	@Autowired
	private RepairEntityMapper repairEntityMapper;
	
	@Autowired
	private FacilityEntityMapper facilityEntityMapper;
	
	@Override
	public List<RepairEntity> queryListByPage(Map<String, Object> parameter) {
		return repairEntityMapper.queryListByPage(parameter);
	}

	@Override
	public List<RepairEntity> findRepairingById(String id) {
		RepairEntityExample example = new RepairEntityExample();
		example.createCriteria().andFacilityIdEqualTo(id).andFinishTimeIsNull();
		List<RepairEntity> list = repairEntityMapper.selectByExample(example);
		return list;
	}

	@Override
	public boolean saveRepairRecordChange(RepairEntity record) {
		int back = repairEntityMapper.updateByPrimaryKeySelective(record);
		if (back>=1){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean saveRepairRecord(RepairEntity record) {
		int back = repairEntityMapper.insertSelective(record);
		if (back>=1){
			return true;
		}
		return false;
	}
}

