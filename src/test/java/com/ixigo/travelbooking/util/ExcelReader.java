package com.ixigo.travelbooking.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

	private static final Logger log = LogManager.getLogger(ExcelReader.class);

	PropertyFileReader prop = new PropertyFileReader();

	public List<Map<String, String>> readExcel(String sheetName, List<String> columNames) throws IOException {

		log.info("Reading Excel sheet={}", sheetName);
		String filePath = prop.getFromPropertyFile("ExcelFilePath");

		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			log.error("Sheet not found: {}", sheetName);
			throw new IllegalArgumentException("Sheet not found: " + sheetName);
		}
		Row headerRow = sheet.getRow(0);

		if (headerRow == null) {
			log.error("Header row missing in sheet={}", sheetName);
			throw new IllegalArgumentException("Header row is missing in sheet: " + sheetName);
		}
		int[] columnIndexes = new int[columNames.size()];

		for (int i = 0; i < columNames.size(); i++) {
			columnIndexes[i] = -1;
			for (Cell cell : headerRow) {
				if (cell.getStringCellValue().trim().equals(columNames.get(i))) {
					columnIndexes[i] = cell.getColumnIndex();//saving the column numbers matching the headers
					break;
				}
			}

		}

		DataFormatter formatter = new DataFormatter();
		List<Map<String, String>> result = new ArrayList<>();

		for (int r = 1; r <= sheet.getLastRowNum(); r++) {
			Row row = sheet.getRow(r);
			if (row == null)
				continue;
			Map<String, String> rowData = new LinkedHashMap<>();
			for (int c = 0; c < columNames.size(); c++) {
				Cell cell = row.getCell(columnIndexes[c]);
				String value = formatter.formatCellValue(cell);
				System.out.print(columNames.get(c) + ": " + value + "  ");
				rowData.put(columNames.get(c), value);

			}

			result.add(rowData);
			System.out.println();
		}

		workbook.close();
		fis.close();
		log.info("Completed reading Excel sheet={}", sheetName);
		return result;
	}
}
