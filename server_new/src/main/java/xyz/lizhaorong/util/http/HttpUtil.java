package xyz.lizhaorong.util.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于发送http请求
 */
@Slf4j
public class HttpUtil {
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    Map<String, String> headers = new HashMap<>();

    /**
     * 设置http头
     * @param k key
     * @param v value
     * @return 原对象，支持链式调用
     */
    public HttpUtil setHeader(String k, String v){
        headers.put(k,v);
        return this;
    }

    /**
     * 使用Object作为数据进行get
     * @param url 请求的链接
     * @param data 传入的数据，使用application/json
     * @param retClass 返回值的类型
     * @return post收到的数据
     * @throws IOException json转换、获取数据可能会出现问题
     */
    public <T>T get(String url, Object data, Class<T> retClass) throws IOException {
        Map<String, String> map;
        StringBuilder sb = new StringBuilder(url);
        sb.append('?');
        if(!(data instanceof Map)){
            String s = jsonMapper.writeValueAsString(data);
            map = jsonMapper.readValue(s, Map.class);
        } else {
            map = (Map)data;
        }
        map.forEach((k,v)->{
            sb.append(k).append('=').append(v).append('&');
        });
        sb.deleteCharAt(sb.length()-1);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(sb.toString());
        headers.forEach(httpGet::setHeader);
        CloseableHttpResponse response = client.execute(httpGet);
        String back = checkAndGetResponse(response);
        client.close();
        return jsonMapper.readValue(back,retClass);
    }

    /**
     * 使用Object作为数据进行post
     * @param url 请求的链接
     * @param data 传入的数据，使用application/json
     * @param retClass 返回值的类型
     * @return post收到的数据
     * @throws IOException json转换、获取数据可能会出现问题
     */
    public <T>T post(String url, Object data , Class<T> retClass) throws IOException {
        String json = jsonMapper.writeValueAsString(data);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringEntity entity = new StringEntity(json, Consts.UTF_8);
        entity.setContentType("application/json");
        HttpPost httpPost = new HttpPost(url);
        //设置http头
        headers.forEach(httpPost::setHeader);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String back = checkAndGetResponse(response);
        httpClient.close();
        return jsonMapper.readValue(back,retClass);
    }

    /**
     * 检查响应状态，并获取数据
     * @param response CloseableHttpResponse
     * @return 字符串数据
     */
    private String checkAndGetResponse(CloseableHttpResponse response) throws IOException {
        int status = response.getStatusLine().getStatusCode();
        if (status > 400) {
            log.error("**********   request failed   **********");
        }
        String back = EntityUtils.toString(response.getEntity(),"UTF-8");
        response.close();
        return back;
    }
}
