package stack;

public class StackTest {
	
	static int[] stack = new int[10];
	static int cur = 0;
	
	static void push(int a) {
		if (cur < stack.length) {
			System.out.println("PUSH: 입력한 데이터 " + a + "를 "
					+ cur + " 인덱스에 저장");
			stack[cur++] = a;
		} else {
			System.out.println("PUSH: 스택이 가득 찼습니다.");
		}
	}
	
	static Integer pop() {
		Integer data = null;
		if (cur > 0) {
			data = stack[--cur];
			System.out.println("POP: " + cur + " 인덱스의 데이터를 꺼냄");
		} else {
			System.out.println("POP: 스택이 비어있습니다.");
		}
		return data;
	}
	
	public static void main(String[] args) {
		
		// 데이터 삽입 push
		push(1);
		
		// 데이터 삽입
		push(10);
		
		// 데이터 삽입
		push(5);
		push(5);
		push(5);
		push(5);
		push(5);
		push(5);
		push(5);
		push(5);
		// 11번째
		push(5);
		
		Integer data = null;
		// 데이터 삭제(사용) pop
		data = pop();
		System.out.println("꺼낸 데이터: " + data);
		
		// 데이터 삭제(사용)
		data = pop();
		System.out.println("꺼낸 데이터: " + data);
		
		// 데이터 삭제(사용)
		data = pop();
		System.out.println("꺼낸 데이터: " + data);
		
		// 데이터 삭제(사용)
		data = pop();
		System.out.println("꺼낸 데이터: " + data);
	}
	
}
