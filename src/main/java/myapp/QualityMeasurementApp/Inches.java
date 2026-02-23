package myapp.QualityMeasurementApp;


import java.util.Objects;

public class Inches {
	
	private final double value;
	
	private Inches(double value) {
		this.value = value; 
	}
	
	public static Inches fromString(String input) {
		try {
			double val = Double.parseDouble(input);
			return new Inches(val);
		}catch(NumberFormatException  e) {
			throw new InvalidFeetException("Invalid numeric value for Inches: " + input);
		}
	}
	
	public double getValue() {
		return value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		Inches other = (Inches)obj;
		return Double.compare(this.value, other.value) == 0;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
	
	@Override
	public String toString() {
		return value + " inch";
	}
}
