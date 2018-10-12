package com.webside.user.service;

import java.util.List;
import java.util.Map;

import com.webside.exception.ServiceException;
import com.webside.user.model.SmsDTO;

public interface SmsService {

	public SmsDTO findByTitle(Long owner, String title);
	
	public int insert(SmsDTO SmsDTO);
	
	public int updateStatus(Long id) throws ServiceException;
	public int updateStatus(List<Long> id) throws ServiceException;
    
    public int deleteBatchById(List<Long> userIds);

	public List<SmsDTO> queryListAll(Map<String, Object> params);
	public List<SmsDTO> queryListByPage(Map<String, Object> parameter);

	public int countUnread(Map<String, Object> params);

	public void insertSms(List<Long> authUserIds, String title, String content);
}