package com.sm.service.function;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.*;
import com.sm.business.model.BirthDateTime;
import com.sm.business.model.Fate;
import com.sm.business.service.FateService;
import com.sm.service.util.WebClientUtil;
import org.iframework.support.spring.context.BaseSpringContextSupport;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by liguangcun on 2019/5/29.
 */
public class CeZiQuery {
    public static String getBrith(String str){
        String cont="";
        try {
            // 得到浏览器对象，直接New一个就能得到，现在就好比说你得到了一个浏览器了
            HtmlForm form = WebClientUtil.getForm("https://www.buyiju.com/cm/cezi/", "buyiju");
            final HtmlSubmitInput button = form.getInputByValue("开始测字");
            final HtmlHiddenInput textField = form.getInputByName("action");
            //设置搜索框的value
            textField.setValueAttribute("test");
            HtmlTextInput  txtUName1 = form.getInputByName("czsm");
            txtUName1.setValueAttribute(str);
            //获得配对文本结果
            Element tagElement = WebClientUtil.getElement(button);
            if (tagElement != null) {
                tagElement.select("div.yunshi").remove();
                tagElement.getElementsByTag("p").last().remove();
                cont = tagElement.toString().replace(" ", "").replace("\n", "").replace("</div>", "").replace("<divclass=\"content\">", "").replace("<aname=\"csshow\"></a>", "");
            } else {
                cont = "请重新输入正确的两个字";
            }
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        }
        return cont;
    }

}

