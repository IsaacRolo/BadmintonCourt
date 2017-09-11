package pojo;

import java.util.Date;


public class Cost {
    private int id;
    private int isWeekDay;
    private Date startTime;
    private Date endTime;
    private int costPerHour;

    public Cost() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsWeekDay() {
        return isWeekDay;
    }

    public void setIsWeekDay(int isWeekDay) {
        this.isWeekDay = isWeekDay;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(int costPerHour) {
        this.costPerHour = costPerHour;
    }
}
