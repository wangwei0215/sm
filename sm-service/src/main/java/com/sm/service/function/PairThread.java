package com.sm.service.function;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.sm.business.model.Pair;
import com.sm.business.service.PairService;
import org.iframework.support.spring.context.BaseSpringContextSupport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairThread implements Runnable {

    private Map<String, Integer> params = new HashMap<>();
    private List<String> datas = new ArrayList<>();
    private String key;
    private int index;
    public PairThread(String key, int index,List<String> datas ) {

        this.key = key;
        this.index = index;
        this.datas = datas;
    }

    @Override
    public void run() {
        try {
            PairService pairService = (PairService) BaseSpringContextSupport.getApplicationContext().getBean("pairService");
            System.out.println("====task " + index + "开始执行");
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
            HtmlPage page = webClient.getPage("https://www.buyiju.com/peidui/sxpd.php");
            // 根据名字得到一个表单，查看上面这个网页的源代码可以发现表单的名字叫“f”
            HtmlForm form = page.getFormByName("xxpd");
            HtmlSubmitInput button = form.getInputByValue("开始测试");
            final HtmlHiddenInput textField = form.getInputByName("action");
            textField.setValueAttribute("test");

            HtmlSelect sx1 = form.getSelectByName("sx1");
            sx1.setSelectedIndex(index);

            for (int i = 0; i < datas.size(); i ++) {
                HtmlSelect sx2 = form.getSelectByName("sx2");
                sx2.setSelectedIndex(i);

                HtmlPage nextPage = button.click();
                // 把获得后的网页转换成document
                Document document = Jsoup.parse(nextPage.asXml());
                // 获得配对文本结果
                Element tagElement = document.getElementsByClass("content").first();
                if (tagElement != null) {
                    tagElement.select("div.yunshi").remove();
                    String content = tagElement.toString()
                            //.replaceAll("<p>","")
                            //.replaceAll("</p>","")
                            .replaceAll("<div class=\"content\">","")
                            .replaceAll("</div>","")
                            //.replaceAll("<font color=\"#ff0000\">","")
                            //.replaceAll("</font>","")
                            //.replaceAll("<b>","")
                            //.replaceAll("</b>","")
                        ;
                    Pair pair = new Pair();
                    pair.setZodiac1(key);
                    pair.setZodiac2(datas.get(i));
                    List<Pair> pairs = pairService.findByModel(pair, null, null);
                    if (CollectionUtils.isEmpty(pairs)) {
                        pair.setContent(content);
                        pairService.insert(pair);
                    }

                    // System.out.println(key + "VS" + datas.get(i));
                    // System.out.println(content);
                }
            }
            System.out.println("====task " + index + "结束执行");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
