package com.example.clubcheckinspring;

import com.example.clubcheckinspring.googleAPI.PersonAPI;
import com.example.clubcheckinspring.googleAPI.SheetsAPI;
import com.example.clubcheckinspring.timeAPI.TimeHelpers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ClubCheckInSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubCheckInSpringApplication.class, args);
        //ClubCheckIn.checkInUser();
        //SheetsAPI.Reader.sheetTo2DArray("150rvJH1XxlRrxEJE2GgzsyCGfFECUm_UvhsrFIh845I");

    }

}
