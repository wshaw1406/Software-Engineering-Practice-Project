/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_eng;

import java.util.ArrayList;

/**
 *
 * @author Will
 */
public class Main {
    
    public static ArrayList<Task> tasks = new ArrayList<>();

    
    public static void main(String args[]) {
//        TaskLogging test = new TaskLogging();
//        test.onClick();
//        
        Database dbTest = new Database();
//        dbTest.pullUsers();

//          tasks.addTask(task);
//          
          tasks = (ArrayList<Task>) dbTest.pullTasks();
          Task.displayTasks(tasks);
          
          System.out.println("Main size: " + tasks.size());
          new CaretakerSchedule2();
    }
}
