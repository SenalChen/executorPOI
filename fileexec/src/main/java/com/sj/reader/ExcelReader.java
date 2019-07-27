package com.sj.reader;

import com.alibaba.fastjson.JSONObject;
import com.sj.utils.TransUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Author ChenSijia
 * @Date 2019/7/9 12:24
 */
public class ExcelReader {

    public static void main(String[] args) throws IOException {
        ExcelReader excelReader = new ExcelReader();
        excelReader.test2();
    }


    private void test2() throws IOException {
        String filePath = "H:\\项目文档\\XDA\\XDA项目元数据表.xlsx";
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> iterator = sheet.rowIterator();
        Map<String, Object> map = new HashMap<>();
        List<String> firstRow = null;
        if (iterator.hasNext()){
            firstRow = transCellIt2StringIt(iterator.next().cellIterator());

        }
        List<Map<String, String>> mapList = new ArrayList<>();
        while (iterator.hasNext()){
            List<String> otherRowList = transCellIt2StringIt(iterator.next().cellIterator());
            mapList.add(TransUtils.transTwoList2Map(firstRow, otherRowList));
        }
        System.out.println(JSONObject.toJSONString(mapList));

    }

    private List<String> transCellIt2StringIt(Iterator<Cell> iterator){
        List<String> list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(transCellType2String(iterator.next()));
        }
        return list;
    }

    private String transCellType2String(Cell cell){
        String cellValue;
        switch (cell.getCellTypeEnum()){
            case NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK: //空值
                cellValue = "";
                break;
            case ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}
