package telran.util;

public interface StackMax {
boolean isEmpty();//true if a stack is empty
void push(int num); //pushes a number at the stack top
int pop(); //returns and deletes a number from the stack top (throws exception
// NoSuchElementException)
int getMax(); //returns the current maximal value in the stack 
//(throws exception NoSuchElementException)
}
