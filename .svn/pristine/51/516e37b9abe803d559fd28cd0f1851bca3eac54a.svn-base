package com.webside.business.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webside.base.basemodel.PairMulti;
import com.webside.base.basemodel.PairName;
import com.webside.base.baseservice.impl.AbstractService;
import com.webside.business.mapper.FacilityFeeMapper;
import com.webside.business.model.FacilityFeeEntity;
import com.webside.business.service.FacilityFeeService;

@Service("FacilityFeeService")
public class FacilityFeeServiceImpl extends AbstractService<FacilityFeeEntity, Long> implements FacilityFeeService{

	@Autowired
	private FacilityFeeMapper facilityFeeMapper;			
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(facilityFeeMapper);
	}

	@Override
	public List<PairName> queryFields(Map<String, Object> map) {
		return facilityFeeMapper.queryFields(map);
	}

	@Override
	public List<PairName> queryFacilityNames(Map<String, Object> map) {
		return facilityFeeMapper.queryFacilityNames(map);
	}

	@Override
	public List<PairName> queryModels(Map<String, Object> map) {
		return facilityFeeMapper.queryModels(map);
	}
	
	@Override
	public List<PairName> queryAccuracys(Map<String, Object> map) {
		return facilityFeeMapper.queryAccuracys(map);
	}

	@Override
	public List<PairName> queryTestRanges(Map<String, Object> map) {
		return facilityFeeMapper.queryTestRanges(map);
	}

	@Override
	public List<PairName> queryFeeUnits(Map<String, Object> map) {
		return facilityFeeMapper.queryFeeUnits(map);
	}
	
	@Override
	public List<PairMulti> queryFeePriceAndUnit(List<String> fids) {
		return facilityFeeMapper.queryFeePriceAndUnit(fids);
	}
	
}
