package com.example.clubcheckinspring.googleAPI;

import com.example.clubcheckinspring.entityModels.PersonEntity;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;

import java.io.IOException;

public class PersonAPI {
    static PeopleService service = GoogleAuthenticator.getPeopleService();

    public static PersonEntity getUser() {
        Person profile;
        try {
            profile = service.people().get("people/me")
                    .setPersonFields("names,emailAddresses")
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PersonEntity person = new PersonEntity(profile.getNames().get(0).getDisplayName(),profile.getEmailAddresses().get(0).getValue());
        System.out.println(person.getName());
        System.out.println(person.getEmail());
        return person;
    }

}
