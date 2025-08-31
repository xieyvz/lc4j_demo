package com.xieyv.lc4j;

import com.xieyv.lc4j.entity.Appointment;
import com.xieyv.lc4j.tool.NativeTools;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RemoteTest {
    @Autowired
    NativeTools nativeTools;

    @Test
    public void sendRemoteTest() {
        Appointment appointment = new Appointment();
        appointment.setIdCard("22251000000000000");
        appointment.setUsername("人人人");
        appointment.setDate("2025-08-00");
        appointment.setTime("上午");
        appointment.setDepartment("测试中心");

        String s = nativeTools.bookAppointment(appointment);
        System.out.println(s);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        s = nativeTools.cancelAppointment(appointment);
        System.out.println(s);
        s = nativeTools.queryDepartment("", "", "", "");
        System.out.println(s);
    }
}
