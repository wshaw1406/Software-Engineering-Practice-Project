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
    
    public static void main(String args[]) {
//        TaskLogging test = new TaskLogging();
//        test.onClick();
//        
        Database dbTest = new Database();
//        dbTest.pullUsers();
        
          Task task = new Task();
//          task.setTaskID("321");
//          
          ArrayList<Task> tasks = new ArrayList<>();
//          tasks.addTask(task);
//          
          tasks = dbTest.pullTasks();
          task.displayTasks(tasks);
    }
}
