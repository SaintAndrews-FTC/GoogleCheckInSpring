package com.example.clubcheckinspring;

import com.example.clubcheckinspring.entityModels.FormEntity;
import com.example.clubcheckinspring.entityModels.PersonEntity;
import com.example.clubcheckinspring.googleAPI.PersonAPI;
import com.example.clubcheckinspring.googleAPI.SheetsAPI;
import com.example.clubcheckinspring.timeAPI.TimeHelpers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        int randomVal = (int) (Math.random() * 100);
        FormEntity formEntity = new FormEntity();
        PersonEntity user = new PersonEntity(PersonAPI.getUser().getName(), PersonAPI.getUser().getEmail(), ClubCheckIn.IsUserCheckedIn());
        model.addAttribute("formEntity", formEntity);
        model.addAttribute("user", user);
        model.addAttribute("currentUser", user.getName());

        return "greeting";
    }


    @PostMapping("/greeting")
    public String checkIn(@ModelAttribute String login, FormEntity formEntity, Model model, boolean checkIn) {
        PersonEntity user = new PersonEntity(PersonAPI.getUser().getName(), PersonAPI.getUser().getEmail(), ClubCheckIn.IsUserCheckedIn());
        model.addAttribute("formEntity", formEntity);
        model.addAttribute("user", user);
        System.out.println();
        System.out.println(user.isCheckedIn());
        if(user.isCheckedIn()) {
            ClubCheckIn.checkOutUser();
            user.setCheckedIn(false);
        }
        else {
            ClubCheckIn.checkInUser();
            user.setCheckedIn(true);
        }
        return "greeting";
    }
}