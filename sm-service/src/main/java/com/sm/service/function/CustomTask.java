package com.sm.service.function;

import com.sm.business.model.BirthDateTime;
import com.sm.business.service.BirthDateTimeService;

import org.iframework.support.spring.context.BaseSpringContextSupport;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by liguangcun on 2019/6/3.
 */
public class CustomTask implements Runnable{
    private  int id;
    public CustomTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        BirthDateTimeService birthDateTimeService = (BirthDateTimeService) BaseSpringContextSupport.getApplicationContext().getBean("birthDateTimeService");
        BirthDateTime content =new BirthDateTime();
        content.setMonth(id);
        List<Map<String, Object>> contentList=birthDateTimeService.findBirthDateTime(content);
        for(Map<String, Object> map:contentList){
            BirthDateTime list=new BirthDateTime();
            list.setId(Long.parseLong(map.get("id").toString()));
            list.setYear(Integer.valueOf(map.get("year").toString()));
            list.setMonth(Integer.valueOf(map.get("month").toString()));
            list.setDay(Integer.valueOf(map.get("day").toString()));
            list.setHour(map.get("hour").toString());
            list.setSex(1);
            String con=BrithQueryUtil.getBrith(list);
            if("日期不存在".equals(con)) {
                birthDateTimeService.deleteById(list);//删除主表信息
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
