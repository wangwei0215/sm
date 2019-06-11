package com.sm.service.util;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by liguangcun on 2019/6/11.
 */
public class CommonUtils {
    public static void print(HttpServletResponse response, Map<String,Object> map) throws Exception{
        JSONObject json = JSONObject.fromObject(map);
        String result = "showData" + "(" + json.toString() + ")";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(result);
    }
    public static String replace(String con){
        return  con.replace("<strong>男命：</strong>","<strong>女命：</strong>").replace("日0:00-","日23:00-").replace("日2:00-","日1:00-")
                .replace("日4:00-","日3:00-").replace("日6:00-","日5:00-").replace("日8:00-","日7:00-").replace("日10:00-","日9:00-")
                .replace("日12:00-","日11:00-").replace("日14:00-","日13:00-").replace("日16:00-","日15:00-").replace("日18:00-","日17:00-")
                .replace("日20:00-","日19:00-").replace("日22:00-","日21:00-");
    }
}
