package myapp.QualityMeasurementApp;


import java.util.Scanner;

public class QuantityMeasurementApp {
	
	public static void main(String[] args) {
		QuantityWeight w1 = new QuantityWeight(1.0,  WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0 , WeightUnit.GRAM);

        System.out.println("1 kg equals 1000 g ??");
        System.out.println(w1.equals(w2));

        System.out.println();

        QuantityWeight w3 = new QuantityWeight(2.0, WeightUnit.POUND);
        System.out.println("2 pounds to kg:");
        System.out.println(w3.convertTo(WeightUnit.KILOGRAM));

        System.out.println();

        QuantityWeight w4 = new QuantityWeight(500.0, WeightUnit.GRAM);
        System.out.println("1 kg + 500 g:");
        System.out.println(w1.add(w4));

        System.out.println();

        System.out.println("1 kg + 1000 g (in GRAM):");
        System.out.println(w1.add(w2, WeightUnit.GRAM));
	}
}
