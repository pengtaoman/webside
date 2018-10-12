package com.webside.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webside.base.baseservice.impl.AbstractService;
import com.webside.exception.ServiceException;
import com.webside.role.mapper.RoleMapper;
import com.webside.role.model.RoleEntity;
import com.webside.user.mapper.CompanyMapper;
import com.webside.user.model.CompanyEntity;
import com.webside.user.model.KvEntity;
import com.webside.user.model.UserEntity;
import com.webside.user.service.CompanyService;
import com.webside.user.service.UserService;
import com.webside.util.StringUtils;

@Service("companyService")
public class CompanyServiceImpl extends AbstractService<CompanyEntity, Long> implements CompanyService{

	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleMapper roleMapper;
		
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(companyMapper);
	}

	/**
	 * 重写用户插入，逻辑：
	 * 1、插入企业
	 */
	public int insertOne(CompanyEntity entity){
		try
		{
			if(companyMapper.insert(entity) == 1)
			{
				if (userService.updateCompanyByUser(entity.getRootId(), entity.getId(), entity.getName()) == 0) {
					throw new ServiceException("新增企业: "+entity.getRootId()+" 失败");
				}
				
				return 1;
			}else
			{
				throw new ServiceException("新增企业: "+entity.getRootId()+" 失败");
			}
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}
	

	/**
	 * 重写用户更新逻辑：
	 * 1、更新用户
	 * 2、更新用户和角色的对应关系
	 * 3、更新用户个人资料信息
	 */
	public int update(CompanyEntity entity){
		try
		{
			if(companyMapper.updateInfo(entity) == 1)
			{
				if(!StringUtils.isNullOrEmpty(entity.getName()) 
						&& entity.getId() > 0) {
					userService.updateCompany(entity.getId(), entity.getName());
				}
				
				return 1;
			}else
			{
				return 0;
			}
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}	
	
	
	/**
	 * 重写用户删除逻辑：
	 * 1、删除用户和角色的对应关系
	 * 2、删除用户
	 */
	public int deleteById(Long id){
		try
		{
			List<Long>uids = userService.findUIdByCompanyId(id);
			if (!uids.isEmpty()) {
				userService.deleteBatchById(uids);
			}
			
			return companyMapper.deleteById(id);
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 锁定公司所有员工账号
	 */
	public int deleteBatchById(List<Long> ids){
			
		return 0;
	}

	@Override
	public int updateStatus(Long id, String val, String reason) {
		try
		{			
			return companyMapper.updateStatus(id, val, reason);
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public int updateCert(Long id, String val) {
		try
		{
			return companyMapper.updateCert(id, val);
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
		
	}

	@Override
	public int updateRemark(Long id, String val) {
		try
		{
			return companyMapper.updateRemark(id, val);
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
		
	}

	@Override
	public int updateStatusBatch(List<Long> ids, String status, String reason) {
		try
		{		
			Map<String, Object> params = new HashMap<String, Object>();
			// 修改申请者为主账户角色			
			params.put("companyIds", ids);
			
			List<UserEntity> userlist = userService.queryListByPage(params);
			
			RoleEntity mainRole = roleMapper.findByKey("roleCompanyRoot");
			
			for (UserEntity entity : userlist) {
				entity.setRole(mainRole);
				userService.updateUserRole(entity);
			}
			
			return companyMapper.updateStatusBatch(ids, status, reason);
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public List<KvEntity> queryListKeyValue(Map<String, Object> param) {
		return companyMapper.queryListKeyValue(param);
	}
	
	@Override
	public List<Long> queryCompanyIdByCname(Map<String, Object> param) {
		return companyMapper.queryCompanyIdByCname(param);
	}
}
