package com.rkylin.cells.excel.poi;

import java.io.FileInputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.rkylin.cells.common.Border;
import com.rkylin.cells.common.BorderCollection;
import com.rkylin.cells.common.BorderLineType;
import com.rkylin.cells.common.BorderType;
import com.rkylin.cells.common.Cell;
import com.rkylin.cells.common.CellArea;
import com.rkylin.cells.common.CellValueType;
import com.rkylin.cells.common.Cells;
import com.rkylin.cells.common.Color;
import com.rkylin.cells.common.Font;
import com.rkylin.cells.common.Row;
import com.rkylin.cells.common.RowCollection;
import com.rkylin.cells.common.Style;
import com.rkylin.cells.common.StyleCollection;
import com.rkylin.cells.common.TextAlignmentType;
import com.rkylin.cells.common.Workbook;
import com.rkylin.cells.common.Worksheet;
import com.rkylin.cells.common.WorksheetCollection;
import com.rkylin.cells.excel.ExcelReaderException;
import com.webside.util.StringUtils;

public class HSSFExcelReader {
	private HSSFWorkbook poiWorkbook = null;
	private HSSFSheet poiWorksheet = null;
	private HSSFCell poiCell = null;
	private HSSFCellStyle poiStyle = null;
	
	private Workbook workbook = null;
	private StyleCollection styleCollection = null;
	private WorksheetCollection worksheets = null;
	private Worksheet worksheet = null;
	private Cells cells = null;
	private RowCollection rowCollection = null;
	private Row row = null;
	private Cell cell = null;
	private static NumberFormat defaultNumberFormat = java.text.NumberFormat.getInstance();
	static {
		defaultNumberFormat.setGroupingUsed(false); 
	}

	public Workbook read(HSSFWorkbook poiWorkbook) throws ExcelReaderException{
		try {
			this.poiWorkbook = poiWorkbook;
			workbook = new Workbook();
			readWorkbook();
			return workbook;
		} catch (Exception e) {
			throw new ExcelReaderException(e);
		}
	}
	
	private void readWorkbook() throws ExcelReaderException {
		readStyleCollection();

		Style style = Style.createDefaultStyle();
		styleCollection.setDefaultStyle(style);

		readWorksheets();
	}

	private void readStyleCollection() {
		styleCollection = new StyleCollection();
		workbook.setStyleCollection(styleCollection);
	}

	private void readWorksheets() {
		worksheets = new WorksheetCollection();
		workbook.setWorksheets(worksheets);

		int count = poiWorkbook.getNumberOfSheets();
		for (int i = 0; i < count; i++) {
			poiWorksheet = poiWorkbook.getSheetAt(i);
			readWorksheet(poiWorksheet);
		}
	}

	private void readWorksheet(HSSFSheet poiWorksheet) {
		worksheet = new Worksheet();
		worksheets.addWorksheet(worksheet);

		worksheet.setName(poiWorksheet.getSheetName());

		cells = new Cells();
		worksheet.setCells(cells);

		int firstRowNum = poiWorksheet.getFirstRowNum();
		int lastRowNum = poiWorksheet.getLastRowNum();

		// 读取列宽信息
		int maxColumnNum = -1;
		for (int i = firstRowNum; i <= lastRowNum ; i++) {
			HSSFRow row = poiWorksheet.getRow(i);
			if (row != null) {
				int lastCellNum = row.getLastCellNum();
				if (lastCellNum > maxColumnNum) {
					maxColumnNum = lastCellNum;
				}
			}
		}
		int[] columnWidths = new int[maxColumnNum + 1];
		for (int i = 0; i <= maxColumnNum; i++) {
			columnWidths[i] = poiWorksheet.getColumnWidth(i);
		}
		cells.setColumnWidths(columnWidths);

		// 读取sheet中的行信息
		rowCollection = new RowCollection();
		cells.setRowCollection(rowCollection);
		for (int i = 0; i < firstRowNum; i++) {
			row = new Row();
			row.setHeight(poiWorksheet.getDefaultRowHeight());
			rowCollection.addRow(row);
		}
		for (int i = firstRowNum; i <= lastRowNum; i++) {
			HSSFRow tmp = poiWorksheet.getRow(i);
			if(tmp != null) {
				row = readRow(tmp, i);
			}
		}		
		
		// 读取合并单元格信息
		readMergedCells();
	}

	/**
	 * 读取合并单元格信息
	 */
	private void readMergedCells() {
		int numMergedRegions = poiWorksheet.getNumMergedRegions();
		for (int i = 0; i < numMergedRegions; i++) {
			org.apache.poi.ss.util.CellRangeAddress poiMergedCell = poiWorksheet.getMergedRegion(i);
			CellArea cellArea = new CellArea();
			cellArea.setStartRow(poiMergedCell.getFirstRow());
			cellArea.setEndRow(poiMergedCell.getLastRow());
			cellArea.setStartColumn(poiMergedCell.getFirstColumn());
			cellArea.setEndColumn(poiMergedCell.getLastColumn());
			cells.addMergedCell(cellArea);
		}
	}

	private Row readRow(HSSFRow poiRow, int rowNo) {
		row = new Row();
		rowCollection.addRow(row);

		row.setHeight(poiRow.getHeight());

		int firstCellNum = poiRow.getFirstCellNum();
		if (firstCellNum >= 0) {
			for (int i = 0; i < firstCellNum; i++) {
				Cell defaultCell = createDefaultCell(rowNo, i);
				row.addCell(defaultCell);
			}

			int lastCellNum = poiRow.getLastCellNum();
			for (int i = firstCellNum; i < lastCellNum; i++) {
				poiCell = poiRow.getCell(i);
				if (poiCell == null) {
					cell = createDefaultCell(rowNo, i);
				} else {
					cell = readCell(poiCell);
				}
				row.addCell(cell);
			}
		}

		return row;
	}

	private Cell createDefaultCell(int rowNo, int colNo) {
		Cell defaultCell = new Cell();
		defaultCell.setRowNo(rowNo);
		defaultCell.setColumnNo(colNo);
		defaultCell.setStyle(workbook.getDefaultStyle());
		return defaultCell;
	}

	private Cell readCell(HSSFCell poiCell) {
		cell = new Cell();

		int column = poiCell.getColumnIndex();
		cell.setColumnNo(column);
		cell.setRowNo(poiCell.getRowIndex());
		int cellType = poiCell.getCellType();

		switch (cellType) {
		case HSSFCell.CELL_TYPE_BLANK: {
			cell.setType(CellValueType.IS_NULL);
			cell.setValue(StringUtils.EMPTY);
			break;
		}
		case HSSFCell.CELL_TYPE_NUMERIC: {
			// mod by kcw start at 2016/08/01
			short format = poiCell.getCellStyle().getDataFormat();
			double value = poiCell.getNumericCellValue();
			if (format == 14 || format == 31 || format == 57 || format == 58) {
				// 日期
				cell.setType(CellValueType.IS_DATE_TIME);
				cell.setValue(new SimpleDateFormat("yyyy-MM-dd").
						format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)));
			} else if (format == 20 || format == 32) {
				cell.setType(CellValueType.IS_DATE_TIME);
				cell.setValue(new SimpleDateFormat("HH:mm").
						format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)));
			} else {
				cell.setType(CellValueType.IS_STRING);
				cell.setValue(defaultNumberFormat.format(value));
			}			
			// mod by kcw end at 2016/08/01
//			cell.setType(CellValueType.IS_NUMERIC);
//			cell.setValue(poiCell.getNumericCellValue());
			break;
		}
		case HSSFCell.CELL_TYPE_STRING: {
			cell.setType(CellValueType.IS_STRING);
			cell.setValue(poiCell.getStringCellValue());
			break;
		}
		case HSSFCell.CELL_TYPE_FORMULA: {
			cell.setType(CellValueType.IS_STRING);
			cell.setValue(poiCell.getCellFormula());
			break;
		}
		case HSSFCell.CELL_TYPE_BOOLEAN: {
			cell.setType(CellValueType.IS_BOOL);
			cell.setValue(poiCell.getBooleanCellValue());
			break;
		}
		case HSSFCell.CELL_TYPE_ERROR: {
			cell.setType(CellValueType.IS_ERROR);
			cell.setValue(StringUtils.EMPTY);
			break;
		}
		}
		cell.setWidth(cells.getColumnWidth(column));
		cell.setHeight(row.getHeight());

		poiStyle = poiCell.getCellStyle();
		Style style = readStyle(poiStyle);
		style = styleCollection.addStyle(style);
		cell.setStyle(style);

		return cell;
	}

	private Style readStyle(HSSFCellStyle poiStyle) {
		Style style = new Style();

		HSSFColor poiBackgroundColor = poiStyle.getFillForegroundColorColor();
		if (poiBackgroundColor != null) {
			style.setBackgroundColor(readColor(poiBackgroundColor));
		}

		// 水平对齐类型
		switch (poiStyle.getAlignment()) {
		case HSSFCellStyle.ALIGN_LEFT: {
			style.setHorizontalAlignment(TextAlignmentType.LEFT);
			break;
		}
		case HSSFCellStyle.ALIGN_RIGHT: {
			style.setHorizontalAlignment(TextAlignmentType.RIGHT);
			break;
		}
		case HSSFCellStyle.ALIGN_GENERAL: {
			style.setHorizontalAlignment(TextAlignmentType.GENERAL);
			break;
		}
		default: {
			style.setHorizontalAlignment(TextAlignmentType.CENTER);
		}
		}

		// 垂直对齐类型
		switch (poiStyle.getVerticalAlignment()) {
		case HSSFCellStyle.VERTICAL_BOTTOM: {
			style.setVerticalAlignment(TextAlignmentType.BOTTOM);
			break;
		}
		case HSSFCellStyle.VERTICAL_TOP: {
			style.setVerticalAlignment(TextAlignmentType.TOP);
			break;
		}
		default: {
			style.setVerticalAlignment(TextAlignmentType.CENTER);
		}
		}

		// 边框类型
		BorderCollection borderCollection = new BorderCollection();
		// 上边框
		int borderType = poiStyle.getBorderTop();
		short borderColor = poiStyle.getTopBorderColor();
		Border border = createBorder(borderType, borderColor);
		borderCollection.setByBorderType(BorderType.TOP_BORDER, border);
		// 下边框
		borderType = poiStyle.getBorderBottom();
		borderColor = poiStyle.getBottomBorderColor();
		border = createBorder(borderType, borderColor);
		borderCollection.setByBorderType(BorderType.BOTTOM_BORDER, border);
		// 左边框
		borderType = poiStyle.getBorderLeft();
		borderColor = poiStyle.getLeftBorderColor();
		border = createBorder(borderType, borderColor);
		borderCollection.setByBorderType(BorderType.LEFT_BORDER, border);
		// 右边框
		borderType = poiStyle.getBorderRight();
		borderColor = poiStyle.getRightBorderColor();
		border = createBorder(borderType, borderColor);
		borderCollection.setByBorderType(BorderType.RIGHT_BORDER, border);
		style.setBorderCollection(borderCollection);
		
		// 字体
		HSSFFont poiFont = poiStyle.getFont(poiWorkbook);
		style.setFont(readFont(poiFont));

		return style;
	}

	private Border createBorder(int borderType, short borderColor) {
		Border border = new Border();
		border.setColor(readColor(borderColor));

		switch (borderType) {
		case HSSFCellStyle.BORDER_NONE: {
			border.setLineStyle(BorderLineType.NONE);
			break;
		}
		case HSSFCellStyle.BORDER_THIN: {
			border.setLineStyle(BorderLineType.THIN);
			break;
		}
		case HSSFCellStyle.BORDER_MEDIUM: {
			border.setLineStyle(BorderLineType.MEDIUM);
			break;
		}
		case HSSFCellStyle.BORDER_DASHED: {
			border.setLineStyle(BorderLineType.DASHED);
			break;
		}
		case HSSFCellStyle.BORDER_DOTTED: {
			border.setLineStyle(BorderLineType.DOTTED);
			break;
		}
		case HSSFCellStyle.BORDER_THICK: {
			border.setLineStyle(BorderLineType.THICK);
			break;
		}
		case HSSFCellStyle.BORDER_DOUBLE: {
			border.setLineStyle(BorderLineType.DOUBLE);
			break;
		}
		case HSSFCellStyle.BORDER_HAIR: {
			border.setLineStyle(BorderLineType.HAIR);
			break;
		}
		case HSSFCellStyle.BORDER_MEDIUM_DASHED: {
			border.setLineStyle(BorderLineType.MEDIUM_DASHED);
			break;
		}
		case HSSFCellStyle.BORDER_DASH_DOT: {
			border.setLineStyle(BorderLineType.DASH_DOT);
			break;
		}
		case HSSFCellStyle.BORDER_MEDIUM_DASH_DOT: {
			border.setLineStyle(BorderLineType.MEDIUM_DASH_DOT);
			break;
		}
		case HSSFCellStyle.BORDER_DASH_DOT_DOT: {
			border.setLineStyle(BorderLineType.DASH_DOT_DOT);
			break;
		}
		case HSSFCellStyle.BORDER_MEDIUM_DASH_DOT_DOT: {
			border.setLineStyle(BorderLineType.MEDIUM_DASH_DOT_DOT);
			break;
		}
		case HSSFCellStyle.BORDER_SLANTED_DASH_DOT: {
			border.setLineStyle(BorderLineType.SLANTED_DASH_DOT);
			break;
		}
		default: {
			border.setLineStyle(BorderLineType.NONE);
		}
		}

		return border;
	}

	private Font readFont(HSSFFont poiFont) {
		Font font = new Font();
		font.setColor(readColor(poiFont.getColor()));
		font.setName(poiFont.getFontName());
		font.setSize(poiFont.getFontHeight());
		font.setUnderline(poiFont.getUnderline() != HSSFFont.U_NONE);
		font.setItalic(poiFont.getItalic());
		font.setBold(poiFont.getBoldweight() == HSSFFont.BOLDWEIGHT_BOLD);
		return font;
	}
	
	private Color readColor(short borderColor) {
		return readColor(poiWorkbook.getCustomPalette().getColor(borderColor));
	}

	/**
	 * 颜色转化为uof颜色
	 * 
	 * @param poiColor HSSFWorkbook中的颜色
	 * @return uof颜色
	 */
	private Color readColor(HSSFColor poiColor) {
		if(poiColor == null){
			return null;
		}
		
		// 一定是rgb
		short[] rgb = poiColor.getTriplet();
		return Color.fromARGB(rgb[0], rgb[1], rgb[2]);
	}

	public static void main(String[] args) {
		try {
			String filePath = "E:/D.xlsx";
			FileInputStream input = new FileInputStream(filePath);
			PoiExcelReader.read(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
