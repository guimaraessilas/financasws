/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceTest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

/**
 *
 * @author Silas
 */
public class HTTPHelper {
    //https://financasapi.herokuapp.com/api/usuario/signup

//    public static final String URL_BASE = "http://localhost:8080/financasServer/api";
    public static final String URL_BASE = "http://financasapi.herokuapp.com/api";

    public String sendGet(String endpoint, Map<String, String> header) throws IOException {
        URL obj = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        StringBuffer response = new StringBuffer();

        if (!header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                con.addRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        con.disconnect();

        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();

        System.out.println("\nSending 'GET' request to URL : " + endpoint);
        System.out.println("Response Code : " + responseCode);
        return response.toString();
    }

    public String sendPost(String url, String request, Map<String, String> header) throws MalformedURLException, ProtocolException, IOException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        if (!header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                con.addRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        con.setDoOutput(true);
        con.setDoInput(true);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(request);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        StringBuffer response = new StringBuffer();
        System.out.println("\nSending 'POST' request to URL : " + url);

        if (String.valueOf(responseCode).startsWith("20")) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            System.out.println("Response Code : " + responseCode);

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            con.disconnect();

        } else {
            System.out.println("Response: " + responseCode);
            return String.valueOf(responseCode);
        }

        return response.toString();
    }
}
