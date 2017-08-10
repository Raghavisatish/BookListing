package com.example.ragha.searchlists;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ragha on 8/8/2017.
 */

public class QueryClass {
    private static final String LOG_TAG = QueryClass.class.getSimpleName();


    private QueryClass() {
    }

    private static List<initialize> extractFeatureFromJson(String booksJSON) {

        if (TextUtils.isEmpty(booksJSON)) {
            return null;
        }

        List<initialize> books = new ArrayList<>();


            try{
                JSONObject baseJsonResponse = new JSONObject(booksJSON);
                JSONArray bookArray = baseJsonResponse.getJSONArray("items");
                for (int i = 0; i < bookArray.length(); i++){
                    JSONObject currentBook = bookArray.getJSONObject(i);

                    JSONObject bookInformation = currentBook.getJSONObject("volumeInfo");
                    String title = bookInformation.getString("title");
                    JSONArray authorsArrayJSON = bookInformation.getJSONArray("authors");
                    String[] authorsArrayStrings = new String[authorsArrayJSON.length()];
                    for(int j = 0; j < authorsArrayStrings.length; j++){
                        authorsArrayStrings[j] = authorsArrayJSON.getString(j);
                    }
                    String description = bookInformation.getString("description");
                    String infoLink = bookInformation.getString("infoLink");
                    initialize book = new initialize(title, authorsArrayStrings, description, infoLink);
                    books.add(book);
                }
            } catch (JSONException e){
                Log.e(LOG_TAG, "Problem extracting from the JSON string", e);
            }
            return books;
        }



    public static List<initialize> fetchBookData(String requestUrl) {

        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<initialize> books = extractFeatureFromJson(jsonResponse);
        return books;
    }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the books JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


}
