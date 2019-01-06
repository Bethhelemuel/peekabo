package com.peekaboo.spacehead.peekaboo.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Troy on 5/8/2018.
 */

public class Utility {

    public static  String api_key="4945275050f30e961d264fe1ff6cce1f";
    public static String news_api_key="d102b24728da48af9b308e1fe4895e32";




    public static String getJson(String base_url){

        String returnedJson="";
        try {
            URL url=new URL(base_url);

            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));

            String line="";


            while(line !=null){

                line=bufferedReader.readLine();
                returnedJson= returnedJson + line;
            }




        } catch (Exception e) {
            Log.v("Message E", e.getMessage());
            e.printStackTrace();

            Log.v("Error ma G: ", " " + e.getMessage());
        }

        return returnedJson;
    }

    public static String newsDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        String todate = dateFormat.format(date);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        Date todate1 = cal.getTime();
        String fromdate = dateFormat.format(todate1);


        return fromdate;
}


    public static  boolean isConnected(Context mContext) {

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);


        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else{
            connected = false;

        }

        return connected;
    }

    public static boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal==0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }








    public static String format(double value) {

        if(value!=0){
            int power;
            String suffix = " kmbt";
            String formattedNumber = "";

            NumberFormat formatter = new DecimalFormat("#,###.#");
            power = (int)StrictMath.log10(value);
            value = value/(Math.pow(10,(power/3)*3));
            formattedNumber=formatter.format(value);
            formattedNumber = formattedNumber + suffix.charAt(power/3);
            return formattedNumber.length()>4 ?  formattedNumber.replaceAll("\\.[0-9]+", "") : formattedNumber;


        }else{

            return "Don't know";
        }





    }

}
