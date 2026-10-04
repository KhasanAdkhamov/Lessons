package httpTasks.hw.task1;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;

//
public class CheckHttp {
    public static void main(String[] args) {
        try {
            System.out.println(isAvailable("google.ru"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(isAvailableHttps("https://google.ru"));

    }

    public static boolean isAvailable(String url) throws IOException {
        InetAddress urlName = InetAddress.getByName(url);
        return urlName.isReachable(3000);
    }

    public static boolean isAvailableHttps(String url) {
        try {
            URL url1 = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setReadTimeout(3000);
            httpURLConnection.setRequestMethod("HEAD");
            int responseCode = httpURLConnection.getResponseCode();
            System.out.println(responseCode);
            return (responseCode == 200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

