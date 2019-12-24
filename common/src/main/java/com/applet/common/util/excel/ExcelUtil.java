package com.applet.common.util.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: EXCEL文件工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-09-12 11:19
 **/
public class ExcelUtil {

    /**
     * 创建excel文档，
     *
     * @param list        数据
     * @param keys        list中map的key数组集合
     * @param columnNames excel的列名
     */
    public static Workbook createWorkBook(String sheetName, List<Map<String, Object>> list, String[] keys, String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(sheetName);
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int i = 0; i < keys.length; i++) {
            sheet.setColumnWidth((short) i, (short) (38 * 155));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // Font f3=wb.createFont();
        // f3.setFontHeightInPoints((short) 10);
        // f3.setColor(IndexedColors.RED.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        // 设置列名
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        // 设置每行每列的值
        for (short i = 1; i < list.size() + 1; i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            // 在row行上创建一个方格
            for (short j = 0; j < keys.length; j++) {
                Cell cell = row1.createCell(j);
                if (list.get(i - 1).get(keys[j]) instanceof Double) {
                    cell.setCellValue(list.get(i - 1).get(keys[j]) == null ? 0 : Double.parseDouble(String.valueOf(list.get(i - 1).get(keys[j]))));
                } else {
                    cell.setCellValue(list.get(i - 1).get(keys[j]) == null ? "" : String.valueOf(list.get(i - 1).get(keys[j])));
                }
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }


    /**
     * 创建excel文档，
     *
     * @param sheetName
     * @param list        数据
     * @param keys        list中map的key数组集合
     * @param columnNames excel的列名
     */
    public static Workbook createWorkBook(String sheetName, List<Map<String, Object>> list, String[] keys, String columnNames[], String statisticStr) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(sheetName);
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int i = 0; i < keys.length; i++) {
            sheet.setColumnWidth((short) i, (short) (38 * 155));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());


        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);


        // 设置列名
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        // 设置每行每列的值
        for (short i = 1; i < list.size() + 1; i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            // 在row行上创建一个方格
            for (short j = 0; j < keys.length; j++) {
                Cell cell = row1.createCell(j);
                if (list.get(i - 1).get(keys[j]) instanceof Double) {
                    cell.setCellValue(list.get(i - 1).get(keys[j]) == null ? 0 : Double.parseDouble(String.valueOf(list.get(i - 1).get(keys[j]))));
                } else {
                    cell.setCellValue(list.get(i - 1).get(keys[j]) == null ? "" : String.valueOf(list.get(i - 1).get(keys[j])));
                }
                cell.setCellStyle(cs2);
            }
        }
        //统计行
        Row tjRow = sheet.createRow(list.size() + 2);
        Cell tjCell = tjRow.createCell(0);
        tjCell.setCellValue(statisticStr);
        sheet.addMergedRegion(new CellRangeAddress(list.size() + 2, list.size() + 2, 0, keys.length - 1));
        return wb;
    }


    public static XSSFWorkbook creat2007Excel(List<Map<String, Object>> list, String[] keys, String columnNames[]) throws FileNotFoundException, IOException {
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet();// 创建一个工作薄对象
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < keys.length; i++) {
            sheet.setColumnWidth((short) i, (short) (38 * 155));
        }


        // 创建两种单元格格式
        CellStyle cs = workBook.createCellStyle();
        CellStyle cs2 = workBook.createCellStyle();

        // 创建两种字体
        Font f = workBook.createFont();
        Font f2 = workBook.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // Font f3=wb.createFont();
        // f3.setFontHeightInPoints((short) 10);
        // f3.setColor(IndexedColors.RED.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置列名
        for (int i = 0; i < columnNames.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        // 设置每行每列的值
        for (int i = 1; i < list.size() + 1; i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            XSSFRow row1 = sheet.createRow((int) i);
            // 在row行上创建一个方格
            for (short j = 0; j < keys.length; j++) {
                XSSFCell cell = row1.createCell(j);
                if (list.get(i - 1).get(keys[j]) instanceof Double) {
                    cell.setCellValue(list.get(i - 1).get(keys[j]) == null ? 0 : Double.parseDouble(String.valueOf(list.get(i - 1).get(keys[j]))));
                } else {
                    cell.setCellValue(list.get(i - 1).get(keys[j]) == null ? "" : String.valueOf(list.get(i - 1).get(keys[j])));
                }
                cell.setCellStyle(cs2);
            }
        }
        return workBook;
    }
}
