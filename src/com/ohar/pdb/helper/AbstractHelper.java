package com.ohar.pdb.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class AbstractHelper {

    public static String encodeParam(String param) {
        try {
            return URLEncoder.encode(param, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            return param;
        }
    }

    public static String decodeParam(String param) {
        try {
            return URLDecoder.decode(param, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            return param;
        }
    }

    public static String runConnection(String urlString, String method, byte[] body) {
        final StringBuilder sb = new StringBuilder();
        try {
            final URL url = new URL(urlString);
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            if (body != null && body.length > 0) {
                con.setDoOutput(true);
                try (OutputStream outputStream = con.getOutputStream()) {
                    outputStream.write(body);
                    outputStream.flush();
                }
            }

            try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
            } catch (final Exception ex) {
                //TODO add exception
            }
        } catch (Exception e) {
            //TODO add exception
        }

        return sb.toString();
    }
}
