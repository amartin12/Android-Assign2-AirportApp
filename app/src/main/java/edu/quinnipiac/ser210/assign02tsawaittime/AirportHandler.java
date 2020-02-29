package edu.quinnipiac.ser210.assign02tsawaittime;

/*
Authors: Alexandra Martin, Megan Forster
Professor Ruby
SER 210 Android Development
Due: 27 February 2020

This is the Airport Handler class which will handle all airport REST API data for the airports
 */


import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AirportHandler {

    private static final int FIRST_AIRPORT = '0';  //ABR //**Need to get the data to see first and last
    private static final int LAST_AIRPORT = '7'; // ABQ
    public static final String AIRPORT_DETAILS = "AIRPORT_DETAILS";

    final public static String[] airports = new String[LAST_AIRPORT - FIRST_AIRPORT + 1];


    public AirportHandler() {

        //populate the array
         // int i = 0;
        for (int i = 0; i <= 7; i++) {
            airports[i] = Integer.toString(i);
        }

    }


    public static String getAirportsDetails(String airportJsonString) throws JSONException {
        //puts the airport data into a json object

        JSONArray airPortArrObj = new JSONArray(airportJsonString);
        JSONObject airDetailObj;


        String str = "0";
        switch (str) {

//            airDetailObj = airPortArrObj.getJSONObject(2);
//                airDetailObj.getString("code");

            case "0":
                airDetailObj =  airPortArrObj.getJSONObject(0);
                String code = airDetailObj.getString("code");

                Log.d("DEBUG switch","In case 0");
                Log.d("DEBUG: ",code);

                break;

            case "1":
                airDetailObj = airPortArrObj.getJSONObject(1);
                Log.d("DEBUG switch","In case 1");
                break;

            case "2":
                airDetailObj = airPortArrObj.getJSONObject(2);

                Log.d("DEGUB switch","In case 2");
                break;

            case "3":
                airDetailObj = airPortArrObj.getJSONObject(3);

                break;

            case "4":
                airDetailObj = airPortArrObj.getJSONObject(4);

                break;

            case "5":
                airDetailObj = airPortArrObj.getJSONObject(5);

                break;

            case "6":
                airDetailObj = airPortArrObj.getJSONObject(6);

                break;

            case "7":
                airDetailObj = airPortArrObj.getJSONObject(7);

                break;
            default:

                System.out.println("DEBUG: IN default of switch ");
                airDetailObj = airPortArrObj.getJSONObject(8);


        }
        return  airDetailObj.toString();


    }
}




