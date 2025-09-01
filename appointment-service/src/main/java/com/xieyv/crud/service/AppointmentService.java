package com.xieyv.crud.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xieyv.crud.entity.Appointment;
import org.springframework.stereotype.Service;

public interface AppointmentService extends IService<Appointment> {
    public Appointment getOne(Appointment appointment);
}
