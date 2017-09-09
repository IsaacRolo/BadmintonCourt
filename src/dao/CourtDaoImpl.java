package dao;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Court;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class CourtDaoImpl {
    private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());


    public List<Court> getCourtByName(String courtName) {
        List<Court> courts = null;
        String sql = "SELECT * FROM court where courtName=?";
        Object[] params = {courtName};
        try {
            courts=qr.query(sql, params,new BeanListHandler<>(Court.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courts;

    }

    public void addCourt(Court court) {
        String sql = "INSERT INTO court VALUES(?,?,?,?,?,?,?)";
        Object[] params = {null,court.getCourtName(),court.getUserId(),court.getStartDate(),court.getEndDate(),court.getCost(),0};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateCourt(Court court){
        String sql="UPDATE court set penalty=?,cost=0 where userId=? and startDate=? and endDate=? and courtName=?";
        Object[] params={court.getPenalty(),court.getUserId(),court.getStartDate(),court.getEndDate(),court.getCourtName()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
