package com.zheng.Utils;


import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.CellView;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;

/**
 * 生成Excel报表
 *
 * @author
 * @Version 1.0
 * @Data 2018/6/19 11:30
 */
public class UpLoadExcel {
    /**
     *
     * @param request request作用域
     * @param response response作用域
     * @param fileName 文件名字
     * @param title 文件请求头
     * @param dataList 数据
     * @return
     * @throws Exception
     */
    public static boolean exportData(HttpServletRequest request, HttpServletResponse response, String fileName,
                                     String[] title, List<String[]> dataList) throws Exception {
        OutputStream os = response.getOutputStream();// 取得输出流
        response.reset();// 清空输出流
        String titleName = fileName;
        // 下面是对中文文件名的处理
        response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
        fileName = java.net.URLEncoder.encode(fileName, "UTF-8"); // 经过处理的该数据，传到putExcel()方法会造成乱码，需在上面保存一下。
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "GBK") + ".xls");
        response.setContentType("application/msexcel");// 定义输出类型

        putExcel(os, titleName, title, dataList);

        return true;
    }

    public static void putExcel(OutputStream os, String fileName, String[] dataTitle, List<String[]> dataList) {
        // 创建工作薄
        WritableWorkbook workbook;
        try {
            workbook = Workbook.createWorkbook(os);
            // 创建新的一页
            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
            // 构造表头
            sheet.mergeCells(0, 0, dataTitle.length, 0); // 添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行

            WritableFont bold = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
            WritableCellFormat titleFormate = new WritableCellFormat(bold);// 生成一个单元格样式控制对象
            titleFormate.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
            titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
            Label title = new Label(0, 0, fileName, titleFormate);
            CellView cellView = new CellView();
            cellView.setAutosize(true); // 设置自动大小
            sheet.setRowView(0, 600, false);// 设置第一行的高度
            for (int i = 0; i < dataTitle.length; i++) {
                sheet.setColumnView(i, cellView);// 设置列宽
            }
            sheet.addCell(title);
            bold = new WritableFont(WritableFont.ARIAL, 14);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
            titleFormate = new WritableCellFormat(bold);// 生成一个单元格样式控制对象
            titleFormate.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
            titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中

            Label label;
            Number number;
            // 报表第一列表头
            label = new Label(0, 1, "序号", titleFormate);
            sheet.addCell(label);
            // 没有需要显示的数据，则在导出时文件里显示无数据。
//            if (dataTitle.length == 1 && Constants.NO_DATA_EXPORT.equals(dataTitle[0])) {
//                sheet.mergeCells(0, 1, dataTitle.length, 0);// 合并单元格
//                label = new Label(0, 1, Constants.NO_DATA_EXPORT, titleFormate);
//                sheet.addCell(label);
//            } else {
            // 设置列表头
            for (int i = 0; i < dataTitle.length; i++) {
                label = new Label(i + 1, 1, dataTitle[i], titleFormate);
                sheet.addCell(label);
            }
            // 设置列内容
            for (int j = 0; j < dataList.size(); j++) {
                // 序号
                number = new Number(0, j + 2, j + 1, titleFormate);
                sheet.addCell(number);
                for (int i = 0; i < dataList.get(j).length; i++) {
                    // 创建要显示的具体内容
                    label = new Label(i + 1, j + 2, dataList.get(j)[i], titleFormate);
                    sheet.addCell(label);
                }
            }
            //     }

            // 把创建的内容写入到输出流中，并关闭输出流
            workbook.write();
            workbook.close();
            os.close();
        } catch (WriteException e) {
            System.out.println("写入错误");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO错误");
        }
    }
}
