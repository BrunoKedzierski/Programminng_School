package pl.coderslab;


import pl.coderslab.Administration.ExerciseManagment;
import pl.coderslab.Administration.UserManagment;
import pl.coderslab.Model.Exercise;
import pl.coderslab.Model.Group;
import pl.coderslab.Model.Solution;
import pl.coderslab.Model.User;
import service.DbService;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        ExerciseManagment.main(null);
        UserManagment.main(null);

    }

}