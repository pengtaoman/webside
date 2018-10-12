package com.webside.user.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.webside.base.basemodel.BaseEntity;

/**
 * 
 * @ClassName: smsDTO
 * @Description: 用户账户信息
 * @author gaogang
 * @date 2016年7月12日 下午2:39:12
 *
 */
@Alias("smsDTO")
public class SmsDTO extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long owner;
	private Integer status;
	private Long idInfo;
	

	private String title;
	private String content;
	private Date createTime;
	
	/*
	 * 个人资料信息
	 */
	private SmsInfoEntity smsInfo;

	public SmsDTO() {

	}
	
	public SmsDTO(Long owner, String title, String content) {
		this.owner = owner;
		this.status = 0;
		smsInfo = new SmsInfoEntity();
		smsInfo.setType(0);
		smsInfo.setTitle(title);
		smsInfo.setContent(content);
	}

	@Override
	public String toString() {
		return "smsDTO [id=" + id;
	}

	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getIdInfo() {
		return idInfo;
	}

	public void setIdInfo(Long idInfo) {
		this.idInfo = idInfo;
	}

	public SmsInfoEntity getSmsInfo() {
		return smsInfo;
	}

	public void setSmsInfo(SmsInfoEntity smsInfo) {
		this.smsInfo = smsInfo;
		this.title = smsInfo.getTitle();
		this.content = smsInfo.getContent();
		this.createTime = smsInfo.getCreateTime();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
