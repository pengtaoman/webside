package com.webside.user.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.webside.base.basemodel.BaseEntity;


/**
 * Database Table Remarks:
 *   公司表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table tb_company
 *
 * @mbg.generated do_not_delete_during_merge Tue Dec 05 16:23:39 CST 2017
 */
@Alias("companyEntity")
public class CompanyEntity extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2643325199139924700L;

	/**
     * Database Column Remarks:
     *   名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_company.c_name
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    private String name;

    /**
     * Database Column Remarks:
     *   主账号id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_company.c_root_id
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    private Long rootId;

    /**
     * Database Column Remarks:
     *   状态
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_company.c_status
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    private String status;

    /**
     * Database Column Remarks:
     *   电话
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_company.c_telephone
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    private String telephone;

    /**
     * Database Column Remarks:
     *   住址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_company.c_address
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    private String address;

    /**
     * Database Column Remarks:
     *   remark
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_company.c_remark
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    private String remark;
    
    private String cert;

    /**
     * Database Column Remarks:
     *   创建者
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_company.c_creator_name
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    private String creatorName;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_company.c_create_time
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    private Date createTime;

    /**
     * Database Column Remarks:
     *   更新时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_company.c_update_time
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    private Date updateTime;

    private String contactPerson;
    private String mobile;
    private String fax;
    private String regionCode;
    
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_company.c_name
     *
     * @return the value of tb_company.c_name
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_company.c_name
     *
     * @param cName the value for tb_company.c_name
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public void setName(String cName) {
        this.name = cName == null ? null : cName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_company.c_root_id
     *
     * @return the value of tb_company.c_root_id
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public Long getRootId() {
        return rootId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_company.c_root_id
     *
     * @param cRootId the value for tb_company.c_root_id
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public void setRootId(Long cRootId) {
        this.rootId = cRootId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_company.c_status
     *
     * @return the value of tb_company.c_status
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_company.c_status
     *
     * @param cStatus the value for tb_company.c_status
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public void setStatus(String cStatus) {
        this.status = cStatus == null ? null : cStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_company.c_telephone
     *
     * @return the value of tb_company.c_telephone
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_company.c_telephone
     *
     * @param cTelephone the value for tb_company.c_telephone
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public void setTelephone(String cTelephone) {
        this.telephone = cTelephone == null ? null : cTelephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_company.c_address
     *
     * @return the value of tb_company.c_address
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_company.c_address
     *
     * @param cAddress the value for tb_company.c_address
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public void setAddress(String cAddress) {
        this.address = cAddress == null ? null : cAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_company.c_remark
     *
     * @return the value of tb_company.c_remark
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_company.c_remark
     *
     * @param cRemark the value for tb_company.c_remark
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public void setRemark(String cRemark) {
        this.remark = cRemark == null ? null : cRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_company.c_creator_name
     *
     * @return the value of tb_company.c_creator_name
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_company.c_creator_name
     *
     * @param cCreatorName the value for tb_company.c_creator_name
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public void setCreatorName(String cCreatorName) {
        this.creatorName = cCreatorName == null ? null : cCreatorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_company.c_create_time
     *
     * @return the value of tb_company.c_create_time
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_company.c_create_time
     *
     * @param cCreateTime the value for tb_company.c_create_time
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public void setCreateTime(Date cCreateTime) {
        this.createTime = cCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_company.c_update_time
     *
     * @return the value of tb_company.c_update_time
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_company.c_update_time
     *
     * @param cUpdateTime the value for tb_company.c_update_time
     *
     * @mbg.generated Tue Dec 05 16:23:39 CST 2017
     */
    public void setUpdateTime(Date cUpdateTime) {
        this.updateTime = cUpdateTime;
    }

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}
}
