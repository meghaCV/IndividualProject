package Com.FirstAssignment;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Scanner;

/** A class represents Todo list objects stored in Arraylist */
public class TodoList {

    private ArrayList<Task> listOfTasks;


    public TodoList() {
        listOfTasks = new ArrayList<>();
    }


    public void addTask(String title, String project, LocalDate dueDate) {
        this.listOfTasks.add(new Task(title,project,dueDate));
    }

    /**A method to read the value from user to create a Task object */
    public boolean inputTaskFromUser() {
        Scanner scan = new Scanner(System.in);

        try {
            System.out.println("Please enter the below details to add a task:");
            System.out.print(" Task Title --> ");
            String title = scan.nextLine();
            System.out.print(" Project Name--> ");
            String project = scan.nextLine();
            System.out.print(" Due Date (sample format --> 2020-12-28) : ");
            LocalDate dueDate = LocalDate.parse(scan.nextLine());

            this.listOfTasks.add(new Task(title, project, dueDate));
            Dmessages.displayMessage("Task is successfully added in the Todolist", false);
            return true;
        } catch (Exception e) {
            Dmessages.displayMessage(e.getMessage(), true);
            return false;
        }
    }

    /** A method to read the value from user to create a Task object */
    public boolean enterTaskFromUserToUpdate(Task task) {
        Scanner scan = new Scanner(System.in);
        boolean isTaskUpdated = false;

        try {
            System.out.println("Please enter the below details to update a task:"
                    + "\n If you do not wish to continue without updating, just press ENTER key!");
            System.out.print(" Task Title --> ");
            String title = scan.nextLine();
            if (!(title.equals(""))) {
                task.setTaskTitle(title);
                isTaskUpdated = true;
            }

            System.out.print(" Project Name--> ");
            String project = scan.nextLine();
            if (!(project.equals(""))) {
                task.setProjectName(project);
                isTaskUpdated = true;
            }

            System.out.print("Due Date (sample format --> 2020-12-28): ");
            String dueDate = scan.nextLine();
            if (!(dueDate.equals(""))) {
                task.setDueDate(LocalDate.parse(dueDate));
                isTaskUpdated = true;
            }

            Dmessages.displayMessage("Task is " + (isTaskUpdated ? " successfully updated" : "not updated") +
                    ": Back to Main Menu", false);
            return true;

        } catch (Exception e) {
            Dmessages.displayMessage(e.getMessage(), true);
            return false;
        }
    }

    /** A method to read the value from user to create a Task object */
    public void displayAllTasksWithIndex() {
        String displayFormat = "%-4s%-35s %-20s %-10s %-10s";

        if (listOfTasks.size() > 0) {
            System.out.println(String.format(displayFormat, "NUM", "TITLE", "PROJECT", "DUE DATE", "COMPLETED"));
            System.out.println(String.format(displayFormat, "===", "=====", "=======", "========", "========="));
        } else {
            System.out.println("No tasks to show");
        }

        listOfTasks.stream()
                .forEach(task -> System.out.println(String.format(displayFormat,
                        listOfTasks.indexOf(task) + 1,
                        task.getTaskTitle(),
                        task.getprojectName(),
                        task.getDueDate(),
                        (task.isCompleted() ? "YES!!!!" : "NO:(:(")
                )));
    }

    /** A method to display the contents of ArrayList */
    public void displayAllTasks(String sortBy) {
        Dmessages.separator('=', 80);
        System.out.println(
                "Total Tasks = " + listOfTasks.size() +
                        "\t\t (Completed = " + completedCount() + "\t\t" +
                        " Not Completed = " + notCompletedCount() +
                        " )");
        Dmessages.separator('=', 80);

        if (sortBy.equals("2")) {
            String displayFormat = "%-20s %-35s %-10s %-10s";

            if (listOfTasks.size() > 0) {
                System.out.println(String.format(displayFormat, "PROJECT", "TITLE", "DUE DATE", "COMPLETED"));
                System.out.println(String.format(displayFormat, "********", "********", "********", "***********"));
            } else {
                System.out.println("No tasks to show");
            }

            listOfTasks.stream()
                    .sorted(Comparator.comparing(Task::getprojectName))
                    .forEach(task -> System.out.println(String.format(displayFormat, task.getprojectName(),
                            task.getTaskTitle(),
                            task.getDueDate(),
                            (task.isCompleted() ? "YES!!!" : "NO:(:(")
                    )));
        } else {
            String displayFormat = "%-10s %-35s %-20s %-10s";

            if (listOfTasks.size() > 0) {
                System.out.println(String.format(displayFormat, "DUE DATE", "TITLE", "PROJECT", "COMPLETED"));
                System.out.println(String.format(displayFormat, "********", "********", "********", "***********"));
            } else {
                System.out.println("No tasks to show");
            }

            listOfTasks.stream()
                    .sorted(Comparator.comparing(Task::getDueDate))
                    .forEach(task -> System.out.println(String.format(displayFormat, task.getDueDate(),
                            task.getTaskTitle(),
                            task.getprojectName(),
                            (task.isCompleted() ? "YES" : "NO")
                    )));
        }
    }

    /** A method to select a particular Task object from ArrayList and perform listed editing operations */
    public void editTask(String selectedTask) throws NullPointerException {
        try {
            // checking if the task number is given and empty string or null
            if (selectedTask.trim().equals("") || selectedTask == null) {
                throw new NullPointerException("Empty or Null Task number:Back to Main Menu");
            }

            int taskIndex = Integer.parseInt(selectedTask) - 1;
            if (taskIndex < 0 || taskIndex > listOfTasks.size()) {
                throw new ArrayIndexOutOfBoundsException("Invalid task number: Back to Main Menu");
            }

            Task task = listOfTasks.get(taskIndex);

            Dmessages.displayMessage("Task Number " + selectedTask + "  is selected:" + task.taskDisplayFormatted(), false);

            Dmessages.editTask();
            Scanner scan = new Scanner(System.in);
            String editOption = scan.nextLine();
            switch (editOption) {
                case "1":
                    enterTaskFromUserToUpdate(task);
                    break;
                case "2":
                    task.markComplete();
                    Dmessages.displayMessage("Task Number " + selectedTask + " is marked as Completed: Back to Main Menu", false);
                    break;
                case "3":
                    listOfTasks.remove(task);
                    Dmessages.displayMessage("Task Number " + selectedTask + " is Removed: Back to Main Menu", true);
                    break;
                default:
                    Dmessages.displayMessage("Back to Main Menu", true);
            }
        } catch (Exception e) {
            Dmessages.displayMessage(e.getMessage(), true);
        }
    }

    /** A method to count the number of tasks with completed status */
    public int completedCount() {
        return (int) listOfTasks.stream()
                .filter(Task::isCompleted)
                .count();
    }

    /** A method to count the number of tasks with incompleted status */
    public int notCompletedCount() {
        return (int) listOfTasks.stream()
                .filter(task -> !task.isCompleted())
                .count();
    }

    /** This method will read the data file containing the data of previously saved tasks */
    public boolean readFromFile(String filename) {
        boolean status = false;

        try {
            if (!Files.isReadable(Paths.get(filename))) {
                Dmessages.displayMessage("The data file, " + filename + " does not exists", true);
                return false;
            }

            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            listOfTasks = (ArrayList<Task>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
            return true;

        } catch (Exception e) {
            Dmessages.displayMessage(e.getMessage(), true);
            return false;
        }
    }

    /**This method will write the data of Tasks from ArrayList to data file */
    public boolean saveToFile(String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(listOfTasks);

            objectOutputStream.close();
            fileOutputStream.close();
            return true;

        } catch (Exception e) {
            Dmessages.displayMessage(e.getMessage(), true);
            return false;
        }
    }
}


