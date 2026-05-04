package com.iflag.controller;

import com.iflag.entity.*;
import com.iflag.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    private final UserService userService;
    private final MemberProfileService  memberProfileService;
    private final CoachProfileService coachProfileService;
    private final AttendanceService attendanceService;
    private final ClashEventService clashEventService;
    private final ClashResultService clashResultService;

    public AdminController(UserService userService, MemberProfileService memberProfileService, CoachProfileService coachProfileService, AttendanceService attendanceService, ClashEventService clashEventService, ClashResultService clashResultService) {
        this.userService = userService;
        this.memberProfileService = memberProfileService;
        this.coachProfileService = coachProfileService;
        this.attendanceService = attendanceService;
        this.clashEventService = clashEventService;
        this.clashResultService = clashResultService;
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(){
        return "admin-dashboard";
    }

    @GetMapping("/admin/users")
    public String showAllUsers(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "admin-users";
    }

    @GetMapping("/admin/users/add")
    public String showAddUserForm(Model model){
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/admin/users/save")
    public String saveUser(@Valid @ModelAttribute("user")User user, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "add-user";
        }

        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/deactivate")
    public String deactivateUser(@RequestParam("id") Long id) {
        userService.deactivateUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/activate")
    public String activateUser(@RequestParam("id") Long id) {
        userService.activateUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/reset-password")
    public String showResetPasswordForm(@RequestParam("id") Long id, Model model){

        User user = userService.findUserById(id);

        model.addAttribute("user", user);

        return "reset-password";
    }

    @PostMapping("/admin/users/reset-password")
    public String resetPassword(@RequestParam("id") Long id,
                                @RequestParam("newPassword") String newPassword,
                                @RequestParam("confirmPassword") String confirmPassword,
                                Model model){
        User user = userService.findUserById(id);


        if(newPassword.isBlank() || confirmPassword.isBlank()){
            model.addAttribute("user", user);
            model.addAttribute("passwordError", "password is required");
            return "reset-password";
        }

        if(!newPassword.equals(confirmPassword)){
            model.addAttribute("user", user);
            model.addAttribute("passwordError", "passwords do not match");
            return "reset-password";
        }

        userService.resetPassword(id, newPassword);

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/member-profiles/add")
    public  String showAddMemberForm(@RequestParam("userId") Long userId, Model model){
        User user = userService.findUserById(userId);

        MemberProfile memberProfile = new MemberProfile();
        memberProfile.setUser(user);

        model.addAttribute("memberProfile", memberProfile);
        return "add-member-profile";
    }

    @PostMapping("/admin/member-profiles/save")
    public String saveMemberProfile(@Valid @ModelAttribute("memberProfile")MemberProfile memberProfile,
                                    BindingResult bindingResult,
                                    Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("users", userService.findActiveUsers());
            return "add-member-profile";
        }

        boolean duplicateExists = memberProfileService.existsByUserId(memberProfile.getUser().getId());

        if(duplicateExists && memberProfile.getId() == null){
            model.addAttribute("users", userService.findActiveUsers());
            model.addAttribute("duplicateError", "This user already has a member profile.");
            return "add-member-profile";
        }
        memberProfileService.saveMemberProfile(memberProfile);
        return "redirect:/admin/member-profiles";
    }

    @GetMapping("/admin/member-profiles")
    public String showMemberProfiles(Model model){
        List<MemberProfile> memberProfiles = memberProfileService.findAllMemberProfiles();

        Map<Long, Long> usedSessionsMap = new HashMap<>();
        Map<Long, Long> remainingSessionsMap = new HashMap<>();

        for(MemberProfile profile : memberProfiles){
            usedSessionsMap.put(profile.getId(), memberProfileService.getUsedSessions(profile.getId()));
            remainingSessionsMap.put(profile.getId(), memberProfileService.getRemainingSessions(profile.getId()));
        }
        model.addAttribute("memberProfiles", memberProfiles);
        model.addAttribute("usedSessionsMap", usedSessionsMap);
        model.addAttribute("remainingSessionsMap", remainingSessionsMap);
        return "member-profiles";
    }

    @GetMapping("/admin/member-profiles/edit")
    public String showEditMemberProfileForm(@RequestParam("id") Long id, Model model){
        MemberProfile memberProfile = memberProfileService.findMemberProfileById(id);
        model.addAttribute("memberProfile", memberProfile);
        return "add-member-profile";
    }

    @GetMapping("/admin/coach-profiles/add")
    public String showAddCoachProfileForm(@RequestParam("userId") Long userId, Model model){
        User user = userService.findUserById(userId);

        CoachProfile coachProfile = new CoachProfile();
        coachProfile.setUser(user);

        model.addAttribute("coachProfile", coachProfile);
        return "add-coach-profile";
    }

    @PostMapping("/admin/coach-profiles/save")
    public String saveCoachProfile(@Valid @ModelAttribute("coachProfile") CoachProfile coachProfile,
                                   BindingResult bindingResult,
                                   Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("users", userService.findActiveUsers());
            return "add-coach-profile";
        }

        boolean duplicateExists = coachProfileService.existsByUserId(coachProfile.getUser().getId());

        if(duplicateExists && coachProfile.getId() == null){
            model.addAttribute("users", userService.findActiveUsers());
            model.addAttribute("duplicateError", "This user already has a coach profile.");
            return "add-coach-profile";
        }
        coachProfileService.saveCoachProfile(coachProfile);
        return "redirect:/admin/coach-profiles";
    }

    @GetMapping("/admin/coach-profiles")
    public String showCoachProfiles(Model model){
        model.addAttribute("coachProfiles",  coachProfileService.findAllCoachProfiles());
        return "coach-profiles";
    }

    @GetMapping("/admin/coach-profiles/edit")
    public String showEditCoachProfileForm(@RequestParam("id") Long id, Model model){
        CoachProfile coachProfile = coachProfileService.findCoachProfileById(id);
        model.addAttribute("coachProfile", coachProfile);
        return "add-coach-profile";
    }

    @GetMapping("/admin/attendance")
    public String showAllAttendance(Model model){
        model.addAttribute("attendanceList",  attendanceService.findAllAttendance());
        return "attendance-list";
    }

    @GetMapping("/admin/attendance/add")
    public String showAddAttendanceForm(@RequestParam(required = false) String memberKeyword, Model model){
        model.addAttribute("attendance", new Attendance());

        model.addAttribute("memberProfiles",   memberProfileService.searchByNameOrId(memberKeyword));

        model.addAttribute("coachProfiles",  coachProfileService.findActiveCoachProfiles());

        model.addAttribute("memberKeyword", memberKeyword);

        return "add-attendance";
    }


    @GetMapping("/admin/attendance/edit")
    public String showEditAttendanceForm(@RequestParam("id") Long id, Model model) {
        Attendance attendance = attendanceService.findAttendanceById(id);

        model.addAttribute("attendance", attendance);
        model.addAttribute("memberProfiles", memberProfileService.findAllMemberProfiles());
        model.addAttribute("coachProfiles", coachProfileService.findAllCoachProfiles());

        return "add-attendance";
    }

    @PostMapping("/admin/attendance/save")
    public String saveAttendance(@Valid @ModelAttribute("attendance")Attendance attendance,
                                 BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){

            model.addAttribute("memberProfiles",   memberProfileService.findAllMemberProfiles());
            model.addAttribute("coachProfiles",  coachProfileService.findAllCoachProfiles());
            return "add-attendance";
        }

        attendanceService.saveAttendance(attendance);
        return "redirect:/admin/attendance";

    }

    @GetMapping("/admin/attendance/delete")
    public String deleteAttendance(@RequestParam("id") Long id){
        attendanceService.deleteAttendanceById(id);
        return "redirect:/admin/attendance";
    }


    @GetMapping("/admin/member-attendance")
    public String showMemberAttendance(@RequestParam("memberId")Long  memberId, Model model){
        MemberProfile memberProfile = memberProfileService.findMemberProfileById(memberId);
        model.addAttribute("memberProfile", memberProfile);
        model.addAttribute("attendanceList", attendanceService.findAttendanceByMemberId(memberId));

        return "member-attendance";
    }

    // clash events section

    @GetMapping("/admin/clash-events/add")
    public String showAddClashEventForm(Model model){
        model.addAttribute("clashEvent", new ClashEvent());
        return "add-clash-event";
    }

    @PostMapping("/admin/clash-events/save")
    public String saveClashEvent(@ModelAttribute("clashEvent") ClashEvent clashEvent) {
        clashEventService.saveClashEvent(clashEvent);
        return "redirect:/admin/dashboard";
    }


        // clash results section

    @GetMapping("/admin/clash-results/add")
    public String showAddClashResultForm(@RequestParam(required = false) String memberKeyword,  Model model) {

        model.addAttribute("clashResult", new ClashResult());

        model.addAttribute("clashEvents", clashEventService.findAllClashEvents());

        model.addAttribute("memberProfiles", memberProfileService.searchByNameOrId(memberKeyword));

        model.addAttribute("memberKeyword", memberKeyword);

        return "add-clash-result";
    }

    @PostMapping("/admin/clash-results/save")
    public String saveClashResult(@Valid @ModelAttribute("clashResult")  ClashResult clashResult,
                                  BindingResult bindingResult,
                                  Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("clashEvents", clashEventService.findAllClashEvents());
            model.addAttribute("memberProfiles", memberProfileService.findAllMemberProfiles());
            return "add-clash-result";
        }

        boolean duplicateExists = clashResultService.existsByClashEventIdAndMemberId(clashResult.getClashEvent().getId(), clashResult.getMember().getId());

        if (duplicateExists && clashResult.getId() == null) {
            model.addAttribute("clashEvents", clashEventService.findAllClashEvents());
            model.addAttribute("memberProfiles", memberProfileService.findAllMemberProfiles());
            model.addAttribute("duplicateError", "This member already has a result for this clash event.");
            return "add-clash-result";

        }

        clashResultService.saveClashResult(clashResult);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/clash-results")
    public String showClashResults(Model model){
        model.addAttribute("clashResults", clashResultService.findAllClashResults());
        return "clash-results";
    }

    @GetMapping("/admin/member-clash-results")
    public String showMemberClashResults(@RequestParam("memberId") Long memberId, Model model ){
        MemberProfile memberProfile = memberProfileService.findMemberProfileById(memberId);

        model.addAttribute("memberProfile", memberProfile);
        model.addAttribute("clashResults", clashResultService.findClashResultsByMemberId(memberId));

        return "member-clash-results";
    }

    @GetMapping("/admin/clash-results/edit")
    public String showEditClashResultForm(@RequestParam("id") Long id, Model model){
        ClashResult clashResult = clashResultService.findClashResultById(id);

        model.addAttribute("clashResult", clashResult);
        model.addAttribute("clashEvents", clashEventService.findAllClashEvents());
        model.addAttribute("memberProfiles", memberProfileService.findAllMemberProfiles());

        return "add-clash-result";
    }

    @GetMapping("/admin/clash-results/delete")
    public String deleteClashResult(@RequestParam("id") Long id){
        clashResultService.deleteClashResultById(id);

        return "redirect:/admin/clash-results";
    }
}

