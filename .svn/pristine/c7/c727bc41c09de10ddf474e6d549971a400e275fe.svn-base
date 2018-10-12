package com.webside.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.webside.base.basemapper.BaseMapper;
import com.webside.business.model.InquiryDetailsEntity;
import com.webside.business.model.InquiryEntity;

public interface InquiryMapper extends BaseMapper<InquiryEntity, String>{
	int insertDetailsBatch(List<InquiryDetailsEntity> list);
	List<InquiryDetailsEntity> queryDetailsList(@Param("id")String id);
	int deleteDetailsByInquiryId(@Param("inquiryId")String inquiryId);
}