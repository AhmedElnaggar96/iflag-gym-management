package com.iflag.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoachController {

    @GetMapping("/coach/dashboard")
    public  String coachDashboard(){
        return "coach-dashboard";
    }
}
