package com.iflag.controller;

import com.iflag.entity.MemberProfile;
import com.iflag.entity.User;
import com.iflag.service.AttendanceService;
import com.iflag.service.ClashResultService;
import com.iflag.service.MemberProfileService;
import com.iflag.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    private final UserService userService;
    private final MemberProfileService memberProfileService;
    private final AttendanceService attendanceService;
    private final ClashResultService  clashResultService;

    public MemberController(UserService userService, MemberProfileService memberProfileService, AttendanceService attendanceService, ClashResultService clashResultService) {
        this.userService = userService;
        this.memberProfileService = memberProfileService;
        this.attendanceService = attendanceService;
        this.clashResultService = clashResultService;
    }


    @GetMapping("/member/dashboard")
    public String memberDashboard(Authentication authentication, Model model) {

        String email = authentication.getName();

        User user = userService.findUserByEmail(email);
        MemberProfile memberProfile = memberProfileService.findByUser(user);

        long usedSessions = memberProfileService.getUsedSessions(memberProfile.getId());
        long remainingSessions = memberProfileService.getRemainingSessions(memberProfile.getId());

        model.addAttribute("user", user);
        model.addAttribute("memberProfile", memberProfile);
        model.addAttribute("usedSessions", usedSessions);
        model.addAttribute("remainingSessions", remainingSessions);

        return "member-dashboard";
    }

    @GetMapping("/member/attendance")
    public String memberAttendance(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        MemberProfile memberProfile = memberProfileService.findByUser(user);

        model.addAttribute("memberProfile", memberProfile);
        model.addAttribute("attendanceList", attendanceService.findAttendanceByMemberId(memberProfile.getId()));

        return "member-attendance-member";
    }

    @GetMapping("/member/clash-results")
    public String memberClashResults(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        MemberProfile memberProfile = memberProfileService.findByUser(user);
        model.addAttribute("memberProfile", memberProfile);
        model.addAttribute("clashResults",  clashResultService.findClashResultsByMemberId(memberProfile.getId()));

        return "member-clash-results-member";
    }
}
