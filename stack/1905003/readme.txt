There are total four files - 

1. StackArr.java [Array implementation for task-1]: class Arr<E> implemets Stack<E> interface. It has three constructors in order to initialize an empty stack with a default size of 10, intializing an empty stack with provided "size" as its maximum length and intitializing an empty stack with the help of a pre-allocated array "b[]" which is passed as an argument to the constructor.

2. StackLL.java [Linked list implementation for task-1]: class LL<E> implemets Stack<E> interface, and class Link<E> represents the nodes of the linked list implementation. The constructor initializes an empty stack with "size = 0". Unlike the Arr<E> class, "setDirection()" function of Stack<E> interface is kept empty in this class since this concept is applicable only in case of array implementation.

3. Main.java [Common sample codes for checking purposes for task-1]: Contains the Stack<E> interface and a main function which is identical for both implementations except for the object instantiation part. Printing algorithm of the stack is also slightly different in array and linked list based implementations.

4. dw.java [main function for task - 2]: 
	class Dish simulates the dishes which takes the size of a dish as its argument in its constructor. 
	Two stacks - "dirtyStack" and "cleanStack" are used for storing the dirty and clean dishes respectively. 
	"temp" is another object of LL/Arr class that is used to facilitate printing the stack, which is used in Main.java class as well. The washing end times of the dishes have been stored in "washingEndTime" stack in chronological order. 
	There are two integer arrays - "f" and "a" which store these two information respectively - "friend(s) that have pushed a dish (in chronological order)" and "time required to wash the dish designated for each course (in ascending order)". 
	"ID" is a LL object that holds the id's of those friends that had had a full meal.
	"sumArr(int[] Arr)" is a helper function to calculate the sum of an array.