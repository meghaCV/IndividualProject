package Com.FirstAssignment;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**This Task class represents a Task object and contains all the fields and methods to operate on task object*/


public class Task implements Serializable {
    private String TaskTitle;
    private String projectName;
    private boolean complete;
    private LocalDate dueDate;

    /** Creating an objects of Task class */
    public Task(String title, String project, LocalDate dueDate) {
        this.setTaskTitle(title);
        this.setProjectName(project);
        this.complete = false;
        this.setDueDate(dueDate);
    }
    /** A method to get the task title */
    public String getTaskTitle() {
        return this.TaskTitle;
    }
    /** A method to get the project name */
    public String getprojectName() {
        return this.projectName;
    }
    /** A method to set the title of a Task object*/
    public void setTaskTitle(String TaskTitle) throws NullPointerException {
        if (TaskTitle.equals("")) {
            throw new NullPointerException("projectTitle cannot be empty");
        }
        this.TaskTitle =TaskTitle;
    }


    /**A method to set the project name*/

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    /** A method to get the completion status of task */
    public boolean isCompleted() {
        return this.complete;
    }

    public boolean markComplete() {
        this.complete = true;
        return this.complete;
    }


    public LocalDate getDueDate() {
        return this.dueDate;
    }
    /** A method to set the due date of a task */
    public void setDueDate(LocalDate dueDate) throws DateTimeException {
        if (dueDate.compareTo(LocalDate.now())<0) {
            throw new DateTimeException("Entered date should always be greater than today's date");
        }

        DateTimeFormatter dateFormatted = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dueDate = LocalDate.parse(dueDate.format(dateFormatted));
    }
    /** A method to get the data of task as formatted string to display in multiple lines  */
    public String taskDisplayFormatted() {
        return (
                "\nTitle     : " + TaskTitle +
                        "\nProject   : " + projectName +
                        "\nStatus    : " + (complete? "Completed":"not completed") +
                        "\nDue Date  : " + dueDate +
                        "\n");
    }
}