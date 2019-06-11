package com.sm.service.function;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.sm.business.model.BirthDateTime;
import com.sm.business.model.Fate;
import com.sm.business.service.FateService;

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
//        String[] str={"子 00:00-00:59","丑 01:00-01:59","丑 02:00-02:59","寅 03:00-03:59","寅 04:00-04:59","卯 05:00-05:59","卯 06:00-06:59","辰 07:00-07:59","辰 08:00-08:59","巳 09:00-09:59","巳 10:00-10:59","午 11:00-11:59","午 12:00-12:59","未 13:00-13:59","未 14:00-14:59","申 15:00-15:59","申 16:00-16:59","酉 17:00-17:59","酉 18:00-18:59","戌 19:00-19:59","戌 20:00-20:59","亥 21:00-21:59","亥 22:00-22:59","子 23:00-23:59"};
//        System.out.println("当前开始时间"+new Date());
        String cont="";
        try {
            FateService fateService = (FateService) BaseSpringContextSupport.getApplicationContext().getBean("fateService");
            // 得到浏览器对象，直接New一个就能得到，现在就好比说你得到了一个浏览器了
            WebClient webclient = new WebClient();

//下面这2句可以写，也可以不写，设置false就是不加载css和js。访问速度更快
            webclient.getOptions().setCssEnabled(false);
            webclient.getOptions().setJavaScriptEnabled(false);
            webclient.getOptions().setThrowExceptionOnScriptError(false);
            // 当出现Http error时，程序不抛异常继续执行
            webclient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webclient.getOptions().setUseInsecureSSL(false);
            // 做的第一件事，去拿到这个网页，只需要调用getPage这个方法即可
            HtmlPage page = webclient.getPage("https://www.buyiju.com/bazi/");
            // 根据名字得到一个表单，查看上面这个网页的源代码可以发现表单的名字叫“f”
            final HtmlForm form = page.getFormByName("sm");
            final HtmlSubmitInput button = form.getInputByValue("开始算命");
            final HtmlHiddenInput textField = form.getInputByName("action");
            //设置搜索框的value
            textField.setValueAttribute("test");
//            for(int year=1945;year<1946;year++){
                HtmlSelect txtUName1 = form.getSelectByName("year");
                txtUName1.setSelectedIndex(content.getYear()-1945);
//                for(int month=1;month<2;month++){
                    HtmlSelect  txtUName2 = form.getSelectByName("month");
                    txtUName2.setSelectedIndex(content.getMonth()-1);
//                    for(int day=1;day<2;day++){
                        HtmlSelect txtUNameg1 = form.getSelectByName("day");
                        txtUNameg1.setSelectedIndex(content.getDay()-1);
//                        for(int hour=0;hour<1;hour++){
                            HtmlSelect  txtUNameg2 = form.getSelectByName("hour");
                            txtUNameg2.setSelectedIndex(Integer.valueOf(content.getHour()));
//                            for(int sex=0;sex<1;sex++){
                                if(content.getSex()==0) {
                                    // 得到搜索框
                                    final List<HtmlRadioButtonInput> radioButtons = form.getRadioButtonsByName("sex");
                                    radioButtons.get(0).setChecked(false);
                                    radioButtons.get(1).setChecked(true);// 选中限定时间段的radion button
                                }else{
// 得到搜索框
                                    final List<HtmlRadioButtonInput> radioButtons = form.getRadioButtonsByName("sex");
                                    radioButtons.get(0).setChecked(true);
                                    radioButtons.get(1).setChecked(false);// 选中限定时间段的radion button
                                }
                                //提交表单
                                final HtmlPage nextPage = button.click();

                                //把获得后的网页转换成document
                                Document document = Jsoup.parse(nextPage.asXml());
                                //获得配对文本结果
                                Element tagElement = document.getElementsByClass("content").first();
                                if(tagElement!=null) {
                                    tagElement.select("div.bz_tb").remove();
                                    tagElement.select("div.inform_vip").remove();
                                    tagElement.getElementsByTag("p").get(38).remove();
                                    tagElement.getElementsByTag("p").get(37).remove();
                                    tagElement.getElementsByTag("p").get(5).remove();
                                    cont=tagElement.toString().replace(" ", "").replace("\n", "").replace("</div>", "").replace("<divclass=\"content\">", "").replace("<aname=\"csshow\"></a>", "");
                                    Fate fate=new Fate();
                                    fate.setBirthDateTimeId(content.getId());
                                    fate.setContent(cont);
                                    fateService.save(fate);
                                }else{
                                    cont="日期不存在";
                                    System.out.println("日期不存在！"+content.getYear()+content.getMonth()+content.getDay());
                                }

//                            }
//                        }
//                    }

//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        }
        return cont;
    }

}

