package com.xieyv.lc4j.feign;

import com.xieyv.lc4j.entity.Appointment;
import com.xieyv.lc4j.entity.QueryRequest;
import com.xieyv.lc4j.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
*  OpenFeign客户端，用于发送请求实现远程调用
*/
@FeignClient(value = "lc4j-crud")   //调用微服务name
public interface ToolMethodFeignClient  {
    /*预约挂号，提交appointment*/
    @PostMapping("/api/bookAppointment")
    Result<Void> bookAppointment(@RequestBody Appointment appointment);
    /*取消预约挂号，根据参数，查询预约是否存在，
    若存在则删除记录并返回‘取消成功’，否则‘取消失败’*/
    @PostMapping("/api/cancelAppointment")
    Result<Void> cancelAppointment(@RequestBody Appointment appointment);
    /*"根据科室名称、日期、时间和医生查询是否有号源，并返回给用户"*/
    @PostMapping("/api/queryDepartment")
    Result<Void> queryDepartment(@RequestBody QueryRequest queryRequest);
}
