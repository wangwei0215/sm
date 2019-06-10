package com.sm.service.function;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.sm.business.model.Birth;
import com.sm.business.service.BirthService;
import org.iframework.support.spring.context.BaseSpringContextSupport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
            // 得到浏览器对象，直接New一个就能得到，现在就好比说你得到了一个浏览器了
            WebClient webClient = new WebClient();
            // 下面这2句可以写，也可以不写，设置false就是不加载css和js。访问速度更快
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            // 当出现Http error时，程序不抛异常继续执行
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setUseInsecureSSL(false);
            // 做的第一件事，去拿到这个网页，只需要调用getPage这个方法即可
            HtmlPage page = webClient.getPage("https://www.buyiju.com/birth/");
            // 根据名字得到一个表单，查看上面这个网页的源代码可以发现表单的名字叫“f”
            HtmlForm form = page.getFormByName("birth");
            HtmlSubmitInput button = form.getInputByValue("开始测试");

            HtmlSelect sx1 = form.getSelectByName("month");
            sx1.setSelectedIndex(month);

            for (int i = 0; i < 31; i ++) {
                HtmlSelect sx2 = form.getSelectByName("day");
                sx2.setSelectedIndex(i);

                HtmlPage nextPage = button.click();
                // 把获得后的网页转换成document
                Document document = Jsoup.parse(nextPage.asXml());
                // 获得配对文本结果
                Element tagElement = document.getElementsByClass("content").first();
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
