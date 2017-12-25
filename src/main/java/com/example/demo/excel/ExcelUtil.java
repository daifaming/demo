package com.example.demo.excel;

import com.example.demo.tools.DateUtils;
import com.example.demo.tools.PatternConstant;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class ExcelUtil
{

    private static Logger log = getLogger(ExcelUtil.class);

    /**
     * @param map
     * @param path
     * @MethodName : generateExcel
     * @Description : 生成excel
     */
    public static void generateExcel(Map<String, List<Object>> map, String path, String fileName)
    {

        //创建excel
        HSSFWorkbook wb = createExcel(map);
        //写入excel
        write(wb, path, fileName);
    }

    /**
     * @param map
     * @return
     * @MethodName : createExcel
     * @Description : 创建excel
     */
    private static HSSFWorkbook createExcel(Map<String, List<Object>> map)
    {
        //创建excel
        //创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();

        //遍历创建每一个sheet
        map.forEach((k, v) ->
        {
            HSSFSheet sheet = wb.createSheet(k);
            getRows(v, sheet);
        });

        return wb;

    }

    /**
     * @param datas
     * @param sheet
     * @MethodName : getRows
     * @Description : 遍历创建每一行
     */
    private static void getRows(List<Object> datas, HSSFSheet sheet)
    {
        datas.forEach(data ->
        {
            HSSFRow row = sheet.createRow(datas.indexOf(data));
            //遍历创建每一行
            getCells(data, row);
        });
    }

    /**
     * @param data
     * @param row
     * @MethodName : getCells
     * @Description : 遍历创建没一个cell
     */
    private static void getCells(Object data, HSSFRow row)
    {

        try
        {
            Class cls = data.getClass();
            // 获取类中的所有定义字段
            Field[] fields = cls.getDeclaredFields();
            List<Field> lists = Arrays.asList(fields);

            for (Field field : lists)
            {
                // 如果不为空，设置可见性，然后返回
                field.setAccessible(true);

                HSSFCell cell = row.createCell(lists.indexOf(field));
                String value = "" + field.get(data);
                cell.setCellValue(value);
            }
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }

    }

    /**
     * @param wb
     * @param path
     * @MethodName : getString
     * @Description : 将创建的excel写入文件中
     */
    private static void write(HSSFWorkbook wb, String path, String fileName)
    {

        try
        {
            String time = DateUtils.getLocalTimeStr(PatternConstant.yyyyMMddHHmmss);
            log.info("current time is {}", time);

            String targetPath = path + File.separator + fileName + time + ".xls";
            log.info("file path is {}", targetPath);

            //TODO 依次判断创建目录和文件

            File file = new File(targetPath);
            if (!file.exists())
            {
                boolean is = file.createNewFile();
                if (is)
                {
                    FileOutputStream output = new FileOutputStream(targetPath);
                    wb.write(output);
                    output.flush();
                }
            }

        }
        catch (IOException e)
        {
            log.info("write excel into file error！");
            e.printStackTrace();
        }
    }

}