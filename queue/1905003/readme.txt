There are four files -

1. QueueArrr.java [task-1]: A single class "AQueue<E>  [implements Queue<E> interface]" represents circular array implementation of queue. Default array size is taken to be 10, which means total 9 elements may be stored initially. front and rear are integers serving as indices of the front and rear element respectively. listArray is the array where the elements are stored.
	Total 3 constructors are available. The first two initializes listArray with either defaultSize or given size as argument. The third one takes an already allocated array and uses it as an empty queue.
	The interface methods are sequentially implemented.

2. QueueLL.java [task-1]: There are two classes in this file - Link<E> [represents nodes of a linked queue] and "LQueue<E> [implements Queue<E> interface]". "front" is a Link<E> pointer pointing to the empty header node, whereas "rear" is a Link<E> pointer pointing to the true last node of the linked queue. 
	A single constructor intializes an empty linked queue.
	The interface methods are sequentially implemented.

3. Main.java [task-1]: Contains the Queue<E> interface and common main() method for checking the array and linked list implementations. Except for the object instantiation part, this method is identical for both implementations.

4. bank.java [task-2]: Customer class represents the customers, each of whom is assigned an id, have an "entryTime" and a "serviceTime" that are known only to that customer and the tellers in the booths.
	bank class contains main function for task-2 of the given assignment. The variables/objects used in this class are -
	n         			-> total number of customers taken as input
	t[], s[] 			-> t: time when the customer enters the bank, s: service time of that customer
	teller1, teller2 		-> calculates and stores the time at which current customer taking service will leave
	q1, q2 			-> AQueue/LQueue objects representing the two queues of customers in front of the two booths
	Qcst[] 			-> array to store all required customer objects
	remCst 			-> remaining customers to be queued in either queue
	currTime 			-> maintains a record of the current point of time (starting from 0)
	isEmptyB1, isEMptyB2 	-> is the service area of corresponding booth is empty
	cst 			-> customer under consideration

After taking the inputs, all customer objects are inserted into the Qcst array.
Then, within a do-while loop that keeps incrementing currTime unless both the queues are empty and remCst != n, at every currTime it is checked whether a new customer is to be added to either queue or not. If a new customer arrives, s/he is added to the shortest queue (if both are equal, s/he is inserted in the first queue) and remCst is decremented by 1. Adding a new customer only takes place until all the customers have been inserted to either of the queues, i.e. when remCst > 0.
After that, it is checked whether currTime equals teller1 or teller2, and the corresponding customer is dequed. If length of the queue becomes zero, then queue switching takes place if appropriate, and then value of teller1 or teller2 is updated. If length of the queue still remains nonzero, then first value of teller1 or teller2 is updated since the next customer in the same queue will enter the service area and then queue switching takes place if a customer standing at the rear of the other queue sees that s/he has n people in front of him and this queue has a total of (n-1) or fewer people.
Finally, values of teller1 and teller2 are printed.
