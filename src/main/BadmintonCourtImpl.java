package main;

import dao.CostDao;
import dao.CourtDao;
import dao.impl.CostDaoImpl;
import dao.impl.CourtDaoImpl;
import pojo.Cost;
import pojo.Court;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static utils.TimeFormat.*;


public class BadmintonCourtImpl implements BadmintonCourt {
    /**
     * 打印所有场地的收入汇总
     */
    private CourtDao courtDao = new CourtDaoImpl();
    private CostDao costDao = new CostDaoImpl();

    @Override
    public void printCourtsList() {
        String lastCourt = "D";
        System.out.println("收入汇总\n---");
        int countCourtA = printCourtList("A", lastCourt);
        int countCourtC = printCourtList("B", lastCourt);
        int countCourtB = printCourtList("C", lastCourt);
        int countCourtD = printCourtList("D", lastCourt);
        int countCourts = countCourtA + countCourtB + countCourtC + countCourtD;
        System.out.println("---\n总计：" + countCourts + "元");
    }


    /**
     * 打印单个场地的预定列表
     */

    private int printCourtList(String courtName, String lastCourt) {
        List<Court> courts = courtDao.getCourtByName(courtName);
        int countCourt = 0;
        StringBuilder courtList = new StringBuilder().append("场地:").append(courtName).append("\n");
        for (Court court : courts) {
            countCourt += court.getCost() + court.getPenalty();
            courtList.append(court.toString()).append("\n");
        }
        courtList.append("小计：").append(countCourt).append("元");
        if (!courtName.equals(lastCourt)) {
            courtList.append("\n");
        }
        System.out.println(courtList);
        return countCourt;
    }

    /**
     * 将输入的预定信息保存到数据库
     */
    @Override
    public void saveBook(String input) {
        String[] parse = input.split(" ");
        String userId = parse[0];
        String date = parse[1];
        String[] time = parse[2].split("~");
        String startTime = time[0];
        String endTime = time[1];
        String[] startHourSplit = time[0].split(":");
        String[] endHourSplit = time[1].split(":");
        int startHour = Integer.parseInt(startHourSplit[0]);
        int endHour = Integer.parseInt(endHourSplit[0]);
        String courtName = parse[3];
        Date startDate = toDate(date + " " + startTime);
        Date endDate = toDate(date + " " + endTime);
        if (canBook(startDate, endDate, courtName)) {

            int cost = calCost(date, startHour, endHour);

            Court court = new Court();
            court.setCourtName(courtName);
            court.setUserId(userId);
            court.setStartDate(startDate);
            court.setEndDate(endDate);
            court.setCost(cost);


            courtDao.addCourt(court);
            System.out.println("Success: the booking is accepted!");


        }
    }


    /**
     * 计算费用
     */

    private int calCost(String date, int startHour, int endHour) {
        int res = 0;
        int[] costArray = new int[24];
        Date day = toDay(date);
        int isWeekDay = getWeek(day);
        List<Cost> list = costDao.getCostByWeek(isWeekDay);
        for (Cost cost : list) {
            int stanStartTime = Integer.parseInt(formatHour(cost.getStartTime()));
            int stanEndTime = Integer.parseInt(formatHour(cost.getEndTime()));
            Arrays.fill(costArray, stanStartTime, stanEndTime, cost.getCostPerHour());
        }

        for (int i = startHour; i < endHour; i++) {
            res += costArray[i];
        }

        return res;
    }


    /**
     * 判断预定是否与已有预定冲突（场地，日期，时间段）
     */

    private boolean canBook(Date newStartDate, Date newEndDate, String courtName) {

        List<Court> list = courtDao.getCourtByName(courtName);
        for (Court court : list) {
            long startDate = court.getStartDate().getTime();
            long endDate = court.getEndDate().getTime();
            if (newEndDate.getTime() < startDate || newStartDate.getTime() > endDate || court.getCost() == 0) {//被取消预定的条目不用判断预定冲突
                return true;
            } else {
                System.out.println("Error: the booking conflicts with existing bookings!");
                return false;
            }
        }
        return true;
    }


    /**
     * 取消预定
     */
    @Override
    public void cancelBook(String input) {
        String[] parse = input.split(" ");
        String userId = parse[0];
        String date = parse[1];
        String[] time = parse[2].split("~");
        String startTime = time[0];
        String endTime = time[1];
        String[] startHourSplit = time[0].split(":");
        String[] endHourSplit = time[1].split(":");
        String courtName = parse[3];
        String isCancel = parse[4];
        Date startDate = toDate(date + " " + startTime);
        Date endDate = toDate(date + " " + endTime);
        int cost = 0;


        Court court = new Court();
        court.setCourtName(courtName);
        court.setUserId(userId);
        court.setStartDate(startDate);
        court.setEndDate(endDate);


        Court certainCourt = courtDao.getCertainCourt(court);
        if (certainCourt != null) {
            if (certainCourt.getCost() != 0) {
                cost = certainCourt.getCost();
                court.setPenalty(calPenalty(startDate, cost));
                courtDao.updateCourt(court);
                System.out.println("Success: the booking is accepted!");
            } else {
                System.out.println("Error: the booking being cancelled does not exist!");
            }
        } else {
            System.out.println("Error: the booking being cancelled does not exist!");
        }

    }

    /**
     * 计算违约金
     */

    private int calPenalty(Date newStartDate, int cost) {
        if (getWeek(newStartDate) == 1) {
            return (int) (cost * 0.5);
        }
        return (int) (cost * 0.25);
    }
}
