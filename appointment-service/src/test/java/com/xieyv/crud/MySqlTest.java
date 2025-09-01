package com.xieyv.crud;

import com.xieyv.crud.entity.Appointment;
import com.xieyv.crud.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MySqlTest {
    @Autowired
    AppointmentService appointmentService;

    @Test
    public void saveTest(){
        Appointment appointment = new Appointment();
        appointment.setIdCard("210000000000000000");
        appointment.setUsername("xieyv");
        appointment.setTime("下午");
        appointment.setDate("2025-8-1");
        appointment.setDepartment("心灵科室");
        appointment.setDoctorName("nijika");
        appointmentService.save(appointment);
    }

    @Test
    public void saveTest2(){
        Appointment appointment = new Appointment();
        appointment.setIdCard("512331000000000000");
        appointment.setUsername("柳如烟");
        appointment.setDate("2025-08-18");
        appointment.setTime("上午");
        appointment.setDepartment("体检中心");
        appointmentService.save(appointment);
    }

    @Test
    public void cancelAppointment() {
        Appointment appointment = new Appointment();
        appointment.setIdCard("512331000000000000");
        appointment.setUsername("柳如烟");
        appointment.setDate("2025-08-18");
        appointment.setTime("上午");
        appointment.setDepartment("体检中心");
        Appointment appointmentFromDB = appointmentService.getOne(appointment);
        System.out.println(appointmentFromDB);
        if (appointmentFromDB != null) {
            boolean removed = appointmentService.removeById(appointmentFromDB.getId());
            if(removed) {
                //TODO 取消成功
                System.out.println("取消成功");    return;
            }
            else {
                //TODO 不存在该挂号记录，删除失败
                System.out.println("不存在该挂号记录，删除失败");    return;
            }
        }
        //TODO
        System.out.println("您没有预约记录，请核对您的预约信息");
    }
}
