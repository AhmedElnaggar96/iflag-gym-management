package com.iflag.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {

        String role =  authentication.getAuthorities().iterator().next().getAuthority();

        if(role.equals("ROLE_MEMBER")){
            return "redirect:/member/dashboard";
        }
        else if(role.equals("ROLE_COACH")){
            return "redirect:/coach/dashboard";
        }
        else if(role.equals("ROLE_ADMIN")){
            return "redirect:/admin/dashboard";
        }
        else if(role.equals("ROLE_OWNER")){
            return "redirect:/owner/dashboard";
        }
        return "redirect:/login";
    }

}
