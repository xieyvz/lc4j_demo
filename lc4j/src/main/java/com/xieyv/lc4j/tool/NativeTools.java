package com.xieyv.lc4j.tool;

import com.xieyv.lc4j.entity.Appointment;
import com.xieyv.lc4j.entity.QueryRequest;
import com.xieyv.lc4j.entity.Result;
import com.xieyv.lc4j.feign.ToolMethodFeignClient;
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
    private ToolMethodFeignClient feignClient;

    @Tool("获取当前精确时间")
    public String getCurrentTime() {
        Date date = new Date();
        log.info("调用api获取到了时间Current time: {}", date);
        return date.toString();
    }

    @Tool("预约挂号功能，根据参数，先执行工具方法queryDepartment查询是否可预约，并返回用户是否可预约，并让用户确认所有预约信息，用户确认后再执行预约功能")
    public String bookAppointment(Appointment appointment) {
        log.info("调用预约挂号api [{}]", new Date());
        Result<Void> result = feignClient.bookAppointment(appointment);
        if (result != null) {
            return result.getMsg();
        }
        else {
            return "未知错误，可能是后台服务器连接失败";
        }
    }

    @Tool("取消预约挂号，根据参数，查询预约是否存在，若存在则删除记录并返回‘取消成功’，否则‘取消失败’")
    public String cancelAppointment(Appointment appointment) {
        log.info("调用取消预约挂号api [{}]", new Date());
        Result<Void> result = feignClient.cancelAppointment(appointment);
        if (result != null) {
            return result.getMsg();
        }
        else {
            return "未知错误，可能是后台服务器连接失败";
        }
    }

    @Tool("根据科室名称、日期、时间和医生查询是否有号源，并返回给用户。")
    public String queryDepartment(
            @P("科室名称") String name,
            @P("日期") String date,
            @P("时间（可选值：上午、下午）") String time,
            @P(value = "医生姓名", required = false) String doctorName) {
        log.info("调用查询号源api [{}]", new Date());
        QueryRequest queryRequest = new QueryRequest();
        queryRequest.setName(name);
        queryRequest.setDate(date);
        queryRequest.setTime(time);
        if(doctorName != null) {
            queryRequest.setDoctorName(doctorName);
        }

        Result<Void> result = feignClient.queryDepartment(queryRequest);

        //200成功 210正常失败 500网络异常
        if (result.getCode() == 200) {
            return "有号源";
        }
        else  if (result.getCode() == 210) {
            return "无号源";
        }
        else  if (result.getCode() == 500) {
            return "后台服务器连接失败";
        }
        else {
            return "未知错误";
        }
    }

}
