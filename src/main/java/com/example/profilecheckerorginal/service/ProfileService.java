package com.example.profilecheckerorginal.service;

import com.example.profilecheckerorginal.entity.LoginTimer;
import com.example.profilecheckerorginal.repository.TimerRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {
    private LocalDate creationTime;
    private final List<String> urls = List.of(
            "https://www.margonem.pl/profile/view,3126532#char_2896,ataentsic",
            "https://www.margonem.pl/profile/view,6768207#char_2865,ataentsic",
            "https://www.margonem.pl/profile/view,8889877#char_2629,ataentsic",
            "https://www.margonem.pl/profile/view,2431235#char_1484,ataentsic",
            "https://www.margonem.pl/profile/view,5915753#char_2754,ataentsic",
            "https://www.margonem.pl/profile/view,4193395#char_1346,ataentsic",
            "https://www.margonem.pl/profile/view,3647884#char_2578,ataentsic",
            "https://www.margonem.pl/profile/view,3122346#char_1841,ataentsic",
            "https://www.margonem.pl/profile/view,5016876#char_1662,ataentsic"
    );
    private final TimerRepository timerRepository;
    private List<List<String>> namesWithTimers = new ArrayList<>();
    public ProfileService(TimerRepository timerRepository){
        this.timerRepository = timerRepository;
        this.creationTime =  LocalDate.now();
    }
    @Scheduled(fixedRate = 120000)
    @Transactional
    public void saveTimers() throws IOException, InterruptedException {
        namesWithTimers.clear();
        HttpClient client = HttpClient.newHttpClient();
        readProfiles();
        if(namesWithTimers.isEmpty()) return;
        List<LoginTimer> loginTimers = new ArrayList<>();
        if(LocalDate.now().minusDays(1).isAfter(creationTime)) {
                timerRepository.deleteAll();
                creationTime = LocalDate.now();
            }
            namesWithTimers.forEach(values->{if(timerRepository.findByTimeAndDateAndNick(values.get(1),values.get(2),values.get(0)).isEmpty())
                loginTimers.add(new LoginTimer(values.get(1),values.get(2),values.get(0)));
            });
        timerRepository.saveAll(loginTimers);
    }


    private void readProfiles() throws IOException, InterruptedException {
        for(int i = 0; i< urls.size();i++) {
            URL url = new URL(urls.get(i));
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String inputline;
            while ((inputline = reader.readLine()) != null) {
                stringBuilder.append(inputline);
                stringBuilder.append(System.lineSeparator());
            }
            reader.close();
            String content = stringBuilder.toString();
            int startOfTimer = content.indexOf("Ostatnie logowanie:");
            int endOfTimer = content.indexOf("<div class=\"profile-header-data\">");
            int startOfNick = content.indexOf("<meta property=\"og:title\" content=\"");

            String nick = content.substring(startOfNick).split(" - M")[0].split("<meta property=\"og:title\" content=\"")[1];
            String timerForCutting = content.substring(startOfTimer, endOfTimer);
            String timeAndDate = content.substring(startOfTimer, endOfTimer).substring(timerForCutting.indexOf("value\">")).split("value\">")[1].split("</div>")[0];
            String time = timeAndDate.split("<br>")[0];
            String date = timeAndDate.split("<br>")[1];
            this.namesWithTimers.add(List.of(nick, time, date));
        }
    }
}
