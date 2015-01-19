package queue;

public class Queue {
	
	static int[] queue = new int[10];
	static int front = 0;
	static int rear = 0;
	
	static void enQueue(int data) {
		if (rear < queue.length) {
			queue[rear] = data;
			rear++;
		} else {
			System.err.println("큐가 가득 참!!");
		}
	}
	
	static Integer deQueue() {
		Integer data = null;
		if (front != rear) {	// 큐가 비어있지 않다면
			data = queue[front];
			front++;
		} else {
			System.err.println("큐가 비어있음!!");
//			System.err.println("front: " + front + ", rear: " + rear);
		}
		return data;
	}
	
	public static void main(String[] args) {
		
		enQueue(1);
		enQueue(3);
		enQueue(5);
		enQueue(5);
		enQueue(5);
		
		enQueue(1);
		enQueue(3);
		enQueue(5);
		enQueue(5);
		enQueue(5);
		
		enQueue(5);
		
		
		
		Integer data = null;
		data = deQueue();	System.out.println(data);
		data = deQueue();	System.out.println(data);
		data = deQueue();	System.out.println(data);
		data = deQueue();	System.out.println(data);
		
	}
	
}


























