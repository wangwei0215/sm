package com.sm.service.util;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by liguangcun on 2019/6/11.
 */
public class CommonUtils {
    public static void print(HttpServletResponse response, Map<String,String> map) throws Exception{
        JSONObject json = JSONObject.fromObject(map);
        String result = "showData" + "(" + json.toString() + ")";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(result);
    }
}
