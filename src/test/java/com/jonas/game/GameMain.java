package com.jonas.game;

import com.jonas.game.crawler.SnakeGameGui;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.SSLContext;
import java.io.IOException;

public class GameMain {
    // 解决问题: javax.net.ssl.SSLException: Received fatal alert: protocol_version
    private static final PoolingHttpClientConnectionManager HTTP_CLIENT_CONNECTION_MANAGER;
    private static final CloseableHttpClient HTTP_CLIENT;
    static {
        SSLContext ctx = SSLContexts.createSystemDefault();
        SSLConnectionSocketFactory fac = new SSLConnectionSocketFactory(
                        ctx,
                        new String[]{"SSLv2Hello", "TLSv1.2"},
                        null,
                        SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        HTTP_CLIENT_CONNECTION_MANAGER = new PoolingHttpClientConnectionManager(RegistryBuilder
                .<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", fac).build());
        HTTP_CLIENT_CONNECTION_MANAGER.setDefaultMaxPerRoute(100);
        HTTP_CLIENT_CONNECTION_MANAGER.setMaxTotal(200);
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(60000).setConnectTimeout(60000)
                .setSocketTimeout(60000).build();

        HTTP_CLIENT = HttpClientBuilder.create().setConnectionManager(HTTP_CLIENT_CONNECTION_MANAGER)
                .setDefaultRequestConfig(requestConfig).build();
    }

    public static void main(String[] vag) {
//        reptile("https://www.kubij.cc/148686/503877.html");
        new SnakeGameGui();
    }
    private static void reptile(String url) {
        try {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse httpResponse = HTTP_CLIENT.execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                String result = EntityUtils.toString(httpEntity, "utf-8");
                Document document = Jsoup.parse(result);
                System.out.println(statusLine + "=>" + result + "\n" + document.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
