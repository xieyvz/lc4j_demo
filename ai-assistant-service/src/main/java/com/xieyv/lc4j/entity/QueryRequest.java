package com.xieyv.lc4j.entity;

import lombok.Data;

//科室名称、日期、时间和医生查询是否有号源
@Data
public class QueryRequest {
    //"科室名称"
    String name;
    //"日期"
    String date;
    //"时间（可选值：上午、下午）"
    String time;
    //"医生姓名" 可空
    String doctorName;
}
