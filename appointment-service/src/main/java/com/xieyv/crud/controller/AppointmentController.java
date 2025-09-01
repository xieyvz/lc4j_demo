package com.xieyv.crud.controller;

import com.xieyv.crud.entity.Appointment;
import com.xieyv.crud.entity.QueryRequest;
import com.xieyv.crud.entity.Result;
import com.xieyv.crud.service.AppointmentService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/api")
@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    /*预约挂号功能，根据参数，先执行工具方法queryDepartment查询是否可预约，
    并返回用户是否可预约，并让用户确认所有预约信息，用户确认后再执行预约功能"*/
    @ResponseBody
    @PostMapping("/bookAppointment")
    public Result<Void> bookAppointment(@RequestBody Appointment appointment) {
        Appointment appointmentFromDB = appointmentService.getOne(appointment);
        if (appointmentFromDB == null) {
            appointment.setId(null);    //避免大模型幻觉刚好蒙到已有id，造成冲突
            //TODO 执行预约
            boolean saved = appointmentService.save(appointment);
            if (saved) {
                //TODO 预约成功
                return Result.ok();
            }
            else {
                return Result.fail(700 ,"预约失败，原因未知");
            }
        }
        //TODO 预约失败，已在相同科室和时间存在预约
        return Result.fail(701 ,"预约失败，已在相同科室和时间存在预约");
    }

    /*取消预约挂号，根据参数，查询预约是否存在，
    若存在则删除记录并返回‘取消成功’，否则‘取消失败’*/
    @ResponseBody
    @PostMapping("/cancelAppointment")
    public Result<Void> cancelAppointment(@RequestBody Appointment appointment) {
        Appointment appointmentFromDB = appointmentService.getOne(appointment);
        if (appointmentFromDB != null) {
            boolean removed = appointmentService.removeById(appointmentFromDB.getId());
            if(removed) {
                //TODO 取消成功
                return Result.ok();
            }
            else {
                //TODO 不存在该挂号记录，删除失败
                return Result.fail(704, "不存在该挂号记录，删除失败");
            }
        }
        //TODO"您没有预约记录，请核对您的预约信息"
        return Result.fail(703,"您没有预约记录，请核对您的预约信息");
    }

    /*"根据科室名称、日期、时间和医生查询是否有号源，并返回给用户"*/
    @ResponseBody
    @PostMapping("/queryDepartment")
    public Result<Void> queryDepartment(@RequestBody QueryRequest queryRequest) {
        //TODO 如果未指定医生姓名，判断当天是否有排班
        //TODO 如果指定医生姓名，判断医生排班时间段是否约满
        //TODO 实现需要建立医生排班表，也是纯crud，搁置了
        return Result.ok();
    }
}
