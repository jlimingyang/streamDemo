package com.demo.item.test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestExcel {

    //显示的导出表的标题
    private String title;
    //导出表的列名
    private String[] rowName;

    private List<Object[]> dataList = new ArrayList<Object[]>();

    private OrderListExcelRespDto orderListExcelRespDto;

    //构造方法，传入要导出的数据
    public TestExcel(String title, String[] rowName, List<Object[]> dataList) {
        this.dataList = dataList;
        this.rowName = rowName;
        this.title = title;
    }

    public TestExcel(String[] rowName, OrderListExcelRespDto orderListExcelRespDto) {
        this.rowName = rowName;
        this.orderListExcelRespDto = orderListExcelRespDto;
        this.dataList = orderListExcelRespDto.getOrderLists();
    }

    /*
     * 导出数据
     * */
    public void export() throws Exception {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();                        // 创建工作簿对象
            HSSFSheet sheet = workbook.createSheet(orderListExcelRespDto.getActivityTitle());                     // 创建工作表

            // 产生表格标题行
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTiltle = rowm.createCell(0);
            rowm.setHeight((short) (25 * 35)); //设置高度
            //sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
            HSSFCellStyle style = this.getStyle(workbook);                    //单元格样式对象
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (rowName.length - 1)));
            cellTiltle.setCellStyle(columnTopStyle);
            cellTiltle.setCellValue(orderListExcelRespDto.getActivityAdress()+"!  "+orderListExcelRespDto.getActivityAdress()+"!");
            //标题2
            HSSFRow rowRowName1 = sheet.createRow(1);                // 在索引2的位置创建行(最顶端的行开始的第二行)
            rowRowName1.setHeight((short) (25 * 25)); //设置高度
            HSSFCell cellRowName1 = rowRowName1.createCell(0);                //创建列头对应个数的单元格
            cellRowName1.setCellType(HSSFCell.CELL_TYPE_STRING);                //设置列头单元格的数据类型
            HSSFRichTextString text1 = new HSSFRichTextString("时间");
            cellRowName1.setCellValue(text1);                                    //设置列头单元格的值
            cellRowName1.setCellStyle(columnTopStyle);                        //设置列头单元格样式
            HSSFCell cellRowName2 = rowRowName1.createCell(1);                //创建列头对应个数的单元格
            cellRowName2.setCellType(HSSFCell.CELL_TYPE_STRING);                //设置列头单元格的数据类型
            HSSFRichTextString text2 = new HSSFRichTextString(orderListExcelRespDto.getActivityTime());
            cellRowName2.setCellValue(text2);                                    //设置列头单元格的值
            cellRowName2.setCellStyle(columnTopStyle);

            //标题3
            HSSFRow rowRowName2 = sheet.createRow(2);                // 在索引2的位置创建行(最顶端的行开始的第二行)
            rowRowName2.setHeight((short) (25 * 25)); //设置高度
            HSSFCell cellRowName3 = rowRowName2.createCell(0);                //创建列头对应个数的单元格
            cellRowName3.setCellType(HSSFCell.CELL_TYPE_STRING);                //设置列头单元格的数据类型
            HSSFRichTextString text3 = new HSSFRichTextString("地点");
            cellRowName3.setCellValue(text3);                                    //设置列头单元格的值
            cellRowName3.setCellStyle(columnTopStyle);                        //设置列头单元格样式
            HSSFCell cellRowName4 = rowRowName2.createCell(1);                //创建列头对应个数的单元格
            cellRowName4.setCellType(HSSFCell.CELL_TYPE_STRING);                //设置列头单元格的数据类型
            HSSFRichTextString text4 = new HSSFRichTextString(orderListExcelRespDto.getActivityAdress());
            cellRowName4.setCellValue(text4);                                    //设置列头单元格的值
            cellRowName4.setCellStyle(columnTopStyle);

            //标题4
            HSSFRow rowRowName3 = sheet.createRow(3);                // 在索引2的位置创建行(最顶端的行开始的第二行)
            rowRowName3.setHeight((short) (25 * 25)); //设置高度
            HSSFCell cellRowName5 = rowRowName3.createCell(0);                //创建列头对应个数的单元格
            cellRowName5.setCellType(HSSFCell.CELL_TYPE_STRING);                //设置列头单元格的数据类型
            HSSFRichTextString text5 = new HSSFRichTextString("价格");
            cellRowName5.setCellValue(text5);                                    //设置列头单元格的值
            cellRowName5.setCellStyle(columnTopStyle);                        //设置列头单元格样式
            HSSFCell cellRowName6 = rowRowName3.createCell(1);                //创建列头对应个数的单元格
            cellRowName6.setCellType(HSSFCell.CELL_TYPE_STRING);                //设置列头单元格的数据类型
            HSSFRichTextString text6 = new HSSFRichTextString(orderListExcelRespDto.getPriceType());
            cellRowName6.setCellValue(text6);                                    //设置列头单元格的值
            cellRowName6.setCellStyle(columnTopStyle);

            //标题5
            HSSFRow rowRowName4 = sheet.createRow(4);                // 在索引2的位置创建行(最顶端的行开始的第二行)
            rowRowName4.setHeight((short) (25 * 25)); //设置高度
            HSSFCell cellRowName7 = rowRowName4.createCell(0);                //创建列头对应个数的单元格
            cellRowName7.setCellType(HSSFCell.CELL_TYPE_STRING);                //设置列头单元格的数据类型
            HSSFRichTextString text7 = new HSSFRichTextString("商家");
            cellRowName7.setCellValue(text7);                                    //设置列头单元格的值
            cellRowName7.setCellStyle(columnTopStyle);                        //设置列头单元格样式
            HSSFCell cellRowName8 = rowRowName4.createCell(1);                //创建列头对应个数的单元格
            cellRowName8.setCellType(HSSFCell.CELL_TYPE_STRING);                //设置列头单元格的数据类型
            HSSFRichTextString text8 = new HSSFRichTextString(orderListExcelRespDto.getCompany());
            cellRowName8.setCellValue(text8);                                    //设置列头单元格的值
            cellRowName8.setCellStyle(columnTopStyle);

            // 定义所需列数
            int columnNum = rowName.length;
            HSSFRow rowRowName = sheet.createRow(5);                // 在索引5的位置创建行(最顶端的行开始的第二行)
            rowRowName.setHeight((short) (25 * 25)); //设置高度

            // 将列头设置到sheet的单元格中
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellRowName = rowRowName.createCell(n);                //创建列头对应个数的单元格
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);                //设置列头单元格的数据类型
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text);                                    //设置列头单元格的值
                cellRowName.setCellStyle(columnTopStyle);                        //设置列头单元格样式
            }

            //将查询出的数据设置到sheet对应的单元格中
            for (int i = 0; i < dataList.size(); i++) {
                Object[] obj = dataList.get(i);//遍历每个对象
                HSSFRow row = sheet.createRow(i + 6);//创建所需的行数

                row.setHeight((short) (25 * 40)); //设置高度

                for (int j = 0; j < obj.length; j++) {
                    HSSFCell cell = null;   //设置单元格的数据类型
                    if (j == 0) {
                        cell = row.createCell(j, HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(i + 1);
                    } else {
                        cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                        if (!"".equals(obj[j]) && obj[j] != null) {
                            cell.setCellValue(obj[j].toString());                        //设置单元格的值
                        }
                    }
                    cell.setCellStyle(style);                                    //设置单元格样式
                    //加一行备注
                }
            }
            //让列宽随着导出的列长自动适应
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    //当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            if(currentCell != null) {
                                int length = currentCell.getStringCellValue().getBytes().length;
                                if (columnWidth < length) {
                                    columnWidth = length;
                                }
                            }
                        }
                    }
                }
                if (colNum == 0) {
                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 128);
                } else {
                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
                }


            }

            if (workbook != null) {
                try {
//                    String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
//                    String headStr = "attachment; filename=\"" + fileName + "\"";
//                    response = getResponse();
//                    response.setContentType("APPLICATION/OCTET-STREAM");
//                    response.setHeader("Content-Disposition", headStr);
//                    OutputStream out = response.getOutputStream();
//                    workbook.write(out);
                    FileOutputStream out = new FileOutputStream( "订单"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() + ".xls");
                    workbook.write(out);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     * 列头单元格样式
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
//        font.setFontHeightInPoints((short) 11);
//        //字体加粗
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        //设置字体名字
//        font.setFontName("Courier New");
//        //设置样式;
//        HSSFCellStyle style = workbook.createCellStyle();
//        //设置底边框;
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        //设置底边框颜色;
//        style.setBottomBorderColor(HSSFColor.BLACK.index);
//        //设置左边框;
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        //设置左边框颜色;
//        style.setLeftBorderColor(HSSFColor.BLACK.index);
//        //设置右边框;
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        //设置右边框颜色;
//        style.setRightBorderColor(HSSFColor.BLACK.index);
//        //设置顶边框;
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        //设置顶边框颜色;
//        style.setTopBorderColor(HSSFColor.BLACK.index);
//        //在样式用应用设置的字体;
//        style.setFont(font);
//        //设置自动换行;
//        style.setWrapText(false);
//        //设置水平对齐的样式为居中对齐;
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        //设置垂直对齐的样式为居中对齐;
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//
//        //设置单元格背景颜色
////        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        return null;

    }

    /*
     * 列数据信息单元格样式
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        //font.setFontHeightInPoints((short)10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
//        //设置底边框;
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        //设置底边框颜色;
//        style.setBottomBorderColor(HSSFColor.BLACK.index);
//        //设置左边框;
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        //设置左边框颜色;
//        style.setLeftBorderColor(HSSFColor.BLACK.index);
//        //设置右边框;
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        //设置右边框颜色;
//        style.setRightBorderColor(HSSFColor.BLACK.index);
//        //设置顶边框;
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        //设置顶边框颜色;
//        style.setTopBorderColor(HSSFColor.BLACK.index);
//        //在样式用应用设置的字体;
//        style.setFont(font);
//        //设置自动换行;
//        style.setWrapText(false);
//        //设置水平对齐的样式为居中对齐;
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        //设置垂直对齐的样式为居中对齐;
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//
//        return style;
        return null;
    }

    public static void main(String[] args) throws Exception {
        String[] rowsName = new String[]{"序号", "用户", "电话", "费用", "订单号", "保单号", "签退", "来源商家", "其他", "备注"};
        OrderListExcelRespDto respDto = new OrderListExcelRespDto();
        respDto.genData();
        new TestExcel(rowsName,respDto).export();
    }
}