package com.encrypt;

import com.util.HttpClientUtils;
import org.junit.Test;
import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MDEncrypt {

    private static final String str = "15484293010429527154842";

    public static void main(String[] args) throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");


        String s = byteToString(md5.digest(str.getBytes()));
        System.out.println(s);
    }

    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    private static final String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


    @Test
    public void test() {


        String url = "https://speed-api.mytokenapi.com/media/medialist?type=2&keyword=KOL&tag=KOL&page=1&size=20&need_pagination=1&" +
                "timestamp={time}&code={code}&platform=web_pc&v=1.0.0&language=zh_CN&legal_currency=CNY";
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            String time = System.currentTimeMillis() + "";
            String saltTime = time + "9527" + time.substring(0, 6);
            String s = byteToString(md5.digest(saltTime.getBytes()));

            System.out.println(time);
            System.out.println(saltTime);
            System.out.println(s);

            String result = url.replace("{time}", time).replace("{code}", s);
            String methodGetResponse = HttpClientUtils.getMethodGetResponse(result);
            System.out.println(result);
            System.out.println(methodGetResponse);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
