package com.zjl.commons.util.netty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhou
 * @version 1.0
 * @className MapUtils
 * @description
 * @date 2022/02/17 20:41
 **/
public class MapUtils {
    //1.自定义指令
    private static Byte codeLoginReq=1;
    private static Byte codeLoginRes=2;
    private static Byte codeMsgReq=3;
    private static Byte codeMsgRes=4;
    private static Byte codeMsgRec=5;

    //2.自定义一个Map，专门管理指令和实体的关系
    private static Map<Byte, Class<? extends BaseEntity>> map=new HashMap<Byte,Class<? extends BaseEntity>>();
    //3.初始化
    static {
        map.put(codeLoginReq,LoginReqEntity.class);
        map.put(codeLoginRes,LoginResEntity.class);
        map.put(codeMsgReq,MsgReqEntity.class);
        map.put(codeMsgRes,MsgResEntity.class);
        map.put(codeMsgRec,MsgRecEntity.class);
    }

    //4.根据指令获取对应的实体
    public static Class<? extends BaseEntity> getBean(Byte code){
        try{
            return map.get(code);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}

