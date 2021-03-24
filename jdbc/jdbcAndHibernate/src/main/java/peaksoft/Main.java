package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserService userService=new UserServiceImpl();

        //положи в swith case
        while (true ) {
            switch (new Scanner(System.in ).nextLine() ) {
                case "1" -> userService.createUsersTable();
                case "2" -> userService.saveUser("кандыбек", "исаев", (byte) 17);
                case "3" -> System.out.println(userService.getAllUsers());
                case "4" -> userService.removeUserById(2L);
                case "5" -> userService.dropUsersTable();
                case "6" -> userService.cleanUsersTable();
            }
        }
    }
}
