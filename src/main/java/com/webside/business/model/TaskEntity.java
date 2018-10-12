package com.webside.business.model;

import java.util.Date;

/**
 * Database Table Remarks:
 *   ä»»åŠ¡è¡¨
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table tb_task
 *
 * @mbg.generated do_not_delete_during_merge Thu Feb 08 10:30:25 CST 2018
 */
public class TaskEntity {
    /**
     * Database Column Remarks:
     *   ä»»åŠ¡id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_task.id
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_task.task_name
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    private String taskName;

    /**
     * Database Column Remarks:
     *   ç­›é€‰ç­–ç•¥ï¼š1 å…¨éƒ¨ï¼Œ2 åŸºäºŽæŠ¥ä»·å�•ï¼Œ3 åŸºäºŽåˆ°æœŸç­›é€‰  
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_task.strategy
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    private String strategy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_task.inquiry_id
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    private String inquiryId;

    /**
     * Database Column Remarks:
     *   ä¼�ä¸šid
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_task.cid
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    private Integer cid;

    /**
     * Database Column Remarks:
     *   æ˜¯å�¦å¼ºæ£€ï¼ˆæ˜¯ 1ã€�å�¦0ï¼‰
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_task.if_enforce
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    private String ifEnforce;

    /**
     * Database Column Remarks:
     *   æ£€æµ‹è¦�æ±‚
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_task.note
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    private String note;

    /**
     * Database Column Remarks:
     *   æ˜¯å�¦ç”Ÿæ•ˆ
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_task.if_valid
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    private String ifValid;

    /**
     * Database Column Remarks:
     *   æ“�ä½œäºº
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_task.operator
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    private String operator;

    /**
     * Database Column Remarks:
     *   æ›´æ–°æ—¶é—´
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_task.update_time
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.id
     *
     * @return the value of tb_task.id
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.id
     *
     * @param id the value for tb_task.id
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.task_name
     *
     * @return the value of tb_task.task_name
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.task_name
     *
     * @param taskName the value for tb_task.task_name
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.strategy
     *
     * @return the value of tb_task.strategy
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public String getStrategy() {
        return strategy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.strategy
     *
     * @param strategy the value for tb_task.strategy
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public void setStrategy(String strategy) {
        this.strategy = strategy == null ? null : strategy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.inquiry_id
     *
     * @return the value of tb_task.inquiry_id
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public String getInquiryId() {
        return inquiryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.inquiry_id
     *
     * @param inquiryId the value for tb_task.inquiry_id
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public void setInquiryId(String inquiryId) {
        this.inquiryId = inquiryId == null ? null : inquiryId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.cid
     *
     * @return the value of tb_task.cid
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.cid
     *
     * @param cid the value for tb_task.cid
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.if_enforce
     *
     * @return the value of tb_task.if_enforce
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public String getIfEnforce() {
        return ifEnforce;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.if_enforce
     *
     * @param ifEnforce the value for tb_task.if_enforce
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public void setIfEnforce(String ifEnforce) {
        this.ifEnforce = ifEnforce == null ? null : ifEnforce.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.note
     *
     * @return the value of tb_task.note
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.note
     *
     * @param note the value for tb_task.note
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.if_valid
     *
     * @return the value of tb_task.if_valid
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public String getIfValid() {
        return ifValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.if_valid
     *
     * @param ifValid the value for tb_task.if_valid
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public void setIfValid(String ifValid) {
        this.ifValid = ifValid == null ? null : ifValid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.operator
     *
     * @return the value of tb_task.operator
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public String getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.operator
     *
     * @param operator the value for tb_task.operator
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.update_time
     *
     * @return the value of tb_task.update_time
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.update_time
     *
     * @param updateTime the value for tb_task.update_time
     *
     * @mbg.generated Thu Feb 08 10:30:25 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getIfOver() {
		return ifOver;
	}

	public void setIfOver(String ifOver) {
		this.ifOver = ifOver;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	String ifOver;
    String companyName;
}