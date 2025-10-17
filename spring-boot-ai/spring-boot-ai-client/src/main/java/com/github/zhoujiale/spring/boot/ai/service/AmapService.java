package com.github.zhoujiale.spring.boot.ai.service;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @classname: AmapService
 * @author: zhou
 * @description:
 * @date: 2025/9/11 13:23
 */
public class AmapService {

    private static final String KEY = "xxxxxxx";

    @Tool(description = "获取地址经纬度")
    public String getGeo(@ToolParam(description = "地址") String address, @ToolParam(description = "城市") String city) throws IOException, InterruptedException {
        URI uri = URI.create("https://restapi.amap.com/v3/geocode/geo?address=" + address + "&city=" + city + "&key=" + KEY);
        HttpClient httpClient = HttpClient.newBuilder()
                .build();
        String body = httpClient.send(HttpRequest.newBuilder().uri(uri).GET().build(), HttpResponse.BodyHandlers.ofString())
                .body();
        return JSONObject.parseObject(body).getJSONArray("geocodes").getJSONObject(0).getString("location");
    }

    @Tool(description = "获取驾车路线规划")
    public String getDrivingLine(@ToolParam(description = "起点经纬度") String origin, @ToolParam(description = "终点经纬度") String destination) throws IOException, InterruptedException {
        System.out.println(origin);
        System.out.println(destination);
        URI uri = URI.create("https://restapi.amap.com/v5/direction/driving?origin=" + origin + "&destination=" + destination + "&key=" + KEY);
        HttpClient httpClient = HttpClient.newBuilder()
                .build();
        String body = httpClient.send(HttpRequest.newBuilder().uri(uri).GET().build(), HttpResponse.BodyHandlers.ofString())
                .body();
        System.out.println(body);
        return JSONObject.parseObject(body).getJSONObject("route").getJSONArray("paths").get(0).toString();
    }

    @Tool(description = "获取公共交通路线规划")
    public String getTransportLing(@ToolParam(description = "起点经纬度")String origin,@ToolParam(description = "终点经纬度")String destination) throws IOException, InterruptedException {
        String cityCode = "0571";
        System.out.println(origin);
        System.out.println(destination);
        URI uri = URI.create("https://restapi.amap.com/v5/direction/transit/integrated?origin=" + origin + "&destination=" + destination + "&city1=" + cityCode
                + "&city2=" + cityCode + "&key=" + KEY);
        HttpClient httpClient = HttpClient.newBuilder()
                .build();
        String body = httpClient.send(HttpRequest.newBuilder().uri(uri).GET().build(), HttpResponse.BodyHandlers.ofString())
                .body();
        System.out.println(body);
        return JSONObject.parseObject(body).getJSONObject("route").getJSONArray("transits").get(0).toString();
    }
}
