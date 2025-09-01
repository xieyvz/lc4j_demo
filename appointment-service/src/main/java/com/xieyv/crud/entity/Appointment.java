package com.xieyv.crud.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@TableName("appointment")
@Data
public class Appointment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("id_card")
    private String idCard;

    @TableField("department")
    private String department;

    @TableField("date")
    private String date;

    @TableField("time")
    private String time;

    @TableField("doctor_name")
    private String doctorName;
}
