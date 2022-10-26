package com.example.profilecheckerorginal.controller;

import com.example.profilecheckerorginal.payload.UserResponse;
import com.example.profilecheckerorginal.service.TimerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
    private TimerService timerService;

    public PlayerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @GetMapping("/users/nick/{nick}")
    public List<UserResponse> getUserTimersByNick(@PathVariable String nick){
        return timerService.getUserTimersByNick(nick);
    }
    @GetMapping("/users/{players}")
    public List<List<UserResponse>>getFewPlayers(@PathVariable String[] players){
        return timerService.getAllTimersByNicks(players);
    }
    @GetMapping("/users")
    public List<UserResponse> getAll(){
        return timerService.getAll();
    }
}
