package com.starluo.poi;

import com.alibaba.fastjson.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cong.zhang
 * @date 2018/5/10.
 * @time 10:02.
 */
public class ExcelImport {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, ParseException {
        List<FortuneDetailRO> loadScoreInfoList = readExcel("C:\\61.xls");
        for (int i = 0; i < loadScoreInfoList.size(); i++) {
            System.out.println(loadScoreInfoList.get(i).toString());
        }

        JSONArray json = (JSONArray) JSONArray.toJSON(loadScoreInfoList);
        System.out.println(json);
    }


    /**
     * 读取Excel
     *
     * @return 数据集合
     */
    private static List<FortuneDetailRO> readExcel(String path) throws ParseException {
        List<FortuneDetailRO> list = new LinkedList<>();

        HSSFWorkbook workbook = null;
        HSSFSheet hssfSheet = null;
        HSSFRow hssfRow = null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            // 读取Excel文件
            InputStream inputStream = new FileInputStream(path);
            workbook = new HSSFWorkbook(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 循环工作表
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            hssfSheet = workbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }

                HSSFCell cell = hssfRow.getCell(1);
                DataFormatter formatter = new DataFormatter();
                String cellValue = formatter.formatCellValue(cell);
                if (Long.parseLong(cellValue) > 20180601L && Long.parseLong(cellValue) < 20180606L) {

                } else {
                    continue;
                }

                FortuneDetailRO info = getFortuneDetailRO(hssfRow, simpleDateFormat);
                list.add(info);
            }
        }

        return list;
    }

    /**
     *
     * @param hssfRow
     * @param simpleDateFormat
     * @return
     * @throws ParseException
     */
    private static FortuneDetailRO getFortuneDetailRO(HSSFRow hssfRow, SimpleDateFormat simpleDateFormat) throws ParseException {
        // 将单元格中的内容存入集合
        FortuneDetailRO info = new FortuneDetailRO();

        // 星座
        HSSFCell cell = hssfRow.getCell(0);
        String constellation = cell.getStringCellValue();
        info.setConstellation(getConstellation(constellation));

        // 日期
        cell = hssfRow.getCell(1);
        String star_time = String.valueOf((long) cell.getNumericCellValue());
        info.setDate(star_time);
        info.setMonth(Integer.valueOf(star_time.substring(5, 6)));
        info.setDay(Integer.valueOf(star_time.substring(6, 8)));
        Date data = simpleDateFormat.parse(String.valueOf((long) cell.getNumericCellValue()));
        info.setStart_time(data.getTime());

        // 综合运势得分
        cell = hssfRow.getCell(2);
        info.setTotal_fortune_score((int) cell.getNumericCellValue());
        cell = hssfRow.getCell(3);
        info.setTotal_fortune_desc(cell.getStringCellValue());

        // sleep 爱情
        cell = hssfRow.getCell(4);
        info.setSleep_fortune_score((int) cell.getNumericCellValue());
        cell = hssfRow.getCell(5);
        info.setSleep_fortune_desc(cell.getStringCellValue());

        // work 事业
        cell = hssfRow.getCell(6);
        info.setWork_fortune_score((int) cell.getNumericCellValue());
        cell = hssfRow.getCell(7);
        info.setWork_fortune_desc(cell.getStringCellValue());

        // love 财富
        cell = hssfRow.getCell(8);
        info.setLove_fortune_score((int) cell.getNumericCellValue());
        cell = hssfRow.getCell(9);
        info.setLove_fortune_desc(cell.getStringCellValue());

        // 运势贴士
        cell = hssfRow.getCell(10);
        if (cell != null) {
            info.setFortune_tips(cell.getStringCellValue());
        }

        info.setCreate_time(System.currentTimeMillis());
        info.setStatus(1);
        return info;
    }

    /**
     *
     * @param hssfRow
     * @param simpleDateFormat
     * @return
     * @throws ParseException
     */
    private static FortuneDetailFullRO getFortuneDetailFullRO(HSSFRow hssfRow, SimpleDateFormat simpleDateFormat) throws ParseException {
        // 将单元格中的内容存入集合
        FortuneDetailFullRO info = new FortuneDetailFullRO();

        // 星座
        HSSFCell cell = hssfRow.getCell(0);
        String constellation = cell.getStringCellValue();
        info.setConstellation(getConstellation(constellation));

        // 日期
        cell = hssfRow.getCell(1);
        String star_time = String.valueOf((long) cell.getNumericCellValue());
        info.setDate(star_time);
        info.setMonth(Integer.valueOf(star_time.substring(5, 6)));
        info.setDay(Integer.valueOf(star_time.substring(6, 8)));
        Date data = simpleDateFormat.parse(String.valueOf((long) cell.getNumericCellValue()));
        info.setStart_time(data.getTime());

        // 综合运势得分
        cell = hssfRow.getCell(2);
        info.setTotal_fortune_score((int) cell.getNumericCellValue());
        cell = hssfRow.getCell(3);
        info.setTotal_fortune_desc(cell.getStringCellValue());

        // sleep 爱情
        cell = hssfRow.getCell(4);
        info.setSleep_fortune_score((int) cell.getNumericCellValue());
        cell = hssfRow.getCell(5);
        info.setSleep_fortune_desc(cell.getStringCellValue());

        // work 事业
        cell = hssfRow.getCell(6);
        info.setWork_fortune_score((int) cell.getNumericCellValue());
        cell = hssfRow.getCell(7);
        info.setWork_fortune_desc(cell.getStringCellValue());

        // love 财富
        cell = hssfRow.getCell(8);
        info.setLove_fortune_score((int) cell.getNumericCellValue());
        cell = hssfRow.getCell(9);
        info.setLove_fortune_desc(cell.getStringCellValue());

        // 健康指数
        cell = hssfRow.getCell(10);
        info.setHealth(cell.getNumericCellValue());

        // 幸运颜色
        cell = hssfRow.getCell(11);
        info.setLucky_color(cell.getStringCellValue());

        // 幸运数字
        cell = hssfRow.getCell(12);
        info.setLucky_number((int) cell.getNumericCellValue());

        // 速配星座
        cell = hssfRow.getCell(13);
        info.setSpeed_dating(cell.getStringCellValue());

        // 运势贴士
        cell = hssfRow.getCell(14);
        info.setFortune_tips(cell.getStringCellValue());

        info.setCreate_time(System.currentTimeMillis());
        info.setStatus(1);
        return info;
    }

    /**
     * 获取星座名称拼音
     *
     * @param constellation
     * @return
     */
    private static String getConstellation(String constellation) {
        switch (constellation) {
            case "水瓶座":
                constellation = "shuiping";
                break;
            case "双鱼座":
                constellation = "shuangyu";
                break;
            case "白羊座":
                constellation = "baiyang";
                break;
            case "金牛座":
                constellation = "jinniu";
                break;
            case "双子座":
                constellation = "shuangzi";
                break;
            case "巨蟹座":
                constellation = "juxie";
                break;
            case "狮子座":
                constellation = "shizi";
                break;
            case "处女座":
                constellation = "chunv";
                break;
            case "天秤座":
                constellation = "tiancheng";
                break;
            case "天蝎座":
                constellation = "tianxie";
                break;
            case "射手座":
                constellation = "sheshou";
                break;
            case "摩羯座":
                constellation = "mojie";
                break;
        }
        return constellation;
    }


}
