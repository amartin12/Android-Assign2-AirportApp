package edu.quinnipiac.ser210.assign02tsawaittime;

/*
Authors: Alexandra Martin, Megan Forster
Professor Ruby
SER 210 Android Development
Due: 27 February 2020

This is the Main Activity class which is accessed after entering from the Splash screen.
Here the user will chose which Airport they wish to get details about TSA wait times.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    AirportHandler airportHandler = new AirportHandler();
    ShareActionProvider provider;
    boolean userPick = false;
    String item = "";
    private String url01 = "https://tsa-wait-times.p.rapidapi.com/airports/test?APIKEY=test";
    private String url02 =   "test?APIKEY=test";



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets the view to from the layout to display
        setContentView(R.layout.activity_main);
        //creates tool bar and connects to the the id from the layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //sets spinner to find the spinner from the layout with the id
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        //Creates an array adapter to display spinner info from the airport Handler class
        ArrayAdapter<String> arAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, airportHandler.airports);

        //sets the view of the spinner to drop down
        arAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arAdapter);

        //sets the listener for the spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (userPick) {
                    item = (String) parent.getItemAtPosition(position);

                    Log.d("DEBUG: onItemSelected :airport", item);
                    Intent selectedAirPort = new Intent(MainActivity.this, AirportHandler.class);
                    selectedAirPort.putExtra("selected", item);

                    //Calls the async subclass FetchAirData to get the airport item details
                    new FetchAirData().execute(item);

                    userPick = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        userPick = true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        provider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        setShareActionIntent("Look how much longer I have to wait!");

        MenuItem helpItem = menu.findItem(R.id.help);
        provider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        if (provider == null)

            //DEBUG
            Log.d("DEBUG: MainActivity", "noshare provider");

        return true;
}
    //Methods to setAction Intents
    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        provider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.setting) {  //id == R.id.action_settings
            Intent intent = new Intent(MainActivity.this, ChangeBackground.class);
            startActivity(intent);
            return true;
        }
        //Switch statement to ge the help activity from Actionbar
        switch (item.getItemId()) {
            case R.id.help:
                Intent intent = new Intent(this, Help.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //ASYNC SUBCLASS: Private class to do background work on the app fetching the data from the api to display
    private class FetchAirData extends AsyncTask<String, Void, String> {

        @Override
        //Method to connect to the API url and grab data
        protected String doInBackground(String... strings) {
            HttpURLConnection urlConnection = null;
            String airportDetails;
            try {
                URL url = new URL(url01 + strings[0]
                        + url02);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("X-RapidAPI-Key", "d2af84939emshd185dedf4ece551p12119bjsn1aede712b856");

                urlConnection.connect();


                if (urlConnection.getInputStream() == null) {

                    //DEBUG
                    Log.e("DEBUG no connection", "no connection");
                    return null;
                }
                //Buffers in the input in of the airportdata from the url
                airportDetails = getStringFromBuffer(
                        new BufferedReader(new InputStreamReader(urlConnection.getInputStream())));

                //DEBUG
                Log.d("DEBUG: airportDetails", airportDetails);

                //Catch error exceptions
            } catch (Exception e) {
                //DEBUG
                Log.e("MainActivity", "Error" + e.getMessage());
                return null;

            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            //return the data that has been buffered in
            return airportDetails;
        }

        //Private method to handle buffering the data into strings
        private String getStringFromBuffer(BufferedReader bufferedReader) throws Exception {
            StringBuffer stringBuffer = new StringBuffer();
            String string;
            int userChoice = Integer.parseInt(item);
            //String buff = bufferedReader.readLine();
            int k = 0;
            int index = 0;
            int startPoint = 0;
            String[] arrayOfAirports = new String[8];


            while ((string = bufferedReader.readLine()) != null) {
                stringBuffer.append(string + ','+'\n');
                for (int i = 0; i <string.length(); i++) {
                    if (string.charAt(i) == ',') {
                        k++;
                        if (k == 7) {
                            String temp = string.substring(startPoint, i + 1);
                            System.out.println("+++++++++++++++++++++ " + temp);
                            arrayOfAirports[index] = temp;
                            index++;
                            k = 0;
                            startPoint = i + 1;
                        }
                    }
                }

            }
            //Checking buffered reader to make sure not null
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();

                } catch (IOException e) {
                    Log.e("DEBUG: MainActivity", "Error" + e.getMessage());
                    return null;
                }
            }
            Log.d("DEBUG: airp", stringBuffer.toString());
            //Returns data from the airportHandler
            return AirportHandler.getAirportsDetails(arrayOfAirports[userChoice]);
        }

        @Override
        //Method that handles execution of the data once selected to send to Details page
        protected void onPostExecute(String airportDetails) {

            if (airportDetails != null) {
                //DEBUG
                Log.d("DEBUG: MainActivity", airportDetails);

                //Intent to send data from this class to the details page
                Intent intent = new Intent(MainActivity.this, Details_Activity.class);
                //Puts the data
                intent.putExtra("AIRPORT_DETAILS", airportDetails);
                startActivity(intent);
            }

        }
    }
}