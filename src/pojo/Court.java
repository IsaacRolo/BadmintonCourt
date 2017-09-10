package pojo;


import utils.TimeFormat;

import java.util.Date;

/**
 * Created by Elrol on 2017/9/8.
 */
public class Court {
    private int id;
    private String courtName;
    private String userId;
    private Date startDate;
    private Date endDate;
    private int cost;
    private int penalty;

    public Court(){

    }

    public Court(int id,String courtName, String userId, Date startDate, Date endDate, int cost, int penalty) {
        this.id=id;
        this.courtName = courtName;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.penalty = penalty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        if(cost!=0) {
            return TimeFormat.formatDate(startDate) + " " + TimeFormat.formatTime(startDate) + "~" + TimeFormat.formatTime(endDate) + " " + cost + "元";
        }
        else {
            return TimeFormat.formatDate(startDate) + " " + TimeFormat.formatTime(startDate) + "~" + TimeFormat.formatTime(endDate) + " 违约金 " + penalty + "元";

        }
    }
}
