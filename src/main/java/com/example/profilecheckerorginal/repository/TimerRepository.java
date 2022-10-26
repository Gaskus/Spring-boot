package com.example.profilecheckerorginal.repository;

import com.example.profilecheckerorginal.entity.LoginTimer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimerRepository extends JpaRepository<LoginTimer,Long> {
    Optional<LoginTimer> findByTimeAndDateAndNick(String time,String date,String username);
    List<LoginTimer> findAllByNick(String nickName);
    @Query("SELECT l FROM LoginTimer l WHERE l.nick = ?1")
    List<LoginTimer> findAllByNicks(String[] nicks);

}
