package sinuses;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Sin {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println(
				"This program tries to approximate the sin value by a McLaren serises \n Please enter the value ");
		double value = scan.nextDouble();
		double actual = value;
		double another = value;
		System.out.println("Please write how much values must the polynominal have");
		int how_much = scan.nextInt();
		boolean toRad = false;
		System.out.println("Please  write how the values should be treated (DEG for Degrees and RAD for radiance)");
		String responce = scan.next();
		while (!(responce.equals("DEG") || responce.equals("RAD"))) {
			System.out.println("Please provide correct input");
			responce = scan.nextLine();
		}
		if (responce.equals("DEG")) {
			toRad = false;
			if (value > 0) {
				while (value >= 360) {
					value -= 360;
				}
			} else {
				while (value <= -360) {
					value += 360;
				}
			}
			value = value * Math.PI / 180;
		} else if (responce.equals("RAD")) {
			toRad = true;
			if (value > 0) {
				while (value >= 2 * Math.PI) {
					value -= 2 * Math.PI;
				}
			} else {
				while (value <= -2 * Math.PI) {
					value += 2 * Math.PI;
				}
			}
		}

		double temp2 = 0;

		for (int i = 0; i <= how_much + 1; i++) {
			temp2 += ((Math.pow(-1, i) * Math.pow(value, 2 * i + 1)) / Factorial((2 * i + 1)));
		}
		System.out.println(new BigDecimal(temp2) + " - calculated by my method ");

		if (toRad) {
			System.out.println((Math.sin(actual)) + " actual  in radiance  ");
			System.out.println((Math.sin(value)) + " actual  in radiance my value ");
		} else
			System.out.println(Math.sin(Math.toRadians(actual)) + " actual in degrees");

	}

	public static long Factorial(int n) {
		long fact = 1;
		for (int i = 2; i <= n; i++) {
			fact = fact * i;
		}
		return fact;
	}

	public static double getPow(double a, double b) {
		return (Math.pow(a, (((b - 1) * 2) - 1)));
	}
}
