package com.sm.service.function;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.sm.business.model.Birth;
import com.sm.business.service.BirthService;
import com.sm.service.util.WebClientUtil;
import org.iframework.support.spring.context.BaseSpringContextSupport;
import org.jsoup.nodes.Element;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class BirthdayThread implements Runnable {

    private int month;

    public BirthdayThread(int month) {
        this.month = month;
    }

    @Override
    public void run() {
        try {
            BirthService birthService = (BirthService) BaseSpringContextSupport.getApplicationContext().getBean("birthService");
            System.out.println("====task " + month + "开始执行");

            HtmlForm form = WebClientUtil.getForm("https://www.buyiju.com/birth/", "birth");
            HtmlSubmitInput button = form.getInputByValue("开始测试");

            HtmlSelect sx1 = form.getSelectByName("month");
            sx1.setSelectedIndex(month);

            for (int i = 0; i < 31; i ++) {
                HtmlSelect sx2 = form.getSelectByName("day");
                sx2.setSelectedIndex(i);

                Element tagElement = WebClientUtil.getElement(button);
                if (tagElement != null) {
                    tagElement.select("div.yunshi").remove();
                    tagElement.select("a").remove();
                    String content = tagElement.toString()
                            //.replaceAll("<p>","")
                            //.replaceAll("</p>","")
                            .replaceAll("<div class=\"content\">","")
                            .replaceAll("</div>","")
                            //.replaceAll("<font color=\"#ff0000\">","")
                            //.replaceAll("</font>  ","")
                            .replaceAll("查看更多：   】","");
                    Birth birth = new Birth();
                    birth.setMonth(month + 1 );
                    birth.setDay(i + 1);
                    List<Birth> births = birthService.findByModel(birth, null, null);
                    if (CollectionUtils.isEmpty(births)) {
                        birth.setContent(content);
                        birthService.insert(birth);
                    }
                }
            }
            System.out.println("====task " + month + "结束执行");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
