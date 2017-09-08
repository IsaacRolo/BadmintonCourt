package dao;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Court;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class CourtDaoImpl {
    private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());


    public List<Court> getAllCourt(String courtName) {
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


}
