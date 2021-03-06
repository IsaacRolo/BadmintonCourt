package dao.impl;


import dao.CourtDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Court;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class CourtDaoImpl implements CourtDao {
    private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());

    @Override
    public List<Court> getCourtByName(String courtName) {
        List<Court> courts = null;
        String sql = "SELECT * FROM court where courtName=? order by startDate asc";
        Object[] params = {courtName};
        try {
            courts = qr.query(sql, params, new BeanListHandler<>(Court.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courts;

    }

    @Override
    public void addCourt(Court court) {
        String sql = "INSERT INTO court VALUES(?,?,?,?,?,?,?)";
        Object[] params = {null, court.getCourtName(), court.getUserId(), court.getStartDate(), court.getEndDate(), court.getCost(), 0};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateCourt(Court court) {
        String sql = "UPDATE court set penalty=?,cost=0 where userId=? and startDate=? and endDate=? and courtName=?";
        Object[] params = {court.getPenalty(), court.getUserId(), court.getStartDate(), court.getEndDate(), court.getCourtName()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Court getCertainCourt(Court court) {
        String sql = "select * from court where userId=? and startDate=? and endDate=? and courtName=?";
        Object[] params = {court.getUserId(), court.getStartDate(), court.getEndDate(), court.getCourtName()};
        try {
            return qr.query(sql, params, new BeanHandler<>(Court.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
