/**
 * Represents a Todo task without any date/time information.
 */
public class Todo extends Task{
    public Todo (String description){
        super(description);
    }
    public String toString(){
        return "[T]" + super.toString();
    }
}