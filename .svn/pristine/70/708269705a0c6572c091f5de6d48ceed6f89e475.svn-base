package com.webside.business.service;

import java.util.List;

import com.webside.base.baseservice.BaseService;
import com.webside.business.model.CodeEntity;
import com.webside.business.model.InquiryDetailsEntity;
import com.webside.business.model.InquiryEntity;


public interface InquiryService extends BaseService<InquiryEntity, String>{
	int insertDetailsBatch(InquiryEntity entity, List<InquiryDetailsEntity> list);
	List<InquiryDetailsEntity> queryDetailsList(String id);
	int updateInquiry(InquiryEntity entity, List<InquiryDetailsEntity> list);
	public List<InquiryEntity> queryInquiryByCid(Long cid);
}
