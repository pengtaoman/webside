package com.webside.business.service.imp;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webside.base.baseservice.impl.AbstractService;
import com.webside.business.mapper.InquiryMapper;
import com.webside.business.model.InquiryDetailsEntity;
import com.webside.business.model.InquiryEntity;
import com.webside.business.service.InquiryService;

@Service("inquiryService")
public class InquiryServiceImpl extends AbstractService<InquiryEntity, String> implements InquiryService{

	@Autowired
	private InquiryMapper inquiryMapper;			
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(inquiryMapper);
	}

	@Override
	public int insertDetailsBatch(InquiryEntity entity, List<InquiryDetailsEntity> list) {
		if(inquiryMapper.insert(entity) < 1) {
			return -1;
		}
		
		int total = list.size();
		int sum = 0;
		int pos = 0;
		for(; (pos + BATCH_ONCE) < total; pos+=BATCH_ONCE) {
			sum += inquiryMapper.insertDetailsBatch(list.subList(pos, pos + BATCH_ONCE));
		} 
		sum += inquiryMapper.insertDetailsBatch(list.subList(pos, total));
		
		return sum;
	}

	@Override
	public int updateInquiry(InquiryEntity entity, List<InquiryDetailsEntity> list) {
		if(inquiryMapper.update(entity) < 1) {
			return -1;
		}
		
		inquiryMapper.deleteDetailsByInquiryId(entity.getId());
		
		return inquiryMapper.insertDetailsBatch(list);
	}
	
	@Override
	public List<InquiryDetailsEntity> queryDetailsList(String id) {
		return inquiryMapper.queryDetailsList(id);
	}
	
	@Override
	public List<InquiryEntity> queryInquiryByCid(Long cid) {
		HashMap pmap = new HashMap();
		pmap.put("companyId", cid);
		return inquiryMapper.queryListAll(pmap);
	}
}
