package com.sm.service.function;

import java.io.IOException;

import org.iframework.commons.util.fast.L;
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
		// String[] str={"子 00:00-00:59","丑 01:00-01:59","丑 02:00-02:59","寅
		// 03:00-03:59","寅 04:00-04:59","卯 05:00-05:59","卯 06:00-06:59","辰
		// 07:00-07:59","辰 08:00-08:59","巳 09:00-09:59","巳 10:00-10:59","午
		// 11:00-11:59","午 12:00-12:59","未 13:00-13:59","未 14:00-14:59","申
		// 15:00-15:59","申 16:00-16:59","酉 17:00-17:59","酉 18:00-18:59","戌
		// 19:00-19:59","戌 20:00-20:59","亥 21:00-21:59","亥 22:00-22:59","子
		// 23:00-23:59"};
		// System.out.println("当前开始时间"+new Date());
		L.i("入参年："+cgsm.getYear() + " 入参月："+cgsm.getMonth()+ " 入参日："+cgsm.getDay() + " 入参小时："+cgsm.getHour()  );
		String cont = "";
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
