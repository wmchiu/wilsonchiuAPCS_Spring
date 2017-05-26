

package textExcel;



public abstract class RealCell implements Cell {
	private String userInput = "";
	private int counter = 0;
	//abstract classto make the subclasses contain this method
	public abstract double getDoubleValue();
	public String abbreviatedCellText() {
		if(userInput.length() < 10){
			//has to be percent with this sign
			if(userInput.contains("%")){
				//makes a placeholder so it doesnt affect full cell text
				String placeholder = userInput.substring(0, userInput.indexOf(".")) + "%";
				//formats to have 10 spaces
				return addSpaces(placeholder);
			}
			//checks if its a whole number thats a value cell, no percent or formula cell
			if(!userInput.contains(".") && !userInput.contains("(")){
				String anotherPlaceholder = userInput + ".0";
				return addSpaces(anotherPlaceholder);
			}
			//the one exception to having 2 zeros next to each other, treats it differently and just adds spaces
			if(userInput.equals("0.0")){
				userInput = addSpaces(userInput);
				return userInput;
			}
			//reverse for loop to check for two or more zeros together from the end
			if(!userInput.contains("(")){
				for(int i = userInput.length()-1; i >=0; i--){
					//ends the loop if its not a zero
					if(userInput.charAt(i) != '0'){
						i = 0;
					}
					if(userInput.charAt(i) == '0'){
						counter ++;
						//checks to see if there is 2 zeros in a row
						if(counter >= 2){
							userInput = userInput.substring(0, i);
						}
					}

				}
			}
			//adds a .0 if theres nothing after the decimal
			if(userInput.charAt(userInput.length()-1) == '.'){
				userInput = userInput + "0";
			}
			userInput = addSpaces(userInput);
			//if longer than 10, shortens to 10
		} else if(userInput.length() > 10){
			return userInput.substring(0, 10);
		}
		return userInput;
	}

	public String getRealCell(){
		return userInput;
	}
	public void setRealCell(String value){
		userInput = value;
	}
	public String fullCellText() {
		RealCell whatever;
		if(userInput.equals("0")){
			return "0";
		}
		//finds the right cell form
		if(userInput.contains("%")){
			whatever = new PercentCell(userInput);
			//formulacell is overriden for fullcelltext
		}else{
			whatever = new ValueCell(userInput);
		}
		//switches double to string to get rid of extra 0s at the end
		String removeZeros = whatever.getDoubleValue() + "";
		//makes sure number is positive and longer than one place value
		if((removeZeros.substring(removeZeros.indexOf(".")).equalsIgnoreCase(".0")) && ((whatever.getDoubleValue() > 0) && (removeZeros.length() > 3))){
			return removeZeros.substring(0, removeZeros.indexOf("."));
		}
		return whatever.getDoubleValue() + "";

}
	public String addSpaces(String placeholder){
		//formats abbreviatedCellText to length 10 if its too short
		while(placeholder.length() < 10){
			placeholder += " ";
		}
		return placeholder;
	}
	public String getValue() {
		return this.userInput;
	}
	public void setValue(String text) {
		this.userInput = text;
	}
}


/*
package textExcel;

abstract class RealCell implements Cell {
	
	private String cellContent;
	
	public String getRealCell(){
		return cellContent;
	}
	public void setRealCell(String value){
		cellContent = value;
		
	}
	
	public String abbreviatedCellText() {

		return padAndTruncate();
	}

	public String fullCellText() {

		String returnVal = cellContent;
		double holder = 0.0;
		
		//checks to see if the value is a percentage or just a number
		if(returnVal.contains("%")){
			holder = getDoubleValue();
			//if its a formula, just return the cell value, doesn't change it
		}else if(returnVal.indexOf(")") == returnVal.length() - 1){
			return returnVal;
			//if the content of the cell is 0, doesn't change it, just return 0
		}else if(returnVal.equals("0")){
			return "0";
		}else{
			holder = Double.parseDouble(returnVal);
		}
		
		//converts the number to a string
		returnVal = Double.toString(holder);
		
		//checks to see if the number ends with a .0
		if(returnVal.substring(returnVal.length() - 2, returnVal.length()).equals(".0")){
			
			//checks to see if the number is negative, or if it only has 1 whole number in it
			if(returnVal.substring(0 , returnVal.indexOf(".0")).length() <= 1 || returnVal.contains("-")){
				return returnVal;
				
				//if the number isn't just one whole number or negative, then cuts off the .0
			}else{
				return returnVal.substring(0,returnVal.length() - 2);
			}
			
		//if it doesn't end in a .0, then checks for unnecessary zeroes to possibly cut out
		}else{
			return checkZeroes(returnVal);
		}

	}
	
	
	public String padAndTruncate(){
		String returnVal = cellContent;
		if(returnVal.charAt(0) == '('){
			returnVal = Double.toString(getDoubleValue());
		}
		//if its a whole number without a percent, make it a double by adding .0 to it
		if(!returnVal.contains(".") && !returnVal.contains("%") ){
			returnVal += ".0";
		}
		
		//gets rid of unnecessary zeroes at the end of the value
		returnVal = checkZeroes(returnVal);
		//checks for unnecessary zeroes again and cuts off the last one to make it a .0 if there is
		if(returnVal.indexOf(".00") + 3 == returnVal.length()){
			returnVal = returnVal.substring(0, returnVal.length() - 1);
		}

		//checks to see if there is a percent sign and takes off the decimal point
		if(returnVal.contains("%")){
			returnVal = returnVal.substring(0, returnVal.indexOf('.'));
			returnVal += "%";
		}
		//checks to see if the value is longer than 10, if it is it cuts it off at the tenth character,
		//or adds spaces to fill it up to ten spaces
		if(returnVal.length() >= 10){
			returnVal = returnVal.substring(0, 10);
			
		}else{
			
			while(returnVal.length() != 10){
				returnVal += " ";
			}
		}
		
		return returnVal;
	}
	
	abstract double getDoubleValue();

	public String checkZeroes(String value){
		int continuousZero = 0;
		int countZero = 0;
		int count = value.length() - 1;
		
		// continues loop until the count hits the start of the value or zero
		while(count != 0){
			//checks for two zeroes in a row and saves the value to continuosZero
			if(countZero >= 2){
				continuousZero = countZero;

			}
			
			//checks for zeros or if the value is at a .0 (which it doesn't count)
			if(value.charAt(count) == '0' && value.charAt(count - 1) != '.'){
				countZero++;

			}else{
				// if its anything but a zero without a decimal point near it reset the count for zeroes
				countZero = 0;

			}
			//decrement by one to go down the value
			count--;

		}
		
		//returns the value after substring the amount of unnecessary zereos
		return value.substring(0, value.length() - continuousZero);
	}
	
}
*/