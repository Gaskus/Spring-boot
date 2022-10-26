package com.example.profilecheckerorginal.service;

import com.example.profilecheckerorginal.entity.LoginTimer;
import com.example.profilecheckerorginal.payload.UserResponse;
import com.example.profilecheckerorginal.repository.TimerRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimerService {
    private final TimerRepository timerRepository;

    public TimerService(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }
    public List<UserResponse> getUserTimersByNick(String nick){
        List<LoginTimer> timers = timerRepository.findAllByNick(nick);
        return timers.stream().map(timer -> new UserResponse(timer.getNick(),timer.getTime(),timer.getDate())).collect(Collectors.toList());
    }
    public List<List<UserResponse>> getAllTimersByNicks(String[] nicks){
        List<List<UserResponse>> players = Arrays.stream(nicks).map(nick->{
               List<LoginTimer> timers = timerRepository.findAllByNicks(nicks);
           return timers.stream().map(timer -> new UserResponse(timer.getNick(),timer.getTime(),timer.getDate())).collect(Collectors.toList());
       }).collect(Collectors.toList());
        return players;
    }
    public List<UserResponse> getAll(){
        return timerRepository.findAll().stream().map(timer ->new UserResponse(timer.getNick(),timer.getTime(),timer.getDate())).collect(Collectors.toList());
    }
}
