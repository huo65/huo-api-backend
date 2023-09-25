package com.huo.huo_client_sdk.client;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.huo.huo_client_sdk.model.User;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.huo.huo_client_sdk.utils.SignUtils.genSign;

/**
 * 1. @ClassName simulateApiClient
 * 2. @Description 调用模拟接口的客户端
 * 3. @Author huo
 * 4. @Date 2023/9/17 21:43
 */

public class SimulateApiClient {
    
    public static  String GATEWAY_HOST = "http://localhost:8090";
    
    private String accessKey;

    private String secretKey;

    public SimulateApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public void setGatewayHost(String gatewayHost) {
        GATEWAY_HOST = gatewayHost;
    }


    private Map<String, String> getHeaderMap(String body, String method) throws UnsupportedEncodingException {
        HashMap<String, String> map = new HashMap<>();
        map.put("accessKey", accessKey);
        map.put("nonce", RandomUtil.randomNumbers(10));
        map.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        map.put("sign", genSign(body, secretKey));
        body = URLUtil.encode(body, CharsetUtil.CHARSET_UTF_8);
        map.put("body", body);
        map.put("method", method);
        return map;
    }

    public String invokeInterface(String params, String url, String method) throws UnsupportedEncodingException {
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + url)
                .header("Accept-Charset", CharsetUtil.UTF_8)
                .addHeaders(getHeaderMap(params, method))
                .body(params)
                .execute();
        return JSONUtil.formatJsonStr(httpResponse.body());
    }


}
