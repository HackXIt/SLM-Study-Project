package at.fhtw.bic.slmstudyproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Source: https://github.com/BernLeWal/fhtw-bic3-uptimecalculator/blob/master/src/main/java/at/fhtw/slm/uptimecalculator/controller/UptimeController.java

@RestController
public class DemoController {
    @GetMapping("/uptime/minutes")
    public double calculateUptimeMinutes(@RequestParam double relative) {
        double minutesOfMonth = 60*24*30;
        double definedUptimeInPercent = relative / 100;
        return minutesOfMonth * definedUptimeInPercent; // uptime in minutes
    }

    @GetMapping("/uptime/hours")
    public double calculateUptimeHours(@RequestParam double relative) {
        double hoursOfMonth = 24*30;
        double definedUptimeInPercent = relative / 100;
        return hoursOfMonth * definedUptimeInPercent; // uptime in hours
    }
}
