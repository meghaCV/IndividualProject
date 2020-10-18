package Com.FirstAssignment;

import java.util.Scanner;

public class Main {

    // A string to hold the data file name where all the data is saved.
    public static String filename = "/Users/megcv/Desktop/Todo.txt";


    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        String displayOptions = "20";

        try {
            Scanner input = new Scanner(System.in);
            // reading the saved data from previous operations
            // if it is first time a message will be displayed saying "file does not exist"
            todoList.readFromFile(filename);
            Dmessages.displayMessage("Welcome to ToDoList", false);

            while (!displayOptions.equals("4")) {
                Dmessages.displayMenu(todoList.notCompletedCount(), todoList.completedCount());
                displayOptions = input.nextLine();

                switch (displayOptions) {
                    case "1":
                        Dmessages.showTaskList();
                        todoList.displayAllTasks(input.nextLine());
                        break;
                    case "2":
                        todoList.inputTaskFromUser();
                        break;
                    case "3":
                        todoList.displayAllTasksWithIndex();
                        Dmessages.editSelectedTask();
                        todoList.editTask(input.nextLine());
                        break;
                    case "4":
                        break;

                    default:
                        Dmessages.invalidMessage();
                }
            }


            todoList.saveToFile(filename);
            Dmessages.endMessage();

        } catch (Exception e) {
            Dmessages.displayMessage("UNCAUGHT EXCEPTION THROWN", true);
            System.out.println("Attempt to write the unsaved data of all tasks in a data file");
            todoList.saveToFile(filename);
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}

