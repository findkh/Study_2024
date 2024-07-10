package algorithm;

import java.util.Scanner;

public class Euclid {
    public static void main(String[] args) {
	Scanner scn = new Scanner(System.in);
	int a;
	int b;
	int originA;
	int originB;

	a = scn.nextInt();
	originA = a;
	b = scn.nextInt();
	originB = b;
	scn.close();

	while (a != b) {
	    if (a > b) {
		a -= b;
	    } else {
		b -= a;
	    }
	}

	System.out.println(originA*originB);
	System.out.println();


	System.out.printf("두수의 최대 공략수는 %d\n", a);
	System.out.printf("두수의 최소 공배수는 %d\n", (originA*originB)/a);
    }
}
