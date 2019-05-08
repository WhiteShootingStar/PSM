package psm7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntFunction;

public class Thermometer {

	public PartOfThePlate[][] plates;

	public Thermometer() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter width of the plate");
		double width = scan.nextDouble();
		System.out.println("Please enter heigth of the plate");
		double heigth = scan.nextDouble();
		System.out.println(
				"Please enter 4 temperatures, they would be assigned in clockwise direction to the sides of the plate");
		String line = scan.next();
		String[] temp = line.split(",");
		int[] temperatures = new int[temp.length];
		for (int i = 0; i < temp.length; i++) {
			temperatures[i] = Integer.parseInt(temp[i]);
		}
		scan.nextLine();
		System.out.println("Please enter  on how much you would like to divide width");
		int dX = scan.nextInt();
		System.out.println("Please enter  on how much you would like to divide heigth");
		int dY = scan.nextInt();

		plates = new PartOfThePlate[dY + 1][dX + 1];
		for (int i = 0; i < plates.length; i++) {
			for (int k = 0; k < plates[i].length; k++) {
				if ((i == 0 && k == 0) || (i == 0 && k == plates[i].length - 1) || (i == plates.length - 1 && k == 0)
						|| (i == plates.length - 1 && k == plates[i].length - 1)) {
					plates[i][k] = new PartOfThePlate(false);
				} else if (i == 0) {
					plates[i][k] = new PartOfThePlate(temperatures[0], true, false, i, k);
				} else if (k == 0) {
					plates[i][k] = new PartOfThePlate(temperatures[3], true, false, i, k);
				} else if (i == plates.length - 1) {
					plates[i][k] = new PartOfThePlate(temperatures[2], true, false, i, k);
				} else if (k == plates[i].length - 1) {
					plates[i][k] = new PartOfThePlate(temperatures[1], true, false, i, k);
				} else
					plates[i][k] = new PartOfThePlate(0, true, true, i, k);
			}
		}
		System.out.println((dY + 1) + " " + (dX + 1));

	}

	Matrices createMatrix() {
		int unKnownAmount = 0;
		for (int i = 0; i < plates.length; i++) {
			for (int k = 0; k < plates[i].length; k++) {
				if (plates[i][k].isNeeded == true && plates[i][k].needToCalculate == true) {
					unKnownAmount++;
				}
			}
		}

		double[][] matrix = new double[unKnownAmount][unKnownAmount];
		double[] answerMatrix = new double[unKnownAmount];

		List<PartOfThePlate> list = getList();
		
		int temp = 0;
		for (PartOfThePlate partOfThePlate : list) {
			// Arrays.toString(getNeigbours(partOfThePlate.height, partOfThePlate.width,
			// unKnownAmount,aaa));
			matrix[temp] = getNeigbours(partOfThePlate.height, partOfThePlate.width, unKnownAmount, list);
			answerMatrix[temp] = caclulatesum(partOfThePlate.height, partOfThePlate.width);
			temp++;
		}
		Matrices finalMatrix = new Matrices(matrix, answerMatrix);
		System.out.println(unKnownAmount);

		return finalMatrix;
	}

	int caclulatesum(int ind1, int ind2) {
		int sum = 0;
		for (int i = ind1 - 1; i <=ind1 + 1; i++) {
			for (int k = ind2 - 1; k <= ind2 + 1; k++) {
				if (plates[i][k].isNeeded == true && plates[i][k].needToCalculate == false
						&& (Math.abs(ind1 - i) == 1 && ind2 == k) || (Math.abs(ind2 - k) == 1 && ind1 == i)) {
					sum += plates[i][k].temperature;
				}
			}
		}

		return -sum;
	}

	double[] getNeigbours(int ind1, int ind2, int matrixSize, List<PartOfThePlate> list) {
		double[] matrix = new double[matrixSize];
		int a = 0;
		for (PartOfThePlate partOfThePlate : list) {
			matrix[a] = isNeigbour(ind1, ind2, partOfThePlate.height, partOfThePlate.width);
			a++;
		}

		return matrix;
	}

	int isNeigbour(int ind1, int ind2, int i1, int i2) {
	
		if (ind1 == i1 && ind2 == i2) {
		
			return -4;
		} else if ((Math.abs(ind1 - i1) == 1 && ind2 == i2) || (Math.abs(ind2 - i2) == 1 && ind1 == i1)) {
			
			return 1;
		} else {
			return 0;
		}

	}

	List<PartOfThePlate> getList() {
		List<PartOfThePlate> list = new ArrayList<>();
		for (int i = 0; i < plates.length; i++) {
			for (int k = 0; k < plates[i].length; k++) {
				if (plates[i][k].isNeeded == true && plates[i][k].needToCalculate == true) {
					list.add(plates[i][k]);
				}
			}
		}
		return list;

	}
}
