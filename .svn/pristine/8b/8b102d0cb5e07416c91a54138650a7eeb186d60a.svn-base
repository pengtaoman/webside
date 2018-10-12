package com.webside.business.service;

import java.util.List;
import java.util.Map;

import com.webside.base.basemodel.PairMulti;
import com.webside.base.basemodel.PairName;
import com.webside.base.baseservice.BaseService;
import com.webside.business.model.FacilityFeeEntity;

public interface FacilityFeeService extends BaseService<FacilityFeeEntity, Long>{
	List<PairName> queryFields(Map<String, Object> map);
	List<PairName> queryFacilityNames(Map<String, Object> map);
	List<PairName> queryModels(Map<String, Object> map);
	List<PairName> queryAccuracys(Map<String, Object> map);
	List<PairName> queryTestRanges(Map<String, Object> map);
	List<PairName> queryFeeUnits(Map<String, Object> map);
	List<PairMulti> queryFeePriceAndUnit(List<String> fids);
}