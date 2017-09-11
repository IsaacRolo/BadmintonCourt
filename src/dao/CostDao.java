package dao;

import pojo.Cost;

import java.util.List;


public interface CostDao {
    List<Cost> getCostByWeek(int isWeekDay);
}
