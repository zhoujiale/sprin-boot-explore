package com.github.zhoujiale.commons.util.ip;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhou
 * @version 1.0
 * @className IPUtil
 * @description ip 工具类
 * @date 2021/09/01 21:28
 **/
@Slf4j
public class IPUtil {

    private static final String UNKNOWN = "unknown";
    private static final String LOCAL_HOST = "127.0.0.1";
    private static final String IP_ZERO = "0:0:0:0:0:0:0:1";


    /**
     * @description 获取ip
     * @date 2022/9/2 14:11
     * @author zhou
     * @param request 请求
     * @return java.lang.String
     */
    public static String getIp(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        log.debug("x-forwarded-for:{}",ipAddress);
        if(ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        log.debug("Proxy-Client-IP:{}",ipAddress);
        if(ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        log.debug("WL-Proxy-Client-IP",ipAddress);
        if(ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals(LOCAL_HOST) || ipAddress.equals(IP_ZERO)){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("根据网卡取本机配置的IP失败[{}]",e.getMessage());
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){
            String c = ",";
            //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(c)>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(c));
            }
        }
        return ipAddress;
    }

}
