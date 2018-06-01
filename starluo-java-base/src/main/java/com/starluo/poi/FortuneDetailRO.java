package com.starluo.poi;

import lombok.Data;

/**
 * 运势详情
 *
 * @author chen.qiu
 * @date 2018/3/17
 */
@Data
public class FortuneDetailRO implements java.io.Serializable {

    //private String  _id;                  // 唯一标识
    private String constellation;         // 星座

    private int month;                    // 月份(1-12)
    private int day;                      // 日期(1-31)
    private long start_time;              // 星座日期

    private int total_fortune_score;      // 整体运势(星级 ：1-5)
    private String total_fortune_desc;    // 整体运势解读

    private int love_fortune_score;       // 财富运势得分
    private String love_fortune_desc;     // 财富运势描述

    private int work_fortune_score;       // 事业运势得分
    private String work_fortune_desc;     // 事业运势描述

    private int sleep_fortune_score;      // 爱情运势得分
    private String sleep_fortune_desc;    // 爱情运势描述

    private String fortune_tips;          // 贴士

    private int status;                   // 状态
    private long create_time;             // 创建时间
    private String date;                  // 日期（排序使用）

}
