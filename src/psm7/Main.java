package psm7;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Thermometer thermometer = new Thermometer();
		for (PartOfThePlate[] points : thermometer.plates) {
			System.out.println(Arrays.toString(points));

		}
		Matrices m = thermometer.createMatrix();
		for (int i = 0; i < m.mainMatrix.length; i++) {
			for (int k = 0; k < m.mainMatrix[i].length; k++) {
				System.out.print(m.mainMatrix[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println(Arrays.toString(m.answerMatrix));
		int razmetka = thermometer.plates[0].length - 2;
		int temp = 0;
		double[] solvedMatrix = Solver.lsolve(m.mainMatrix, m.answerMatrix);

		for (int i = 0; i < solvedMatrix.length; i++) {
			if (temp == razmetka) {
				System.out.println();
				temp = 0;
			}
			System.out.print(solvedMatrix[i] + " |");

			temp++;
		}
		// System.out.println(Arrays.toString(solvedMatrix));
	}

}
