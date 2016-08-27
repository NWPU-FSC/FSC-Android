package com.banixc.fsc.api;

import com.banixc.fsc.model.Error;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpEngine {
    private final static boolean DEBUG = true;

    private static String SERVER_URL;
    private final static String REQUEST_MOTHOD = "POST";
    private final static String ENCODE_TYPE = "UTF-8";
    private final static int TIME_OUT = 15000;
    private final static int SYNTAXERROE_CODE = -15001;
    private final static String SYNTAXERROE_MESSAGE = "JSONSyntaxError";

    private static HttpEngine instance = null;

    private HttpEngine() {

    }

    public static String getServerUrl(){
        return SERVER_URL;
    }
    public static void setServerUrl(String url){
        SERVER_URL = url;
    }

    public static HttpEngine getInstance() {
        if (instance == null) {
            instance = new HttpEngine();
        }
        return instance;
    }

    public <T> Receive<T> postHandle(Map<String, Object> paramsMap, Type typeOfT) throws IOException {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        String data = gson.toJson(paramsMap);
        if (DEBUG) {
            System.out.println("[HttpEngine][Send]: " + data);
        }
        HttpURLConnection connection = getConnection();
        connection.setRequestProperty("Content-Length", String.valueOf(data.getBytes().length));
        connection.connect();
        OutputStream os = connection.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        if (connection.getResponseCode() == 200) {
            // 获取响应的输入流对象
            InputStream is = connection.getInputStream();
            // 创建字节输出流对象
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 定义读取的长度
            int len = 0;
            // 定义缓冲区
            byte buffer[] = new byte[1024];
            // 按照缓冲区的大小，循环读取
            while ((len = is.read(buffer)) != -1) {
                // 根据读取的长度写入到os对象中
                baos.write(buffer, 0, len);
            }
            // 释放资源
            is.close();
            baos.close();
            connection.disconnect();
            // 返回字符串
            final String result = new String(baos.toByteArray());
            if (DEBUG) {
                System.out.println("[HttpEngine][Receive]: " + result);
            }
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (200 == jsonObject.getInt("status")) {
                    return new Receive(gson.fromJson(jsonObject.get("result").toString(), typeOfT), null);
                } else {
                    return new Receive<>(null, new Error(jsonObject.getInt("status"), jsonObject.getString("message")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return new Receive<>(null, new Error(SYNTAXERROE_CODE, SYNTAXERROE_MESSAGE));
            }
        } else {
            connection.disconnect();
            return null;
        }
    }

    private HttpURLConnection getConnection() {
        HttpURLConnection connection = null;
        // 初始化connection
        try {
            // 根据地址创建URL对象
            URL url = new URL(SERVER_URL);
            // 根据URL对象打开链接
            connection = (HttpURLConnection) url.openConnection();
            // 设置请求的方式
            connection.setRequestMethod(REQUEST_MOTHOD);
            // 发送POST请求必须设置允许输入，默认为true
            connection.setDoInput(true);
            // 发送POST请求必须设置允许输出
            connection.setDoOutput(true);
            // 设置不使用缓存
            connection.setUseCaches(false);
            // 设置请求的超时时间
            connection.setReadTimeout(TIME_OUT);
            connection.setConnectTimeout(TIME_OUT);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Response-Type", "json");
            connection.setChunkedStreamingMode(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}