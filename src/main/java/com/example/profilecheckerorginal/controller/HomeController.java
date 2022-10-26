package com.example.profilecheckerorginal.controller;

import com.example.profilecheckerorginal.payload.UserResponse;
import com.example.profilecheckerorginal.service.TimerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


@Controller
public class HomeController {
    private TimerService timerService;

    public HomeController(TimerService timerService) {
        this.timerService = timerService;
    }
    @GetMapping
    public String getHomePage(Model model){
        List<UserResponse> timers = timerService.getAll();
        model.addAttribute("timers",timers);
        return "allTimers";
    }
}
