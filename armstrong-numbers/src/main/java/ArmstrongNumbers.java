class ArmstrongNumbers {

	/* See: https://en.wikipedia.org/wiki/Narcissistic_number */
	boolean isArmstrongNumber(int numberToCheck) {
		/* Strategy:
		* Count the number of digits in a number, call this L.
		* For each digit d in the number N, take the sum from i = 0
		* to i = L of d^L .
		* If this sum equals N, then N is an Armstrong number.
		 */

		String numberAsString = Integer.toString(numberToCheck);

		int numberLength = numberAsString.length();

		int sum = 0;

		for (int i = 0; i < numberAsString.length(); i++)
		{
			int digit = Integer.parseInt(String.valueOf(numberAsString.charAt(i)));
			int digitRaisedToLength = (int)Math.pow( (double)digit, (double)numberLength);

			sum += digitRaisedToLength;

		}


		return (sum == numberToCheck);
	}

}
