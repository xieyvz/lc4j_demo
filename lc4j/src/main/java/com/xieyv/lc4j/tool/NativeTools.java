package com.xieyv.lc4j.tool;

import com.xieyv.lc4j.entity.Appointment;
//import com.xieyv.lc4j.service.AppointmentService;
import com.xieyv.lc4j.service.AppointmentService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class NativeTools {

    @Autowired
    private AppointmentService appointmentService;

    @Tool("获取当前精确时间")
    public String getCurrentTime() {
        Date date = new Date();
        log.info("调用api获取到了时间Current time: {}", date);
        return date.toString();
    }

    @Tool("预约挂号功能，根据参数，先执行工具方法queryDepartment查询是否可预约，并返回用户是否可预约，并让用户确认所有预约信息，用户确认后再执行预约功能")
    public String bookAppointment(Appointment appointment) {
        log.info("调用预约挂号api [{}]", new Date());
        Appointment appointmentFromDB = appointmentService.getOne(appointment);
        if (appointmentFromDB == null) {
            appointment.setId(null);    //避免大模型幻觉刚好蒙到已有id，造成冲突
            //TODO 执行预约
            boolean saved = appointmentService.save(appointment);
            if (saved) {
                //TODO 预约成功，并返回预约详情，需要用户确认
                return "预约成功";
            }
            else {
                return "预约失败，原因未知";
            }
        }
        //TODO 预约失败，已在相同科室和时间存在预约
        return "预约失败，已在相同科室和时间存在预约";
    }

    @Tool("取消预约挂号，根据参数，查询预约是否存在，若存在则删除记录并返回‘取消成功’，否则‘取消失败’")
    public String cancelAppointment(Appointment appointment) {
        log.info("调用取消预约挂号api [{}]", new Date());
        Appointment appointmentFromDB = appointmentService.getOne(appointment);
        if (appointmentFromDB != null) {
            boolean removed = appointmentService.removeById(appointmentFromDB.getId());
            if(removed) {
                //TODO 取消成功
                return "取消成功";
            }
            else {
                //TODO 不存在该挂号记录，删除失败
                return "不存在该挂号记录，删除失败";
            }
        }
        //TODO
        return "您没有预约记录，请核对您的预约信息";
    }

    @Tool("根据科室名称、日期、时间和医生查询是否有号源，并返回给用户")
    public boolean queryDepartment(
            @P("科室名称") String name,
            @P("日期") String date,
            @P("时间（可选值：上午、下午）") String time,
            @P(value = "医生姓名", required = false) String doctorName) {
        log.info("调用查询号源api [{}]", new Date());
        //TODO 如果未指定医生姓名，判断当天是否有排班
        //TODO 如果指定医生姓名，判断医生排班时间段是否约满
        return true;
    }

}
