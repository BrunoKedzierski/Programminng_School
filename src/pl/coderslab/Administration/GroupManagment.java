package pl.coderslab.Administration;

import pl.coderslab.Model.Exercise;
import pl.coderslab.Model.Group;
import service.DbService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GroupManagment {
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        showAllGroup();
        manageGroup();
    }





    private static void showAllGroup() {
        System.out.println("| id| description|");
        String query = "SELECT * FROM user_group";
        try {
            List<String[]> rows = DbService.getData(query, null);
            for(String [] row : rows){
                int id = Integer.parseInt(row[0]);
                String description = row[1];
                System.out.println("| " + id + " | "  + description + " |");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private static void manageGroup() {
        String answer = "";
        do {

            System.out.println("Choose one of the option: Add, Edit, Delete, Close");
            answer = scan.nextLine();
            if (answer.equalsIgnoreCase("Add")) {
                System.out.println("Input name");
                String name = scan.nextLine();
                Group group = new Group(name);
                group.saveToDb();
                showAllGroup();

            }
            if (answer.equalsIgnoreCase("Edit")) {
                System.out.println("Input id of a group you want to edit");
                while (!scan.hasNextInt()) {
                    System.out.println("Type a valid id");
                    scan.next();
                }
                int id = scan.nextInt();
                while (Group.getGroupById(id).getId() == -1){
                    System.out.println("This id does not exist. Try again!");
                    try{id = scan.nextInt();}
                    catch (InputMismatchException e ){
                        System.out.println("Type a valid id");
                        return;
                    }

                }
                scan.nextLine();
                System.out.println("Input name");
                String name = scan.nextLine();
                Group group = Group.getGroupById(id);
                group.setName(name);
                group.saveToDb();
                showAllGroup();


            }
            if (answer.equalsIgnoreCase("delete")) {
                System.out.println("Input id of an group you want to delete");
                while (!scan.hasNextInt()) {
                    System.out.println("Type a valid id");

                }

                int id = scan.nextInt();
                while (Group.getGroupById(id).getId() == -1){
                    System.out.println("This id does not exist. Try again");
                    try{id = scan.nextInt();}
                    catch (InputMismatchException e){
                        System.out.println("Type a valid id");
                        return;
                    }

                }

                Group group = Group.getGroupById(id);
                group.delete();
                showAllGroup();


            }

        } while (!answer.equalsIgnoreCase("close"));

    }
}
