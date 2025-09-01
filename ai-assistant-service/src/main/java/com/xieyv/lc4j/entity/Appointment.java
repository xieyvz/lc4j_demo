package com.xieyv.lc4j.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class Appointment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String idCard;
    private String department;
    private String date;
    private String time;
    private String doctorName;
}
