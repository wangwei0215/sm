package com.sm.service.function;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.sm.service.util.WebClientUtil;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class PhoneUtil {

    private static Logger logger = LoggerFactory.getLogger(PhoneUtil.class);
    public static Map<String, Object> getResult(String phone) {

        Map<String, Object> result = new HashMap<>();
        try {
            HtmlForm form = WebClientUtil.getForm("https://www.buyiju.com/shouji/", "buyiju");
            HtmlSubmitInput button = form.getInputByValue("开始测试");

            HtmlTextInput input = form.getInputByName("sjhao");
            input.setValueAttribute(phone);

            Element tagElement = WebClientUtil.getElement(button);
            if (tagElement != null) {
                tagElement.select("div.inform_vip").remove();
                tagElement.getElementsByTag("p").get(7).remove();

                String content = tagElement.toString()
                        .replaceAll("<div class=\"content\">","")
                        .replaceAll("</div>","");
                result.put("data",content);
                logger.info("=======================" + content);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
