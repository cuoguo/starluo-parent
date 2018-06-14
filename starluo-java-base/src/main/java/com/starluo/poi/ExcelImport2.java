package com.starluo.poi;

import com.alibaba.fastjson.JSONArray;
import org.apache.http.client.utils.DateUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 读取Excel
 *
 * @author cong.zhang
 * @date 2018/6/6.
 * @time 9:40.
 */
public class ExcelImport2 {

    private static Workbook wb = null;
    private static DataFormatter formatter = null;
    private static FortuneDetailRO info = null;
    private static List<FortuneDetailRO> fortuneDetailROList = null;

    /**
     * 处理表格数据
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String path = "C:\\6-13.xlsx";

        // 读取全部
        //fortuneDetailROList = readExcel(path);
        //fortuneDetailROList = readExcel(path, 20180605L);
        // 读取指定时间
        fortuneDetailROList = readExcel(path,20180614L,20180617L);

        JSONArray json = (JSONArray) JSONArray.toJSON(fortuneDetailROList);
        System.out.println(json);
    }

    /**
     * 读取Excel
     *
     * @param path          文件路径
     * @return
     * @throws Exception
     */
    private static List<FortuneDetailRO> readExcel(String path) throws Exception {
        return readExcel(path, 0, 0);
    }

    /**
     * 读取Excel
     * @param path          文件路径
     * @param begin_date    开始时间
     * @return
     * @throws Exception
     */
    private static List<FortuneDetailRO> readExcel(String path, long begin_date) throws Exception {
        return readExcel(path, begin_date, 0);
    }

    /**
     * @param path          文件路径
     * @param begin_date    开始日期
     * @param after_date    结束日期
     * @return
     * @throws Exception
     */
    private static List<FortuneDetailRO> readExcel(String path, long begin_date, long after_date) throws Exception {
        wb = WorkbookFactory.create(new File(path));
        formatter = new DataFormatter();
        fortuneDetailROList = new LinkedList<>();

        int count = 0;

        // 获取所有的Sheet页
        int numberOfSheets = wb.getNumberOfSheets();

        // 记录Sheet读取到第几页
        int i = 1;
        for (Sheet sheet : wb) {
            if (i < 10) {
                System.out.println("Sheet " + i + "   of " + numberOfSheets + " : " + sheet.getSheetName());
            } else {
                System.out.println("Sheet " + i + "  of " + numberOfSheets + " : " + sheet.getSheetName());
            }

            // 循环当前页面的数据行
            for (Row row : sheet) {
                // 跳过第一行标题栏
                if (row.getRowNum() == 0) {
                    continue;
                }

                // 当前读取行号
                // System.out.println("Row Num : " + row.getRowNum());

                long constellation_date = Long.parseLong(formatter.formatCellValue(row.getCell(1)));
                if (begin_date > 0) {
                    // 小于开始时间的数据过滤掉
                    if (!(constellation_date > begin_date)) {
                        continue;
                    }
                }

                if (begin_date > 0 && after_date > 0) {
                    // 非开始时间和结束时间时间过滤掉
                    if (!(constellation_date > begin_date && constellation_date < after_date)) {
                        continue;
                    }
                }

                // 将单元格中的内容存入集合
                info = getFortuneDetail(row);
                if (info != null) {
                    fortuneDetailROList.add(info);
                }
            }

            // 获取每一页Sheet内容
            System.out.println( i + "---------------" + fortuneDetailROList.size());
            for (int c = count; c<fortuneDetailROList.size();c++){
                System.out.println(fortuneDetailROList.get(c).toString());
            }
            count= fortuneDetailROList.size();
            // Sheet页 + 1
            i++;
        }

        return fortuneDetailROList;
    }

    /**
     * 数据封装到业务类
     *
     * @param row 行
     */
    private static FortuneDetailRO getFortuneDetail(Row row) {
        info = new FortuneDetailRO();
        formatter = new DataFormatter();
        // 星座
        Cell cell = row.getCell(0);
        String constellation = formatter.formatCellValue(cell);
        info.setConstellation(getConstellation(constellation));

        // 日期
        cell = row.getCell(1);
        String star_time = formatter.formatCellValue(cell);
        Date data = DateUtils.parseDate(star_time, new String[]{"yyyyMMdd"});
        info.setDate(star_time);
        info.setMonth(data.getMonth() + 1);
        info.setDay(data.getDate());
        info.setStart_time(data.getTime());

        // 综合运势得分
        cell = row.getCell(2);
        info.setTotal_fortune_score(Integer.valueOf(formatter.formatCellValue(cell)));
        cell = row.getCell(3);
        info.setTotal_fortune_desc(formatter.formatCellValue(cell));

        // sleep 爱情
        cell = row.getCell(4);
        info.setSleep_fortune_score(Integer.valueOf(formatter.formatCellValue(cell)));
        cell = row.getCell(5);
        info.setSleep_fortune_desc(formatter.formatCellValue(cell));

        // work 事业
        cell = row.getCell(6);
        info.setWork_fortune_score(Integer.valueOf(formatter.formatCellValue(cell)));
        cell = row.getCell(7);
        info.setWork_fortune_desc(formatter.formatCellValue(cell));

        // love 财富
        cell = row.getCell(8);
        info.setLove_fortune_score(Integer.valueOf(formatter.formatCellValue(cell)));
        cell = row.getCell(9);
        info.setLove_fortune_desc(formatter.formatCellValue(cell));

        // 运势贴士
        cell = row.getCell(10);
        if (cell != null) {
            info.setFortune_tips(formatter.formatCellValue(cell));
        }

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
