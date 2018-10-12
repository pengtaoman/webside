package com.webside.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.webside.base.basemapper.BaseMapper;
import com.webside.user.model.SmsDTO;
import com.webside.user.model.SmsInfoEntity;

@Repository
public interface SmsMapper  extends BaseMapper<SmsDTO, Long>{
	
	public List<SmsDTO> queryList(@Param("owner")Long owner);
	
	public SmsDTO findByTitle(@Param("owner")Long owner, @Param("title")String title);
	
	public int insertSmsInfo(@Param("record")SmsInfoEntity record);

	public int updateStatus(Long id);
	public int updateStatusBatch(List<Long> ids);
}
