package com.example.demo.service;

import com.example.demo.excel.ExcelUtil;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 数据重新编排
 */
@Service
@Configuration
@PropertySource(value = "classpath:application-dev.yml", ignoreResourceNotFound = true)
public class ExcelService
{
    // 日志
    private static Logger log = getLogger(ExcelService.class);

    private static String path;

    private static String fileName;

    private static String sheet;

    @Value("${excel.out.path}")
    public void setPath(String path)
    {
        this.path = path;
    }

    @Value("${excel.out.fileName}")
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    @Value("${excel.out.sheet}")
    public void setSheet(String sheet)
    {
        this.sheet = sheet;
    }

    @Autowired
    private UserRepository userRepository;

    public Map<String, List<Object>> getExcelMap()
    {
        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        List<Object> listsA = this.getExcelList();
        log.info("listA is: {}", listsA);

        map.put(sheet, listsA);
        return map;
    }

    public List<Object> getExcelList()
    {
        List<Object> lists = userRepository.findAll();
        return lists;
    }

    public void generateExcel()
    {
        Map<String, List<Object>> map = this.getExcelMap();

        ExcelUtil.generateExcel(map, path, fileName);
    }
}
