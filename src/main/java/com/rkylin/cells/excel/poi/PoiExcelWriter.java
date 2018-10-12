package com.rkylin.cells.excel.poi;

import java.io.IOException;
import java.io.OutputStream;

import com.rkylin.cells.common.Workbook;
import com.rkylin.cells.excel.ExcelWriter;
import com.rkylin.cells.excel.WorkbookConvertor;

public class PoiExcelWriter implements ExcelWriter {
	private OutputStream os = null;
	private WorkbookConvertor poiWorkbookConvertor = null;
	
	public PoiExcelWriter(OutputStream os){
		this.os = os;
		poiWorkbookConvertor = new PoiWorkbookConvertor();
	}

	@Override
	public void write(Workbook workbook, String fileName) {
		org.apache.poi.ss.usermodel.Workbook poiWorkbook = 
				(org.apache.poi.ss.usermodel.Workbook)poiWorkbookConvertor.convert(workbook, fileName);
		try {
			poiWorkbook.write(os);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
