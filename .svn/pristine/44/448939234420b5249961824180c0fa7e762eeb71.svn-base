package com.rkylin.cells.excel.poi;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rkylin.cells.common.Cell;
import com.rkylin.cells.common.CellArea;
import com.rkylin.cells.common.CellValueType;
import com.rkylin.cells.common.Cells;
import com.rkylin.cells.common.Row;
import com.rkylin.cells.common.RowCollection;
import com.rkylin.cells.common.Workbook;
import com.rkylin.cells.common.Worksheet;
import com.rkylin.cells.common.WorksheetCollection;
import com.rkylin.cells.excel.WorkbookConvertor;

public class PoiWorkbookConvertor implements WorkbookConvertor {

	@Override
	public org.apache.poi.ss.usermodel.Workbook convert(Workbook workbook, String fileName){

		// 生成poi的workbook
		org.apache.poi.ss.usermodel.Workbook poiWorkbook = this.createPoiWorkbook(fileName);

		// 取得源workbook中的sheet集合
		WorksheetCollection worksheets = workbook.getWorksheets();
		long t0 = System.currentTimeMillis();
		long tSetValue = t0;
		long tMerge = t0;
		// 遍历sheet集合
		if(worksheets != null){
			for(int i = 0; i < worksheets.getCount(); i++){
	
				// 取得单一sheet
				Worksheet worksheet = worksheets.get(i);
				if(worksheet != null){

					// 在poiWorkbook中生成一个与源workbook中同名的sheet
					org.apache.poi.ss.usermodel.Sheet poiSheet = poiWorkbook.createSheet(worksheet.getName());

					// 取得源sheet中的cells集合
					Cells cells = worksheet.getCells();
					if(cells != null){

						// 取得数据行
						RowCollection rowCollection = cells.getRowCollection();
						int rowCnt = rowCollection.getCount();
						// 取得单一行
						Row firstRow = rowCollection.getRow(0);
						int clmCnt = firstRow.getCount();
						
						// 设定poirow的行高
						poiSheet.setDefaultRowHeight((short)firstRow.getHeight());
						
						if(rowCollection != null){
							// 遍历数据行
							for(int j = 0; j < rowCnt; j++){

								// 取得单一行
								Row row = rowCollection.getRow(j);
								if(row != null){
									// 在poisheet中创建一行
									org.apache.poi.ss.usermodel.Row poiRow = poiSheet.createRow(j);
									
									// 遍历行中单元格
									for(int k = 0; k < clmCnt; k++){
										
										// 取得单元格
										Cell cell = row.getCell(k);
										// 在poirow中创建单元格
										org.apache.poi.ss.usermodel.Cell poiCell = poiRow.createCell(k);
								
										// 单元格赋值 TODO：类型匹配尚未实现，暂定只有String|数值2种值类型
										if (cell != null) {
											Object value = cell.getValue();
											if (value != null) {
												String valueStr = value.toString();
												if (StringUtils.isNotEmpty(valueStr)) {
													if (CellValueType.IS_NUMERIC == cell.getType()) {
														// 此处需要格式化功能，以后需要完善
														try {
															poiCell.setCellValue(Double.valueOf(valueStr));
														} catch (Exception e) {
															poiCell.setCellValue(valueStr);
														}
													} else {
														poiCell.setCellValue(valueStr);
														DataFormat format = poiWorkbook.createDataFormat();
														CellStyle cellStyle = poiWorkbook.createCellStyle();
														cellStyle.setDataFormat(format.getFormat("@"));
														poiCell.setCellStyle(cellStyle);
													}
												} else {
													poiCell.setCellValue("");
												}
											} else {
												poiCell.setCellValue("");
											}

										}
									}
								}								
							}
						}
						
						tSetValue = System.currentTimeMillis();
						
						// 合并单元格
						List<CellArea> cellAreas =  cells.getMergedCells();
						if(cellAreas != null && cellAreas.size() > 0){
							for(int j = 0; j < cellAreas.size(); j++){
								CellArea cellArea = cellAreas.get(j);
								CellRangeAddress cellRangeAddress = 
										new CellRangeAddress(cellArea.getStartRow(),cellArea.getEndRow(),
															cellArea.getStartColumn(),cellArea.getEndColumn());
								poiSheet.addMergedRegion(cellRangeAddress);
							}
						}
						
						tMerge = System.currentTimeMillis();
					}
				}
			}
		}
		
		System.out.println("####POI####----SetValue:" + (tSetValue - t0));
		System.out.println("####POI####----MergeCell:" + (tMerge - tSetValue));

		return poiWorkbook;
	}
	
	private org.apache.poi.ss.usermodel.Workbook createPoiWorkbook(String fileName){
		org.apache.poi.ss.usermodel.Workbook workbook = null;
		if(fileName != null && fileName.endsWith(".xlsx")){
			workbook = new XSSFWorkbook();
		} else {
			workbook = new HSSFWorkbook();
		}
		return workbook;
	}

	private CellStyle setCellStyle(CellStyle cellStyle, Font font, String rowType){
		return setIndexDefaultCellStyle(cellStyle, font, rowType);
	}
	
	private CellStyle setIndexDefaultCellStyle(CellStyle cellStyle, Font font, String rowType){
		// 边框
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		
		// 居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
		// 背景色
		if("title".equals(rowType)){
			cellStyle.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//		}else if("stat".equals(rowType)){
//			cellStyle.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
//		}else if("data2".equals(rowType)){
//			cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		} else {
			cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}

		
		// 字体
		font.setFontName("黑体");
		if("title".equals(rowType)){
			font.setColor(IndexedColors.WHITE.getIndex());
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//		}else if("stat".equals(rowType)){
//			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		}
		cellStyle.setFont(font);
		
		return cellStyle;
	}
}
