package com.turing.website.util;

import com.turing.website.dao.ResumeDao;
import com.turing.website.entity.Resume;
import com.turing.website.service.guest.GuestResumeService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/22  8:19
 */
@Service
@Transactional
public class ExcelUtil {


    public static  void creatExcel(String path,String [] header,String tableName,List<Resume> resumes){
        //声明一个工作簿
        Workbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        Sheet sheet = workbook.createSheet(tableName);

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);
        //创建标题的显示样式
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //创建第一行表头
        Row headrow = sheet.createRow(0);

        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
           Cell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
        }

        //获取所有的employee
        for (int i = 0; i < resumes.size(); i++) {
            //创建一行
            Row row1 = sheet.createRow(i + 1);
            //第一列创建并赋值
            row1.createCell(0).setCellValue(new HSSFRichTextString(resumes.get(i).getResumeName()));
            row1.createCell(1).setCellValue(new HSSFRichTextString(resumes.get(i).getResumeStudentId()));
            row1.createCell(2).setCellValue(new HSSFRichTextString(resumes.get(i).getResumeTelephone()));
            row1.createCell(3).setCellValue(new HSSFRichTextString(resumes.get(i).getResumeMajor()));
            row1.createCell(4).setCellValue(new HSSFRichTextString(resumes.get(i).getResumeDirect()));
            row1.createCell(5).setCellValue(new HSSFRichTextString(resumes.get(i).getResumeEvaluation()));
            row1.createCell(6).setCellValue(new HSSFRichTextString(resumes.get(i).getResumeSkills()));
            row1.createCell(7).setCellValue(new HSSFRichTextString(resumes.get(i).getResumeExpect()));
            row1.createCell(8).setCellValue(new HSSFRichTextString(resumes.get(i).getResumeExp()));
            row1.createCell(9).setCellValue(new HSSFRichTextString(resumes.get(i).getResumeOther()));
        }
        buildExcelFile(path,workbook);

    }
    /**
     * 生成excel文件
     * @param path 生成excel路径
     * @param wb
     */
    private static  void  buildExcelFile(String path, Workbook wb){

        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        try {
            wb.write(new FileOutputStream(file));
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
