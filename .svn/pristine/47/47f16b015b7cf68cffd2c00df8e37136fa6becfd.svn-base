package com.webside.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.webside.base.basemapper.BaseMapper;
import com.webside.exception.ServiceException;
import com.webside.user.model.CompanyEntity;
import com.webside.user.model.KvEntity;

@Repository
public interface CompanyMapper extends BaseMapper<CompanyEntity, Long>{
	public int updateInfo(CompanyEntity entity) throws ServiceException;
	
	public int updateRemark(@Param("id")Long id, @Param("remark")String remark) throws ServiceException;
	
	public int updateCert(@Param("id")Long id, @Param("cert")String cert) throws ServiceException;
	
	public int updateStatus(@Param("id")Long id, @Param("status")String status, @Param("reason")String reason) throws ServiceException;
	public CompanyEntity findByRootId(@Param("rootId")Long rootId) throws ServiceException;

	public int updateStatusBatch(@Param("ids")List<Long> ids, @Param("status")String status, @Param("reason")String reason);

	public List<KvEntity> queryListKeyValue(Map<String, Object> param);
	
	List<Long> queryCompanyIdByCname(Map<String, Object> param);
}
