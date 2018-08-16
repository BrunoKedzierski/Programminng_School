package pl.coderslab.UserApp;

import org.mindrot.BCrypt;
import pl.coderslab.Administration.ExerciseManagment;
import pl.coderslab.Model.Exercise;
import pl.coderslab.Model.User;
import service.DbService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserApp {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        userInterface();
    }

    private static boolean checkPass(String password, User user) {
        boolean check = false;
        if (BCrypt.checkpw(password, user.getPassword())) {
            System.out.println("Loging complete");
            check = true;
        } else {
            System.out.println("Wrong password");
        }

        return check;
    }

    private static void userInterface() {
        System.out.println("Insert user email");
        String email = scan.nextLine();
        while (User.getUserByMail(email).getEmail() == "") {
            System.out.println("This email does not exist. Try again");
            email = scan.nextLine();
        }
        System.out.println("Insert password");
        String password = scan.nextLine();
        User user = User.getUserByMail(email);
        if (checkPass(password, user)) {
            showAllUserSol(user);



        }


    }

    private static void showAllUserSol(User user) {
        int id = user.getId();
        System.out.println("id | created | updated | description | exercise id");
        System.out.println("");
        String query = "SELECT * FROM solution WHERE users_id =" + id;
        try {
            List<String[]> rows = DbService.getData(query, null);
            if(rows.isEmpty()){
                System.out.println("nie masz żadnych rozwiązań!");
            }
            for (String[] row : rows) {
                int idd = Integer.parseInt(row[0]);
                String created = row[1];
                String updated = row[2];
                String description = row[3];
                int exercise = Integer.parseInt(row[4]);
                System.out.println(idd + "] " + created + " | " + updated + " | " + description + " | " + exercise + " |");


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private static void showAllEmptyExer() {



        System.out.println("| id  | title | description|");
        String query = "SELECT * FROM exercise";
        try {
            List<String[]> rows = DbService.getData(query, null);
            for (String[] row : rows) {
                int id = Integer.parseInt(row[0]);
                String title = row[1];
                String description = row[2];
                System.out.println("| " + id + " | " + title + " | " + description + " |");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}





    

