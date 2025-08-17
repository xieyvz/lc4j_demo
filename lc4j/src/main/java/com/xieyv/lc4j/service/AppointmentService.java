package com.xieyv.lc4j.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xieyv.lc4j.entity.Appointment;

public interface AppointmentService extends IService<Appointment> {
    public Appointment getOne(Appointment appointment);
}
