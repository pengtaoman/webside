package com.webside.quartz.service;

import java.util.List;
import java.util.Map;

import org.quartz.JobKey;
import org.quartz.TriggerKey;

import com.webside.quartz.model.ScheduleJobEntity;

public interface ScheduleJobService {

	public List<ScheduleJobEntity> queryListByPage(Map<String, Object> parameter);

	public int insert(ScheduleJobEntity job);
	
	public ScheduleJobEntity findById(Long id);

	public int update(ScheduleJobEntity job);
    
    public int deleteById(Long id);
    
    /**
     * 
     * 获取计划中的任务列表
     * @return	List<ScheduleJobEntity>	List<ScheduleJobEntity>
     */
    public List<ScheduleJobEntity> getPlanJobList();
    
    /**
     * 
     * 获取正在运行的任务列表
     * @return	List<ScheduleJobEntity> List<ScheduleJobEntity>
     */
    public List<ScheduleJobEntity> getRunningJobList();
    /**
     * 
     * 根据job获取和该job绑定的trigger列表信息
     * @param job	ScheduleJobEntity对象
     * @return	List<Trigger> List<Trigger>
     */
    public List<ScheduleJobEntity> getTriggers(ScheduleJobEntity job);
    /**
     * 
     * 根据job从scheduler中获取ScheduleJobEntity信息
     * @param job	ScheduleJobEntity
     * @return	ScheduleJobEntity ScheduleJobEntity
     */
    public ScheduleJobEntity getScheduleJobEntity(ScheduleJobEntity job);
    /**
     * 
     * 动态添加job
     * @param job	ScheduleJobEntity
     * @return	boolean	return true if success ,else false
     */
    public boolean addJob(ScheduleJobEntity job);
    /**
     * 
     * 动态添加trigger
     * @param job	ScheduleJobEntity
     * @return	boolean	return true if success ,else false
     */
    public boolean addJobTrigger(ScheduleJobEntity job);
    /**
     * 
     * 更新triggerr
     * @param job	ScheduleJobEntity
     * @return	boolean	return true if success ,else false
     */
    public boolean updateJobTrigger(ScheduleJobEntity job);
    /**
     * 
     * 暂停job
     * @param jobKey	JobKey
     * @return	boolean	return true if success ,else false
     */
    public boolean pauseJob(JobKey jobKey);
    /**
     * 
     * 暂停trigger
     * @param triggerKey
     * @return	boolean	return true if success ,else false
     */
    public boolean pauseJobTrigger(TriggerKey triggerKey);
    /**
     * 
     * 恢复job
     * @param job
     * @return	boolean	return true if success ,else false
     */
    public boolean resumeJob(JobKey jobKey);
    /**
     * 
     * 恢复trigger
     * @param job
     * @return	boolean	return true if success ,else false
     */
    public boolean resumeJobTrigger(TriggerKey triggerKey);
    /**
     * 
     * 删除job,同时会删除和job相关的trigger
     * @param jobKey	JobKey
     * @return	boolean	return true if success ,else false
     */
    public boolean deleteJob(JobKey jobKey);
    /**
     * 
     * 删除job触发器
     * @param triggerKey	TriggerKey
     * @return	boolean	return true if success ,else false
     */
    public boolean deleteJobTrigger(TriggerKey triggerKey);
    /**
     * 
     * 执行job
     * @param jobKey	JobKey
     * @return	boolean	return true if success ,else false
     */
    public boolean executeJob(JobKey jobKey);
    
    /**
     * 中断job
     * @param jobKey	JobKey
     * @return	boolean	return true if success ,else false
     */
    public boolean interruptJob(JobKey jobKey);
    /**
     * 
     * 开始所有job
     * @return	boolean	return true if success ,else false
     */
    public boolean startAllJob();
    /**
     * 
     * 暂停所有job
     * @return	boolean	return true if success ,else false
     */
    public boolean shutdownAllJob();
}
