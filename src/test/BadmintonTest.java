package test;

import dao.CourtDaoImpl;
import org.junit.Test;
import pojo.Court;

import java.util.List;

/**
 * Created by Elrol on 2017/9/8.
 */
public class BadmintonTest {
    @Test
    public void printIncomeTest(){
        CourtDaoImpl courtDao=new CourtDaoImpl();
        List<Court> courts=courtDao.getAllCourt("A");
        float count=0f;
        for (Court court : courts) {
            count+=court.getCost()+court.getPenalty();
            System.out.println(court.toString());
        }
        System.out.println(count);

    }

}

