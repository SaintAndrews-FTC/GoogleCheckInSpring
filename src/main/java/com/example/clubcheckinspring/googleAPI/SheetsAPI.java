package com.example.clubcheckinspring.googleAPI;

import com.example.clubcheckinspring.entityModels.PersonEntity;
import com.example.clubcheckinspring.values.StringValues;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SheetsAPI {
    public static Sheets service = GoogleAuthenticator.getSheetsService();

    public static class Reader {
        public static String readEntireSheet(String sheetID) {
            BatchGetValuesResponse readResult;
            try {
                readResult = service.spreadsheets().values()
                        .batchGet(sheetID)
                        .setMajorDimension("COLUMNS")
                        .setRanges(Arrays.asList("Sheet1!1:5"))
                        .execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.println(readResult.toPrettyString());
                System.out.println();
                System.out.println(readResult.getValueRanges().get(0).getValues());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return readResult.getValueRanges().get(0).getValues().toString();
        }
        public static BatchGetValuesResponse loadEntireSheet(String sheetID) {
            BatchGetValuesResponse readResult;
            try {
                readResult = service.spreadsheets().values()
                        .batchGet(sheetID)
                        .setMajorDimension("ROWS")
                        .setRanges(Arrays.asList("Sheet1"))
                        .execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ValueRange valueRange = readResult.getValueRanges().get(0);
            Object[] myList = valueRange.getValues().toArray();
            for(int i = 0; i < myList.length; i++) {
                System.out.println("myList: " + myList[i]);
            }
            return readResult;
        }
        public static String[][] sheetTo2DArray(String sheetID) {
            System.out.println();
            ValueRange readResults;

            try {
                readResults = service.spreadsheets().values().get(sheetID, "Sheet1").execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int numOfRows = readResults.getValues().size();
            int numOfCols = readResults.getValues().get(0).size();

            String[][] table = new String[numOfRows][numOfCols];

            for(int i = 0; i < numOfRows; i++) {
                for(int j = 0; j < readResults.getValues().get(i).size(); j++) {
                    table[i][j] = readResults.getValues().get(i).get(j).toString();
                }
            }

            for(int i = 0; i < table.length; i++) {
                for(int j = 0; j < table[i].length; j++) {
                   System.out.print(table[i][j] + " ");
                }
                System.out.println();
            }

            return table;
        }
        public static  ArrayList<String> getHeaderColumn(String sheetID) {
            BatchGetValuesResponse readResult;
            try {
                readResult = service.spreadsheets().values()
                        .batchGet(sheetID)
                        .setMajorDimension("ROWS")
                        .setRanges(Arrays.asList("Sheet1"))
                        .execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.println(readResult.toPrettyString());
                System.out.println();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ArrayList<String> headerList = new ArrayList<>();
            for(int i = 0; i < readResult.getValueRanges().get(0).size(); i++) {
                headerList.add(readResult.getValueRanges().get(0).getValues().get(0).get(i).toString());
            }
            return headerList;
        }

    }

    public static class Writer {
        public static void writeToCell(String sheetID, String startingCell, String text) {
            BatchGetValuesResponse readResult;
            try {
                readResult = service.spreadsheets().values()
                        .batchGet(sheetID)
                        .setMajorDimension("COLUMNS")
                        .setRanges(Arrays.asList("Sheet1!1:5"))
                        .execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ValueRange body = new ValueRange()
                    .setValues(Arrays.asList(
                            Arrays.asList(text)));

            try {
                UpdateValuesResponse result = service.spreadsheets().values()
                        .update(sheetID, startingCell, body)
                        .setValueInputOption("RAW")
                        .execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static void writeToNextRow(String sheetID,List<List<Object>> rowEntries) {
            ValueRange body;
            String range = "Sheet1!A1";
            ArrayList<Object> row = new ArrayList<>();
            body = new ValueRange()
                    .setValues(rowEntries);
            try {
                service.spreadsheets().values().append(sheetID, range, body)
                        .setValueInputOption("RAW")
                        .execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}