package main;

import java.util.Scanner;

import static utils.TimeFormat.toHour;

public class Entrance {
    public static void main(String[] args) {
        BadmintonCourt badmintonCourt = new BadmintonCourtImpl();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            handler(badmintonCourt, str);

        }

    }

    public static void handler(BadmintonCourt badmintonCourtImpl, String str) {
        if (str.matches("^U[\\d]{3} [\\d]{4}-[\\d]{2}-[\\d]{2} (([01]9)|(1[3-8])|([12][012])):00~(([01]9)|(1[3-8])|([12][012])):00 [A-D](?: C)?$")) {
            String[] split = str.split(" ");
            String[] time = split[2].split("~");
            if (toHour(time[0]).compareTo(toHour(time[1])) >= 0) {
                System.out.println(" Error: the booking is invalid!");
            } else if (split.length == 5) {
                badmintonCourtImpl.cancelBook(str);
            } else if (split.length == 4) {
                badmintonCourtImpl.saveBook(str);
            }

        } else if (str.equals("")) {
            badmintonCourtImpl.printCourtsList();
        } else {
            System.out.println("Error: the booking is invalid!");
        }
    }
}
