package Com.FirstAssignment;

public class Dmessages{

    /**Welcome screen of my todo list application*/
    public static void displayMenu(int incompleteTaskCount, int completedTaskCount) {
        System.out.println("\n WELCOME TO TODO LIST");
        System.out.println("*****************************\n");
        System.out.println("You have " + incompleteTaskCount+ " tasks pending "
                + " and " + completedTaskCount + " tasks done \n");
        System.out.println("Choose from the below options:");
        System.out.println("(1) Show Task List (by date or project)");
        System.out.println("(2) Add New Task");
        System.out.println("(3) Edit Task (update, mark as done, remove)");
        System.out.println("(4) Save and Quit\n");
        System.out.println("Select any one of the option from [1-4]: ");
    }

    /**This method will display all the options when the user selects option 1 from mainmenu*/
    public static void showTaskList() {
        System.out.println("\nSHOW ALL TASKS LIST");
        System.out.println("*************************\n");
        System.out.println("Choose from the below options:");
        System.out.println("(1) Show Task List by date" +
                " [default choice, just press ENTER key]" );
        System.out.println("(2) Show Task List by project");
        System.out.print("\nSelect any one of the option[1-2]: ");
    }

    /**This method will ask user to enter the tasknumber to edit*/
    public static void editSelectedTask() {

        System.out.print("Type a task number to EDIT and press ENTER key: ");

    }

    /**
     * This method will display all the edit options for a user*/

    public static void editTask() {
        System.out.println("\nEDIT THE TASK");
        System.out.println("*********************\n");
        System.out.println("Choose from the below options:");
        System.out.println("(1) Edit a selected task");
        System.out.println("(2) Mark selected task as COMPLETED");
        System.out.println("(3) Delete/Remove a selected task");
        System.out.println("(4) Go back to main menu "
                + " [default choice, just press ENTER]");
        System.out.print("\nSelect any one of the option from[1-4]: ");
    }
    public static void separator (char formatToPrint, int t) {
        for (int i=0; i<t; i++)
            System.out.print(formatToPrint);
        System.out.println("");

    }
    /**
     * This method will display the end message while user exiting the program
     */
    public static void endMessage() {

        System.out.println("Thank you for using TODO list");
        System.out.println("All tasks have been saved to data file");

    }
    public static void displayMessage(String message, boolean warning) {
        System.out.println(warning);
        System.out.println(" " + message);

    }

    /**
     * This method will display the error message if a user input an invalid option
     */
    public static void invalidMessage() {
        System.out.println("Incorrect option: Please type a number from given options ");
    }







}



