package aop_xml;

public class ImpeCalculator implements Calculator {

	@Override
	public long factorial(long num) {
		long result = 1;
		for(int i=0; i<=num; i++) {
			result *= i;
		}
		return result;
	}

}
