package com.rkylin.cells.excel;

import com.rkylin.cells.common.Workbook;

public interface ExcelWriter {

	public void write(Workbook workbook, String fileName);

}
