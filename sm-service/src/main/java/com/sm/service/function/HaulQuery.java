package com.sm.service.function;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.*;
import com.sm.business.model.BirthDateTime;
import com.sm.business.model.Haul;
import com.sm.business.service.HaulService;
import com.sm.service.util.WebClientUtil;
import org.iframework.support.spring.context.BaseSpringContextSupport;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by liguangcun on 2019/5/29.
 */
public class HaulQuery {
    public static String getBrith(BirthDateTime content) {
        String cont = "";
        try {
            HaulService haulService = (HaulService) BaseSpringContextSupport.getApplicationContext().getBean("haulService");
            HtmlForm form = WebClientUtil.getForm("https://www.buyiju.com/bazi/", "sm");
            final HtmlSubmitInput button = form.getInputByValue("开始算命");
            final HtmlHiddenInput textField = form.getInputByName("action");
            //设置搜索框的value
            textField.setValueAttribute("test");
            HtmlSelect txtUName1 = form.getSelectByName("year");
            txtUName1.setSelectedIndex(content.getYear() - 1945);
            HtmlSelect txtUName2 = form.getSelectByName("month");
            txtUName2.setSelectedIndex(content.getMonth() - 1);
            HtmlSelect txtUNameg1 = form.getSelectByName("day");
            txtUNameg1.setSelectedIndex(content.getDay() - 1);
            HtmlSelect txtUNameg2 = form.getSelectByName("hour");
            txtUNameg2.setSelectedIndex(Integer.valueOf(content.getHour()));
            if (content.getSex() == 0) {
                // 得到搜索框
                final List<HtmlRadioButtonInput> radioButtons = form.getRadioButtonsByName("sex");
                radioButtons.get(0).setChecked(false);
                radioButtons.get(1).setChecked(true);// 选中限定时间段的radion button
            } else {
               // 得到搜索框
                final List<HtmlRadioButtonInput> radioButtons = form.getRadioButtonsByName("sex");
                radioButtons.get(0).setChecked(true);
                radioButtons.get(1).setChecked(false);// 选中限定时间段的radion button
            }
            //获得配对文本结果
            Element tagElement = WebClientUtil.getElement(button);
            if (tagElement != null) {
                tagElement.select("div.inform_vip").remove();
                tagElement.select("table").get(0).remove();
                tagElement.getElementsByTag("p").last().remove();
                tagElement.getElementsByTag("p").last().remove();
                tagElement.getElementsByTag("p").get(4).remove();
                cont = tagElement.toString().replace(" ", "").replace("\n", "").replace("</div>", "").replace("<divclass=\"content\">", "").replace("<aname=\"csshow\"></a>", "");
                Haul haul = new Haul();
                haul.setBirthDateTimeId(content.getId());
                haul.setContent(cont);
                haulService.save(haul);
            } else {
                cont = "日期不存在";
                System.out.println("日期不存在！" + content.getYear() + content.getMonth() + content.getDay());
            }
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        }
        return cont;
    }

}

