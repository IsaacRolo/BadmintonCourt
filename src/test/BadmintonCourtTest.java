package test;

import main.BadmintonCourt;
import main.BadmintonCourtImpl;
import main.Entrance;
import org.junit.Test;


public class BadmintonCourtTest {
    private BadmintonCourt badmintonCourt = new BadmintonCourtImpl();

    @Test
    public void printIncomeTest() throws Exception {
        badmintonCourt.printCourtsList();
    }

    @Test
    public void saveBookTest() throws Exception {
        badmintonCourt.saveBook("U002 2017-08-10 19:00~20:00 A");
    }


    @Test
    public void cancelBookTest() throws Exception {
        badmintonCourt.cancelBook("U002 2017-08-10 19:00~20:00 A C");
    }


    /**
     * 模拟测试用例1的输入
     */
    @Test
    public void testExampleOne() throws Exception {
        Entrance.handler(badmintonCourt, "abcdefghijklmnopqrst1234567890");
        Entrance.handler(badmintonCourt, "U001 2016-06-02 22:00~22:00 A");
        Entrance.handler(badmintonCourt, "U002 2017-08-01 19:00~22:00 A");
        Entrance.handler(badmintonCourt, "U003 2017-08-02 13:00~17:00 B");
        Entrance.handler(badmintonCourt, "U004 2017-08-03 15:00~16:00 C");
        Entrance.handler(badmintonCourt, "U005 2017-08-05 09:00~11:00 D");
        Entrance.handler(badmintonCourt, "");
    }


    /**
     * 模拟测试用例2的输入
     */
    @Test
    public void testExampleTwo() throws Exception {
        Entrance.handler(badmintonCourt, "U002 2017-08-01 19:00~22:00 A");
        Entrance.handler(badmintonCourt, "U003 2017-08-01 18:00~20:00 A");
        Entrance.handler(badmintonCourt, "U002 2017-08-01 19:00~22:00 A C");
        Entrance.handler(badmintonCourt, "U002 2017-08-01 19:00~22:00 A C");
        Entrance.handler(badmintonCourt, "U003 2017-08-01 18:00~20:00 A");
        Entrance.handler(badmintonCourt, "U003 2017-08-02 13:00~17:00 B");
        Entrance.handler(badmintonCourt, "");
    }
}

