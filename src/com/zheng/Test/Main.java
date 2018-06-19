package com.zheng.Test;

import com.zheng.Utils.UpLoadExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @Version 1.0
 * @Data 2018/6/19 11:33
 */
public class Main {
    public static void main(String[] args) {
//        //查询所有的统计表数据
//        Result result= selectStatiscsEntityAll();
//        //查询所有的数据
//        List<StatisticsDTO> statisticsEntities = (List<StatisticsDTO>) result.getData();
//        List<String[]> statisticsMessage=new ArrayList<>();
//        for (int i=0;i<statisticsEntities.size();i++){
//            StatisticsDTO statisticsDTO=statisticsEntities.get(i);
//            //遍历数据集合 拿到Excel列
//            String[] statisticsMessages= {""+statisticsDTO.getStatisticsCreateTime(),statisticsDTO.getFinanceType(),statisticsDTO.getCommodityInventory().getCommodityName()+"-"+statisticsDTO.getCommodityInventory().getCommoditySku(),statisticsDTO.getFinanceType(),""+statisticsDTO.getFinancePrice(),""+statisticsDTO.getStatisticsNumber()};
//            statisticsMessage.add(statisticsMessages);
//        }
//        try {
//            //生成Excel
//            UpLoadExcel.exportData(request,response,ServiceConstant.STATISTICS_FILE_NAME,ServiceConstant.statisticsTiele,statisticsMessage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}


