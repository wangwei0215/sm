package com.sm.service.function;

import java.io.IOException;
import java.util.List;

import org.iframework.commons.util.fast.L;
import org.iframework.commons.util.fast.V;
import org.iframework.support.spring.context.BaseSpringContextSupport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.sm.business.model.Cgsm;
import com.sm.business.service.CgsmService;

/**
 * 测骨算命 Created by sh on 2019/06/20.
 */
public class CgsmQueryUtil {
	
	public static String getContent(Cgsm cgsm) {
		
		L.i("入参年："+cgsm.getYear() + " 入参月："+cgsm.getMonth()+ " 入参日："+cgsm.getDay() + " 入参小时："+cgsm.getHour()  );
		String cont = "";
		CgsmService cgsmService = (CgsmService) BaseSpringContextSupport.getApplicationContext().getBean("cgsmService");
		//存在，不用再爬取
		List<Cgsm> findByModel = cgsmService.findByModel(cgsm, null, null);
		if(V.isNotEmpty(findByModel)){
			return findByModel.get(0).getContent();
		}
		try {
			 CgsmService cgSmSercice = (CgsmService)BaseSpringContextSupport.getApplicationContext().getBean("cgsmService");
			// 得到浏览器对象，直接New一个就能得到，现在就好比说你得到了一个浏览器了
			WebClient webclient = new WebClient();

			// 下面这2句可以写，也可以不写，设置false就是不加载css和js。访问速度更快
			webclient.getOptions().setCssEnabled(false);
			webclient.getOptions().setJavaScriptEnabled(false);
			webclient.getOptions().setThrowExceptionOnScriptError(false);
			// 当出现Http error时，程序不抛异常继续执行
			webclient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webclient.getOptions().setUseInsecureSSL(false);
			// 做的第一件事，去拿到这个网页，只需要调用getPage这个方法即可
			HtmlPage page = webclient.getPage("https://www.buyiju.com/cgsm/");
			// 根据名字得到一个表单，查看上面这个网页的源代码可以发现表单的名字叫“f”
			final HtmlForm form = page.getFormByName("bz");
			final HtmlSubmitInput button = form.getInputByValue("开始算命");

			HtmlSelect txtUName1 = form.getSelectByName("year");
			txtUName1.setSelectedAttribute(cgsm.getYear().toString(), true);
			HtmlSelect txtUName2 = form.getSelectByName("month");
			txtUName2.setSelectedAttribute(cgsm.getMonth().toString(), true);
			HtmlSelect txtUNameg1 = form.getSelectByName("day");
			txtUNameg1.setSelectedAttribute(cgsm.getDay().toString(), true);
			HtmlSelect txtUNameg2 = form.getSelectByName("hour");
			txtUNameg2.setSelectedAttribute(cgsm.getHour(), true);

			// 提交表单
			final HtmlPage nextPage = button.click();

			// 把获得后的网页转换成document
			Document document = Jsoup.parse(nextPage.asXml());
			// 获得配对文本结果
			Element tagElement = document.getElementsByClass("content").first();
			if (tagElement != null) {
				tagElement.select("div.bz_tb").remove();
				tagElement.select("div.inform_vip").remove();
				tagElement.getElementsByTag("p").last().remove();
				tagElement.getElementsByTag("p").last().remove();
				cont = tagElement.toString().replace(" ", "").replace("\n", "").replace("</div>", "")
						.replace("<divclass=\"content\">", "").replace("<aname=\"csshow\"></a>", "");
				
				L.i(cont);
				// 入库
				
				cgsm.setContent(cont);
				cgSmSercice.save(cgsm);
			} else {
				// cont="日期不存在";
				// System.out.println("日期不存在！"+content.getYear()+content.getMonth()+content.getDay());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		}
		return cont;
	}

//	public static void main(String[] args) {
//		Integer[] year = {5,6,7,8,9,10,11,12,13,14,15,16};
//		Integer[] month = {5,6,7,8,9,15,16,18};
//		Integer[] day = {5,6,7,8,9,10,15,16,17,18};
//		Integer[] hour = {5,6,7,8,9,10,16};
//		for (int i = 0; i < year.length; i++) {
//			for (int j = 0; j < month.length; j++) {
//				for (int j2 = 0; j2 < day.length; j2++) {
//					for (int k = 0; k < hour.length; k++) {
//						Cgsm cgsm = new Cgsm();
//						cgsm.setYear(year[i]);
//						cgsm.setMonth(month[j]);
//						cgsm.setDay(day[j2]);
//						cgsm.setHour(String.valueOf(hour[k]));
//						CgsmQueryUtil.getContent(cgsm);
//					}
//				}
//			}
//		}
//		
//	}

}
