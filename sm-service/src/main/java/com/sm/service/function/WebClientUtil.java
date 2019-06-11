package com.sm.service.function;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebClientUtil {

    public static HtmlForm getForm (String url, String formName) {
        HtmlForm form = null;
        try {
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
            HtmlPage page = webClient.getPage(url);
            // 根据名字得到一个表单，查看上面这个网页的源代码可以发现表单的名字叫“f”
            form = page.getFormByName(formName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return form;
    }

    public static Element getElement(HtmlSubmitInput button) {
        Element tagElement = null;
        try {
            HtmlPage nextPage = button.click();
            // 把获得后的网页转换成document
            Document document = Jsoup.parse(nextPage.asXml());
            // 获得配对文本结果
            tagElement = document.getElementsByClass("content").first();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tagElement;
    }
}
