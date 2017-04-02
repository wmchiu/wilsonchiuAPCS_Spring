package textExcel;

public class FormulaCell extends RealCell {
	/*
	public double getDoubleValue(){
		return 0.0;				
	}
	public FormulaCell(String cellText){
		super(cellText);
	}
	@Override
	public String abbreviatedCellText() {
		// TODO Auto-generated method stub
		return super.abbreviatedCellText();
	}

	@Override
	public String fullCellText() {
		// TODO Auto-generated method stub
		return super.fullCellText();
	}
	*/
	/*
	private String formula;
	public FormulaCell(String cellText){
		super(cellText);
		formula=cellText;
	}
	
	public double getDoubleValue(){
		String[] splittedFormula=formula.split(" ");
		double currAnswer=Double.parseDouble(splittedFormula[1]);	//initialize currAnswer as first number and cycle through for loop
		for(int i=2;i<splittedFormula.length-1;i+=2){				//for loop to cycle through every other operator
			double firstNum=Double.parseDouble(splittedFormula[i+1]);
			if(splittedFormula[i].equals("+")){			//addition
				currAnswer+=firstNum;
			}else if(splittedFormula[i].equals("-")){	//subtraction
				currAnswer-=firstNum;
			}else if(splittedFormula[i].equals("*")){	//multiplication
				currAnswer*=firstNum;
			}else if(splittedFormula[i].equals("/")){	//division
				currAnswer/=firstNum;
			}
		}
		return currAnswer;				
	}
	
	@Override
	public String abbreviatedCellText() {
		// TODO Auto-generated method stub
		return (getDoubleValue()+"            ").substring(0, 10);		//add however many spaces and then substring
	}

	@Override
	public String fullCellText() {
		// TODO Auto-generated method stub
		return formula;						//return the formula itself only with correct calculations
	}
	*/
Cell[][] excelSpreadsheet = new Cell[20][12];
	
	public FormulaCell(String formula){
		super(formula);
	}
	
	public double getDoubleValue(){
		String input = getUserInput();
		String[] splitted=input.split(" ");
		if (splitted[0].toLowerCase().contains("sum")) {
		
		}
		if (splitted[0].toLowerCase().contains("avg")) {
			double number;
		}
		double first=Double.parseDouble(splitted[1]);
		for(int i=2;i<splitted.length-1;i+=2){
			double nextNum=Double.parseDouble(splitted[i+1]);
			if(splitted[i].equals("+")){	//addition
				first+=nextNum;
			}else if(splitted[i].equals("-")){	//subtraction
				first-=nextNum;
			}else if(splitted[i].equals("*")){	//multiplication
				first*=nextNum;
			}else if(splitted[i].equals("/")){	//division
				first/=nextNum;
			}
		}
		return first;				
	}
	
	@Override
	public String abbreviatedCellText() {
		
		return (getDoubleValue()+"            ").substring(0, 10);	
	}

	@Override
	public String fullCellText() {

		return getUserInput();	//return the formula itself only with correct calculations
	}

	

}
