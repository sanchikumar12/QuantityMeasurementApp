package myapp.QualityMeasurementApp;



import java.util.Objects;

public final class Feet {
	
		private final double value;
		
		private Feet(double value) {
			this.value = value;
		}
		
		public static Feet fromString(String input) throws NumberFormatException {
			try {
				double val = Double.parseDouble(input);
				return new Feet(val);
			} catch(NumberFormatException e) {
				throw new InvalidFeetException("Invalid numeric value for Feet: " + input);
			}
		}
		
		public double getValue() {
			return value;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(this == obj)return true;
			if(obj == null || getClass() != obj.getClass())return false;
			Feet other = (Feet)obj;
			return Double.compare(this.value,other.value) == 0;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(value);
		}
		
		@Override
		public String toString() {
			return value + " ft";
		}
}
