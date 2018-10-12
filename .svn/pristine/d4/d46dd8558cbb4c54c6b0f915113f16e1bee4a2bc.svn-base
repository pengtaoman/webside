package com.rkylin.cells.excel.poi;

import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rkylin.cells.common.Workbook;
import com.rkylin.cells.excel.ExcelReaderException;

public class PoiExcelReader {
	public static Workbook read(InputStream input) throws ExcelReaderException {
		try {
			org.apache.poi.ss.usermodel.Workbook poiWorkbook = WorkbookFactory.create(input);
			if(poiWorkbook instanceof HSSFWorkbook){
				return new HSSFExcelReader().read((HSSFWorkbook)poiWorkbook);
			}else{
				return new XSSFExcelReader().read((XSSFWorkbook)poiWorkbook);
			}
		} catch (Exception e) {
			throw new ExcelReaderException(e);
		}
	}
}
