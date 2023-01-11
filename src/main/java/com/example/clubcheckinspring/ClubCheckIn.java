package com.example.clubcheckinspring;

import com.example.clubcheckinspring.entityModels.PersonEntity;
import com.example.clubcheckinspring.googleAPI.PersonAPI;
import com.example.clubcheckinspring.googleAPI.SheetsAPI;
import com.example.clubcheckinspring.values.StringValues;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.clubcheckinspring.googleAPI.SheetsAPI.Reader.sheetTo2DArray;

public class ClubCheckIn {

    public static void checkInUser() {
        PersonEntity user = PersonAPI.getUser();
        LocalDateTime now = LocalDateTime.now();
        List<List<Object>> rowEntries = new ArrayList<>();
        ArrayList<Object> row = new ArrayList<>();
        row.add(user.getName());
        row.add(user.getEmail());
        row.add(now.toString());
        rowEntries.add(row);
        SheetsAPI.Writer.writeToNextRow(StringValues.sheetID,rowEntries);
    }

    public static void checkInUser(String name) {
        PersonEntity user = PersonAPI.getUser();
        LocalDateTime now = LocalDateTime.now();
        List<List<Object>> rowEntries = new ArrayList<>();
        ArrayList<Object> row = new ArrayList<>();
        row.add(user.getName());
        row.add(user.getEmail());
        row.add(now.toString());
        rowEntries.add(row);
        SheetsAPI.Writer.writeToNextRow(StringValues.sheetID,rowEntries);
    }

    public static void checkOutUser() {
        PersonEntity user = PersonAPI.getUser();
        LocalDateTime now = LocalDateTime.now();
        List<List<Object>> values = new ArrayList();
        values.add(new ArrayList<>());
        values.get(0).add(LocalDateTime.now().toString());
        int rowPos = 0;
        int colPos = 3;
        String[][] table = SheetsAPI.Reader.sheetTo2DArray(StringValues.sheetID);
        for(int i = 0; i < table.length; i++) {
            boolean isUser = table[i][0].equals(user.getName());
            boolean isStillActive = table[i][3]==(null);
            if(isUser && isStillActive) {
                rowPos = i;
            }
        }
        String range = "Sheet1!R["+rowPos+"]C["+colPos+"]";
        ValueRange body = new ValueRange()
                .setValues(values);
        try {
            SheetsAPI.service.spreadsheets().values().update(StringValues.sheetID, range, body)
                    .setValueInputOption("RAW")
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean IsUserCheckedIn() {
        PersonEntity user = PersonAPI.getUser();
        String[][] table = SheetsAPI.Reader.sheetTo2DArray(StringValues.sheetID);
        for(int i = 0; i < table.length; i++) {
            if(table[i][0].equals(user.getName()) && table[i][3]==null)
                return true;
        }
        return false;
    }
}
