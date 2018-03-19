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
public class TaskList extends Task{
    ArrayList<Task> tasks = new ArrayList<>();
    
    TaskList()
    {
        
    }
    
    public void addTask(Task task)
    {
        tasks.add(task);
    }
    
    public void removeTask(Task task)
    {
        tasks.remove(task);
    }
}
