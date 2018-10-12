package com.webside.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webside.base.baseservice.impl.AbstractService;
import com.webside.exception.ServiceException;
import com.webside.user.mapper.SmsMapper;
import com.webside.user.model.SmsDTO;
import com.webside.user.service.SmsService;

@Service("smsService")
public class SmsServiceImpl extends AbstractService<SmsDTO, Long> implements SmsService{

	@Autowired
	private SmsMapper smsMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为smsMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(smsMapper);
	}
	
	public int insert(SmsDTO entity){
		try
		{
			if(smsMapper.insertSmsInfo(entity.getSmsInfo()) == 1)
			{
				entity.setIdInfo(entity.getSmsInfo().getId());
				return smsMapper.insert(entity);
			}else
			{
				throw new ServiceException("新增失败: "+entity.getTitle());
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
	public int deleteBatchById(List<Long> ids){
		try
		{
			return smsMapper.deleteBatchById(ids);
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public SmsDTO findByTitle(Long owner, String title) {
		return smsMapper.findByTitle(owner, title);
	}

	@Override
	public int updateStatus(Long id) throws ServiceException {
		return smsMapper.updateStatus(id);
	}
	@Override
	public int updateStatus(List<Long> ids) throws ServiceException {
		return smsMapper.updateStatusBatch(ids);
	}

	@Override
	public int countUnread(Map<String, Object> params) {
		return smsMapper.count(params);
	}

	@Override
	public void insertSms(List<Long> authUserIds, String title, String content) {
		authUserIds.forEach(x -> insert(new SmsDTO(x, title, content)));
	}
}
