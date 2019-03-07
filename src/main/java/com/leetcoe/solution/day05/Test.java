package com.leetcoe.solution.day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Test {

/*    public static void main(String[] args) throws Exception {

        //生成模拟日志文件
        LogAnalysis logAnalysis = new LogAnalysis();
        File log = new File("ip.txt");
        log.createNewFile();
        logAnalysis.creatLog(log, 10000);//节省时间

        //分割日志文件
        logAnalysis.splitLog(log, 1024);

        //分析文件
        File logSplits = new File("logSplit");
        for (File logSplit : logSplits.listFiles()) {
            logAnalysis.analysis(logSplit);
        }
        for (IP o : logAnalysis.set) {
            System.out.println(o.ip + "---" + o.nums);
        }
    }*/

    public static void main(String[] args) {

        try {
            String lat = "39.983424"; // 纬度
            // String lng = "116.322987";  // 经度
            //  AK 百度开放平台申请的KEY
             URL url = new URL("http://api.map.baidu.com/geocoder/v2/?ak=wPPxsfCtNFm4PqEV68jbDdjP7U4twstj&callback=renderReverse&location="+ lat + "," + lat + "&output=json&pois=1");
             HttpURLConnection ucon = (HttpURLConnection) url.openConnection();
             ucon.connect();
             InputStream in = ucon.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
             String str = reader.readLine();
             str = str.substring(str.indexOf("(") + 1, str.length()-1);
             //outprint.print(str);
            // JSONObject jsonObject =  JSONObject.parseObject(str);
            String address = "";
            // address = jsonObject.getJSONObject("result").getString("formatted_address");
            in.close();
            reader.close();

            /*outprint.print(address);
             outprint.close();
             outprint=null;  */
        }catch(Exception e){
            e.printStackTrace();
        }

        }

    }
