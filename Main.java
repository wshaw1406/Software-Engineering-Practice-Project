/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_eng;

/**
 *
 * @author Will
 */
public class Main {
    
    public static void main(String args[]) {
//        TaskLogging test = new TaskLogging();
//        test.onClick();
        
        Database dbTest = new Database();
        dbTest.connect();
        dbTest.query();
    }
}
