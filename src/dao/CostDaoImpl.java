package dao;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Cost;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class CostDaoImpl {
    private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());


    public List<Cost> getCostByWeek(int isWeekDay) {
        List<Cost> costs = null;
        String sql = "SELECT * FROM cost where isWeekDay=?";
        Object[] params = {isWeekDay};
        try {
            costs = qr.query(sql, params, new BeanListHandler<>(Cost.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return costs;

    }


}
