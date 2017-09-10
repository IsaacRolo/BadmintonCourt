package main;

import java.util.Scanner;

import static utils.TimeFormat.toTime;

/**
 * Created by Elrol on 2017/9/10.
 */
public class Main {
    public static void main(String[] args) {
        BadmintonCourt badmintonCourt = new BadmintonCourt();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();

            if (str.matches("^U[\\d]{3} [\\d]{4}-[\\d]{2}-[\\d]{2} (?:(?:[01]9)|(?:1[3-8])|(?:[12][012])):00~(?:(?:[01]9)|(?:1[3-8])|(?:[12][012])):00 [A-D](?: C)?$")) {
                String[] split = str.split(" ");
                String[] time = split[2].split("~");
                if (toTime(time[0]).compareTo(toTime(time[1])) >= 0) {
                    System.out.println("Error: the booking is invalid!");
                } else if (split.length == 5) {
                    badmintonCourt.cancelBook(str);
                } else if (split.length == 4) {
                    badmintonCourt.saveBook(str);
                }

            } else if (str.equals("")) {
                badmintonCourt.printCourtsList();
            } else {
                System.out.println("Error: the booking is invalid!");
            }

        }

    }
}
