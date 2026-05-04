package com.iflag.controller;

import com.iflag.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {

    private final UserService userService;
    private final MemberProfileService memberProfileService;
    private final AttendanceService attendanceService;
    private final ClashResultService clashResultService;
    private final CoachProfileService coachProfileService;

    public OwnerController(UserService userService, MemberProfileService memberProfileService, AttendanceService attendanceService, ClashResultService clashResultService, CoachProfileService coachProfileService) {
        this.userService = userService;
        this.memberProfileService = memberProfileService;
        this.attendanceService = attendanceService;
        this.clashResultService = clashResultService;
        this.coachProfileService = coachProfileService;
    }

    @GetMapping("/owner/dashboard")
    public String ownerDashboard(Model model) {
        model.addAttribute("totalUsers", userService.countUsers());
        model.addAttribute("totalMemberProfiles", memberProfileService.countMemberProfiles());
        model.addAttribute("totalAttendance", attendanceService.countAttendance());
        model.addAttribute("totalClashResults", clashResultService.countClashResults());
        model.addAttribute("totalCoachProfiles", coachProfileService.countCoachProfiles());

        return "owner-dashboard";
    }
}
