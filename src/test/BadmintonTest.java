package test;

import main.BadmintonCourt;
import org.junit.Test;

/**
 * Created by Elrol on 2017/9/8.
 */
public class BadmintonTest {
    private BadmintonCourt badmintonCourt=new BadmintonCourt();
    @Test
    public void printIncomeTest() {
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

}

