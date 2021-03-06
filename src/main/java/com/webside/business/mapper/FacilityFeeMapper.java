package com.webside.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.webside.base.basemapper.BaseMapper;
import com.webside.base.basemodel.PairMulti;
import com.webside.base.basemodel.PairName;
import com.webside.business.model.FacilityFeeEntity;
import com.webside.business.model.FacilityFeeEntityExample;

public interface FacilityFeeMapper  extends BaseMapper<FacilityFeeEntity, Long> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_facility_fee
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    long countByExample(FacilityFeeEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_facility_fee
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    int deleteByExample(FacilityFeeEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_facility_fee
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_facility_fee
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    int insert(FacilityFeeEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_facility_fee
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    int insertSelective(FacilityFeeEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_facility_fee
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    List<FacilityFeeEntity> selectByExample(FacilityFeeEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_facility_fee
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    FacilityFeeEntity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_facility_fee
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    int updateByExampleSelective(@Param("record") FacilityFeeEntity record, @Param("example") FacilityFeeEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_facility_fee
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    int updateByExample(@Param("record") FacilityFeeEntity record, @Param("example") FacilityFeeEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_facility_fee
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    int updateByPrimaryKeySelective(FacilityFeeEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_facility_fee
     *
     * @mbg.generated Wed Dec 13 19:32:22 CST 2017
     */
    int updateByPrimaryKey(FacilityFeeEntity record);
    
	List<PairName> queryFields(Map<String, Object> map);
	List<PairName> queryFacilityNames(Map<String, Object> map);
	List<PairName> queryModels(Map<String, Object> map);
	List<PairName> queryAccuracys(Map<String, Object> map);
	List<PairName> queryTestRanges(Map<String, Object> map);
	List<PairName> queryFeeUnits(Map<String, Object> map);
	
	List<PairMulti> queryFeePriceAndUnit(List<String> fids);
}