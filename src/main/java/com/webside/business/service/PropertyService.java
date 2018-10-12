package com.webside.business.service;

import com.webside.base.baseservice.BaseService;
import com.webside.business.model.PropertyEntity;

public interface PropertyService extends BaseService<PropertyEntity, String>{
	final String KEY_Inquiry_TEMPLATE = "InquiryTemplate"; 
	final String KEY_Entrust_TEMPLATE = "EntrustTemplate";
	final String KEY_Entrustforce_TEMPLATE = "EntrustforceTemplate";
}