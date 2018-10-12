package com.webside.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.webside.base.basemapper.BaseMapper;
import com.webside.business.model.CompanyAuthEntity;

public interface CompanyAuthMapper  extends BaseMapper<CompanyAuthEntity, Integer>{

	List<Long> queryUserIdByCompanyId(@Param("id")Long companyId);
    
	List<Long> queryCompanyIdByUserId(@Param("id")Long userId);
}