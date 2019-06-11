package com.sm.service.function;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.sm.business.model.Pair;
import com.sm.business.service.PairService;
import org.iframework.support.spring.context.BaseSpringContextSupport;
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

            HtmlForm form = WebClientUtil.getForm("https://www.buyiju.com/peidui/sxpd.php", "xxpd");
            HtmlSubmitInput button = form.getInputByValue("开始测试");
            final HtmlHiddenInput textField = form.getInputByName("action");
            textField.setValueAttribute("test");

            HtmlSelect sx1 = form.getSelectByName("sx1");
            sx1.setSelectedIndex(index);

            for (int i = 0; i < datas.size(); i ++) {
                HtmlSelect sx2 = form.getSelectByName("sx2");
                sx2.setSelectedIndex(i);

                Element tagElement = WebClientUtil.getElement(button);
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
                }
            }
            System.out.println("====task " + index + "结束执行");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
