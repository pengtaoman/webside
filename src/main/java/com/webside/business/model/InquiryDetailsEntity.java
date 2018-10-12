package com.webside.business.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Database Table Remarks:
 *   è¯¢æŠ¥ä»·æ˜Žç»†è¡¨
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table tb_inquiry_details
 *
 * @mbg.generated do_not_delete_during_merge Sat Jan 06 21:07:14 CST 2018
 */
public class InquiryDetailsEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_inquiry_details.id
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_inquiry_details.inquiry_id
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    private String inquiryId;

    /**
     * Database Column Remarks:
     *   计量器具
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_facility_fee.name
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    private String name;

    /**
     * Database Column Remarks:
     *   所属专业
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_facility_fee.field
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    private String field;

    /**
     * Database Column Remarks:
     *   规格型号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_facility_fee.model
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    private String model;

    /**
     * Database Column Remarks:
     *   准确度等级
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_facility_fee.accuracy
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    private String accuracy;

    /**
     * Database Column Remarks:
     *   æ ‡å‡†èµ„è´¹
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_inquiry_details.stand_price
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    private BigDecimal standPrice;

    /**
     * Database Column Remarks:
     *   æœŸæœ›ä»·æ ¼
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_inquiry_details.expect_price
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    private BigDecimal expectPrice;

    /**
     * Database Column Remarks:
     *   æŠ¥ä»·
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_inquiry_details.price
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    private BigDecimal price;

    /**
     * Database Column Remarks:
     *   æ•°é‡�
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_inquiry_details.amount
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    private Integer amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_inquiry_details.type
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_inquiry_details.unit
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    private String unit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_inquiry_details.test_range
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    private String testRange;

    private String note;

    public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	/**
     * Database Column Remarks:
     *   åˆ›å»ºæ—¶é—´
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_inquiry_details.create_time
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_inquiry_details.id
     *
     * @return the value of tb_inquiry_details.id
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_inquiry_details.id
     *
     * @param id the value for tb_inquiry_details.id
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_inquiry_details.inquiry_id
     *
     * @return the value of tb_inquiry_details.inquiry_id
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public String getInquiryId() {
        return inquiryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_inquiry_details.inquiry_id
     *
     * @param inquiryId the value for tb_inquiry_details.inquiry_id
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public void setInquiryId(String inquiryId) {
        this.inquiryId = inquiryId == null ? null : inquiryId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_inquiry_details.stand_price
     *
     * @return the value of tb_inquiry_details.stand_price
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public BigDecimal getStandPrice() {
        return standPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_inquiry_details.stand_price
     *
     * @param standPrice the value for tb_inquiry_details.stand_price
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public void setStandPrice(BigDecimal standPrice) {
        this.standPrice = standPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_inquiry_details.expect_price
     *
     * @return the value of tb_inquiry_details.expect_price
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public BigDecimal getExpectPrice() {
        return expectPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_inquiry_details.expect_price
     *
     * @param expectPrice the value for tb_inquiry_details.expect_price
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public void setExpectPrice(BigDecimal expectPrice) {
        this.expectPrice = expectPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_inquiry_details.price
     *
     * @return the value of tb_inquiry_details.price
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_inquiry_details.price
     *
     * @param price the value for tb_inquiry_details.price
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_inquiry_details.price
     *
     * @param price the value for tb_inquiry_details.price
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public void setPrice(String price) {
    	try {
    		this.price = new BigDecimal(price);
    	} catch(Exception e){}
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_inquiry_details.amount
     *
     * @return the value of tb_inquiry_details.amount
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_inquiry_details.amount
     *
     * @param amount the value for tb_inquiry_details.amount
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_inquiry_details.type
     *
     * @return the value of tb_inquiry_details.type
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_inquiry_details.type
     *
     * @param type the value for tb_inquiry_details.type
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_inquiry_details.unit
     *
     * @return the value of tb_inquiry_details.unit
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public String getUnit() {
        return unit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_inquiry_details.unit
     *
     * @param unit the value for tb_inquiry_details.unit
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_inquiry_details.test_range
     *
     * @return the value of tb_inquiry_details.test_range
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public String getTestRange() {
        return testRange;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_inquiry_details.test_range
     *
     * @param testRange the value for tb_inquiry_details.test_range
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public void setTestRange(String testRange) {
        this.testRange = testRange == null ? null : testRange.trim();
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_inquiry_details.create_time
     *
     * @return the value of tb_inquiry_details.create_time
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_inquiry_details.create_time
     *
     * @param createTime the value for tb_inquiry_details.create_time
     *
     * @mbg.generated Sat Jan 06 21:07:14 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}