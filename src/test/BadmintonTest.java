package test;

import dao.CostDaoImpl;
import dao.CourtDaoImpl;
import org.junit.Test;
import pojo.Cost;
import pojo.Court;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static utils.TimeFormat.*;

/**
 * Created by Elrol on 2017/9/8.
 */
public class BadmintonTest {
    @Test
    public void printIncomeTest() {
        printCourtsList();
    }

    /**
     * 打印所有场地的收入汇总
     */
    private void printCourtsList() {
        String lastCourt="D";
        System.out.println("收入汇总\n---");
        float countCourtA = printCourtList("A",lastCourt);
        float countCourtB = printCourtList("C",lastCourt);
        float countCourtC = printCourtList("B",lastCourt);
        float countCourtD = printCourtList("D",lastCourt);
        float countCourts = countCourtA + countCourtB + countCourtC + countCourtD;
        System.out.println("---\n总计：" + countCourts+"元");
    }


    /**
     * 打印单个场地的预定列表
     */
    private float printCourtList(String courtName, String lastCourt) {
        CourtDaoImpl courtDao = new CourtDaoImpl();
        List<Court> courts = courtDao.getCourtByName(courtName);
        float countCourt = 0f;
        StringBuilder courtList = new StringBuilder().append("场地:").append(courtName).append("\n");
        for (Court court : courts) {
            countCourt += court.getCost() + court.getPenalty();
            courtList.append(court.toString()).append("\n");
        }
        courtList.append("小计：").append(countCourt).append("元");
        if(!courtName.equals(lastCourt)){
            courtList.append("\n");
        }
        System.out.println(courtList);
        return countCourt;
    }


    @Test
    public void saveBookTest() throws Exception {
        saveBook("U002 2017-08-01 19:00~23:00 A");
    }


    /**
     * 将输入的预定信息保存到数据库
     */
    private void saveBook(String input) {
        String[] parse=parseInput(input);
        String userId = parse[0];
        String date = parse[1];
        String[] time=parse[2].split("~");
        String startTime=time[0];
        String endTime=time[1];
        String[] startHourSplit=time[0].split(":");
        String[] endHourSplit=time[1].split(":");
        int startHour= Integer.parseInt(startHourSplit[0]);
        int endHour= Integer.parseInt(endHourSplit[0]);
        String courtName = parse[3];
        Date startDate = toDate(date + " " + startTime);
        Date endDate = toDate(date + " " + endTime);
        if (isInteger(startHourSplit[1],endHourSplit[1])&&isExist(startDate,endDate,courtName)) {

            int cost = calCost(date, startHour, endHour);

            Court court = new Court();
            court.setCourtName(courtName);
            court.setUserId(userId);
            court.setStartDate(startDate);
            court.setEndDate(endDate);
            court.setCost(cost);

            CourtDaoImpl courtDao = new CourtDaoImpl();
            courtDao.addCourt(court);

        }
    }

    private String[] parseInput(String input) {
        String[] res=input.split(" ");
        return res;
    }



    /**
     * 计算每一条记录的费用
     */
    private int calCost(String date, int startHour, int endHour) {
        int res=0;
        int[] costArray=new int[24];
        Date day=toDay(date);
        String isWeekDay=getWeek(day);
        CostDaoImpl costDao=new CostDaoImpl();
        List<Cost> list=costDao.getCostByWeek(isWeekDay);
        for (Cost cost : list) {
            int stanStartTime= Integer.parseInt(formatHour(cost.getStartTime()));
            int stanEndTime= Integer.parseInt(formatHour(cost.getEndTime()));
            Arrays.fill(costArray,stanStartTime,stanEndTime,cost.getCostPerHour());

        }


        for (int i = startHour; i <endHour ; i++) {
            res+=costArray[i];
        }

        return res;
    }


    /**
     * 判断预定是否与已有预定冲突
     */
    private boolean isExist(Date newStartDate, Date newEndDate, String courtName){
        CourtDaoImpl courtDao=new CourtDaoImpl();
        List<Court> list=courtDao.getCourtByName(courtName);
        for (Court court : list) {
            long startDate=court.getStartDate().getTime();
            long endDate=court.getEndDate().getTime();
            if(newEndDate.getTime()<startDate||newStartDate.getTime()>endDate){
                System.out.println("预定与已有预定冲突，预定失败");
                return false;
            }
        }
        return true;
    }

    /**
     * 判断输入时间是否为整数小时
     */
    private boolean isInteger(String startMinutes,String endMinutes){
        if (!(startMinutes.equals("00")&&endMinutes.equals("00"))){
            System.out.println("时间段的起止必须为整数小时");
            return false;
        }
        return true;
    }
}

