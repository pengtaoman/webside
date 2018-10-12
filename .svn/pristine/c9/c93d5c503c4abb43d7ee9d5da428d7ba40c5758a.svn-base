package com.webside.business.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webside.base.baseservice.impl.AbstractService;
import com.webside.business.mapper.PropertyMapper;
import com.webside.business.model.PropertyEntity;
import com.webside.business.service.PropertyService;

@Service("propertyService")
public class PropertyServiceImpl extends AbstractService<PropertyEntity, String> implements PropertyService{

	@Autowired
	private PropertyMapper PropertyMapper;			
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(PropertyMapper);
	}
}
