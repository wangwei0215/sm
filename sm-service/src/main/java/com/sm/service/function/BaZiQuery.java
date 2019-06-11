package com.sm.service.function;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.sm.business.model.BirthDateTime;
import com.sm.business.model.Fate;
import com.sm.business.service.FateService;

import com.sm.service.util.WebClientUtil;
import org.iframework.support.spring.context.BaseSpringContextSupport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

/**
 * Created by liguangcun on 2019/5/29.
 */
public class BaZiQuery {
    public static String getBrith(BirthDateTime content){
        String cont="";
        try {
            FateService fateService = (FateService) BaseSpringContextSupport.getApplicationContext().getBean("fateService");
            // 得到浏览器对象，直接New一个就能得到，现在就好比说你得到了一个浏览器了
            HtmlForm form = WebClientUtil.getForm("https://www.buyiju.com/bazi/", "sm");
            final HtmlSubmitInput button = form.getInputByValue("开始算命");
            final HtmlHiddenInput textField = form.getInputByName("action");
            //设置搜索框的value
            textField.setValueAttribute("test");
//            for(int year=1945;year<1946;year++){
            HtmlSelect txtUName1 = form.getSelectByName("year");
            txtUName1.setSelectedIndex(content.getYear() - 1945);
//                for(int month=1;month<2;month++){
            HtmlSelect txtUName2 = form.getSelectByName("month");
            txtUName2.setSelectedIndex(content.getMonth() - 1);
//                    for(int day=1;day<2;day++){
            HtmlSelect txtUNameg1 = form.getSelectByName("day");
            txtUNameg1.setSelectedIndex(content.getDay() - 1);
//                        for(int hour=0;hour<1;hour++){
            HtmlSelect txtUNameg2 = form.getSelectByName("hour");
            txtUNameg2.setSelectedIndex(Integer.valueOf(content.getHour()));
//                            for(int sex=0;sex<1;sex++){
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
                tagElement.select("div.bz_tb").remove();
                tagElement.select("div.inform_vip").remove();
                tagElement.getElementsByTag("p").get(38).remove();
                tagElement.getElementsByTag("p").get(37).remove();
                tagElement.getElementsByTag("p").get(5).remove();
                cont = tagElement.toString().replace(" ", "").replace("\n", "").replace("</div>", "").replace("<divclass=\"content\">", "").replace("<aname=\"csshow\"></a>", "");
                Fate fate = new Fate();
                fate.setBirthDateTimeId(content.getId());
                fate.setContent(cont);
                fateService.save(fate);
            } else {
                cont = "日期不存在";
                System.out.println("日期不存在！" + content.getYear() + content.getMonth() + content.getDay());
            }

//                            }
//                        }
//                    }

//                }
//            }
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        }
        return cont;
    }

}

