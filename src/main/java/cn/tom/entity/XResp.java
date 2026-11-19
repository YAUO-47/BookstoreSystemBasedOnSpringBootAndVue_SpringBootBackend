package cn.tom.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class XResp {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static XJson pack(int code , String msg , Object data){
        return new XJson(code,msg,data);
    }

    public static Map<String,Object> packM(int code , String msg , Object data){
        HashMap<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",msg);
        map.put("data",data);
        return map;
    }

    public static String packJson(int code , String msg , Object data){
        HashMap<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",msg);
        map.put("data",data);
        String json="";
        try{
            json = objectMapper.writeValueAsString(map);
        }catch (Exception e){
            System.out.println("Json转换失败");
        }
        return json;
    }
}
