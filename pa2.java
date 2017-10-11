package pa1;

import java.util.Scanner;

public class pa2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			int N = Integer.parseInt(scanner.nextLine());
			
			String students[] = new String[N];
			
			for(int i = 0; i < N; i++) {
				students[i] = scanner.nextLine();
			}
			
			for(int i = 0; i < N; i++) {
				String name = students[i].split(" ")[0];
				int grade = Integer.parseInt(students[i].split(" ")[1]);
				System.out.println(String.format("%-10s %03d", name, grade));
			}
		}
	}

}
