package utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class JdbcUtils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	public static DataSource getDataSource(){
		return dataSource;
	}
}
