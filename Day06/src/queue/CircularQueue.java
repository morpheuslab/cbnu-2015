package queue;

public class CircularQueue {
	
	static int[] queue = new int[10];
	static int front = 0, rear = 0;
	
	static void enQueue(int data) {
		// 큐가 가득차지 않았다면 삽입
		if (front != (rear + 1) % queue.length) {
			queue[rear] = data;
			rear = (rear + 1) % queue.length;
			System.out.println("데이터삽입 [INDEX: " + rear
					+ ", DATA: " + data + "]");
		}
		// 가득찼다면 메시지 출력
		else {
			System.err.println("큐가 가득 찼습니다.");
		}
	}
	
	static Integer deQueue() {
		Integer data = null;
		// 큐가 비어있지 않다면 꺼내고
		if (front != rear) {
			data = queue[front];
			
			System.out.println("데이터삭제: [INDEX: " + front + ", "
					+ "DATA: " + data);
			
			front = (front + 1) % queue.length;
		}
		// 큐가 비었다면 메시지 출력
		else {
			System.err.println("큐가 비어있습니다.");
		}
		
		return data;
	}
	
}











