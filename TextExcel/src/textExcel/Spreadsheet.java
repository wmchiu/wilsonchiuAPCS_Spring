/*
package textExcel;

public class Spreadsheet implements Grid{

	 Cell[][]spreadsheet=new Cell[getRows()][getCols()];
	
	public Spreadsheet(){
		
		for(int i = 0; i< spreadsheet.length; i++){
			for(int j = 0; j<spreadsheet[j].length; j++){
				spreadsheet[i][j]= new EmptyCell();
			}
		}
	}
	@Override

	public String processCommand(String command)
	{
		
		String[] splitCommand = command.split(" ");
		
		splitCommand[0] = splitCommand[0].toUpperCase();
		if(command.length()==0){
			return "";
		} else if(splitCommand.length >= 3){
			String userInput = splitCommand[2] + "";
			int counter = 3;
			while(counter < splitCommand.length){
				userInput += " " + splitCommand[counter];
				counter++;
			}
			String cell = splitCommand[0];
			cellAssignment(userInput, cell);
			return getGridText();
			//if its less than 3, it has to be cell inspection
		}else if(command.length() <= 3){
			return cellInspection(splitCommand[0]);
			//check if the user input has clear, has been changed to uppercase
		} else if (splitCommand[0].contains("CLEAR")){
			//if theres no spaces, then must be just clear so clear entire cell
			if(splitCommand.length == 1){
				 clearEntireCell();
				 return getGridText();
				 //if there is space, then must be clearing just one spot
			} else{
				//in case the cell isnt uppercased
				clearOneCell(splitCommand[1].toUpperCase());
				return getGridText();
			}
		}
		return "";
	}
	
	public int getRows()
	{
		return 20;
	}


	public int getCols()
	{
		return 12;
	}

	public Cell getCell(Location loc)
	{
		return spreadsheet[loc.getRow()][loc.getCol()];
	}
	
	public String getGridText()
	{
		String grid = "   |";
		//fills in the top row with the letters
		for(int i = 0; i < 12; i++){
			//cast to character type for letters, add the 10 spaces between this and the next one
			grid += (char) ('A' + i) + "         |";
		}
		
		//fills in the grid
		for(int i = 1; i <= 20; i++){
			//makes new line at end of row and adds the number
			grid += "\n" + i;
			//fixes the spacing when the numbers hit double digits
			if(i >= 10){
				grid += " |";
			}else{
				grid += "  |";
			}
			
			//sets all the values of each part of the array
			for(int k = 0; k < 12; k++){
				//includes the dashed lines at the end, only lets first 10 characters show
				//Puts the cell with its values in the grid
				//i-1 because i started at 1 for numbering but arrays are zero based
				grid += spreadsheet[i-1][k].abbreviatedCellText() + "|";
			}
		}
		// skips to next line after finishing creating the grid
		grid += "\n";
		return grid;
	}
	public String cellInspection(String cell){
		//makes new spreadsheetlocation object to get the rows and col
		SpreadsheetLocation a = new SpreadsheetLocation(cell);
		String result = spreadsheet[a.getRow()][a.getCol()].fullCellText();
		return result;
	}
	//assigns cell using Textcell constructor
	public void cellAssignment(String input, String cell){
		SpreadsheetLocation b = new SpreadsheetLocation(cell);
		//assigns cell accordingly to distinct characteristics
		if(input.contains("%")){
			spreadsheet[b.getRow()][b.getCol()] = new PercentCell(input);
		} else if(input.contains("\"")){
			spreadsheet[b.getRow()][b.getCol()] = new TextCell(input);
		} else if(input.contains("(")){
			spreadsheet[b.getRow()][b.getCol()] = new FormulaCell(input, spreadsheet);
		} else{
			spreadsheet[b.getRow()][b.getCol()] = new ValueCell(input);
		}
	}
	
	//sets everything to emptycell to clear
	public void clearEntireCell(){
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 12; j++){
				spreadsheet[i][j] = new EmptyCell();
			}
		}
	}
	public void clearOneCell(String cell){
		//clears one cell by making it an emptycell
		SpreadsheetLocation userInput = new SpreadsheetLocation(cell);
		spreadsheet[userInput.getRow()][userInput.getCol()] = new EmptyCell();
	}
	
}	
*/
/*
	
	
	
	package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	private Cell [][] cells = new Cell [this.getRows()][this.getCols()];
	public Spreadsheet(){
		for (int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[i].length; j++){
			cells[i][j] = new EmptyCell();
		}
		}
	}
	@Override
	public String processCommand(String command)
	{
		if(command.length() > 1){
		String [] Command = command.split(" ",3);
		if(Command.length == 2&&Command[0].toLowerCase().equals("clear")){  		//clearing a particular cell (e.g., clear A1).
			clearCell(Command[1]);
			return getGridText();
		}else if(Command.length == 3){						//assignment to string values (e.g., A1 = "Hello").
			assignValue(Command[0],Command[2]);
			return getGridText();	
		}else{
			if(Command.length==1&&Command[0].toLowerCase().equals("clear")){  //clearing the entire sheet (e.g., clear).
				clear();
				return getGridText();
			}else{     			//cell inspection (e.g., A1). This should return the value at that cell
				return inspectCell(Command[0]);
			}
		}
		}else {
			return command;
		}
		
	}
	public void clear(){
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				cells[i][j] = new EmptyCell();
			}
		}
	}
	public void clearCell(String location){
		SpreadsheetLocation loc = new SpreadsheetLocation(location.toUpperCase());
		cells[loc.getRow()][loc.getCol()] = new EmptyCell();
	}
	@Override
	public int getRows()
	{
		return 20;
	}

	@Override
	public int getCols()
	{
		return 12;
	}

	@Override
	public Cell getCell(Location loc)
	{
		int row = loc.getRow();
		int column = loc.getCol();
		return cells[row][column];
	}

	@Override
	public String getGridText()
	{
		String topLetter = "   |";
		for(char i = 'A'; i<='L'; i++){
			topLetter += i + "         |";
		}
		String numbers = "\n";
		for(int i = 0;i < 20;i++){
			if(i<9){
				numbers += (i+1);
				numbers += "  |";
				for(int j = 0; j<12;j++){
					numbers += cells[i][j].abbreviatedCellText() + "|";
				}
				numbers +="\n";
			}else{
				numbers += (i+1);
				numbers += " |";
				for(int j = 0; j<12;j++){
					numbers += cells[i][j].abbreviatedCellText() + "|";
				}
				numbers +="\n";
			}
		}
		return topLetter + numbers;
	}
	public void assignValue(String cell, String input){
		SpreadsheetLocation loc = new SpreadsheetLocation(cell.toUpperCase());
		if(input.contains("\"")){
			cells[loc.getRow()][loc.getCol()] = new TextCell(input.trim());
		}else if(input.contains("%")){
			cells[loc.getRow()][loc.getCol()] = new PercentCell(input);
		}else if(input.charAt(0) == '('){
			cells[loc.getRow()][loc.getCol()] = new FormulaCell(input, cells);
		}else{ 
			cells[loc.getRow()][loc.getCol()] = new ValueCell(input);
		}
	}
	
	public String inspectCell(String cell){
		SpreadsheetLocation loc = new SpreadsheetLocation(cell.toUpperCase()); 
		return getCell(loc).fullCellText();
}
	public Cell [][] getSheet() {
		return this.cells;
	}
public SpreadsheetLocation getLocation(String command) {
	SpreadsheetLocation location = new SpreadsheetLocation(command);
	return location;
}

}
*/
	
package textExcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.io.*;


public class Spreadsheet implements Grid
{
	private Cell[][] textExcel = new Cell [getRows()][getCols()];
	
	public Spreadsheet(){
		newSheet();
	}
	
	@Override
	public String processCommand(String command)
	{
			
		
		
		
			//checks to see if the input is anything, if not just ends here
			if(command.length() == 0 || command.equals("quit")){
				return "";
			}
			
			//splits the input by spaces
			String[] split = command.split(" ",3);
			
			//gets rid of issue if the input has lowercases or uppercases
			// for columns
			split[0] = split[0].toUpperCase();
			
			//checks to see if the input has save in it
			if(split[0].equals("SAVE")){
				return saveData(split[1]);
			}
			
			// checks for an open in the input
			if(split[0].equals("OPEN")){
				return openData(split[1]);
			}
			//checks to see if the input is only 3 characters or less
			//which could only be a cell and returns the value
			
			if(split.length == 3){
				setCellValue(split[0], split[2]);
				return getGridText();
			
			// the first value is all uppercase
			// checks to see if the first word is clear
			}else if(split[0].contains("CLEAR")){
				
				// means that its clear with a cell so clears the cell
				if(split.length == 2){
					split[1] = split[1].toUpperCase();
					clearCell(split[1]);
					
					//returns how the grid looks like afterwards
					return getGridText();
					
				}else{
					
					//clears the entire grid by making an entirely new grid
					newSheet();
					
					//returns the new grid
					return getGridText();
				}
				
			}else{
				//returns the value of the cell
				return inspectCell(split[0]);
			}	
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	

	@Override
	public int getRows()
	{
		//total number of rows
		return 20;
	}

	@Override
	public int getCols()
	{
		//total number of columns
		return 12;
	}

	@Override
	public Cell getCell(Location loc)
	{
		// uses spreadsheet location to find the location of the cell
		return textExcel[loc.getRow()][loc.getCol()];
	}

	@Override
	public String getGridText()
	{
		/*
		//begins the start of the grid
		String grid = "   |";
		
		//fills in the top row with the letters
		for(int i = 0; i < getCols(); i++){
			grid += (char) (i + 'A') + "         |";
		}
		
		//fills in the body or rest of the grid
		for(int i = 1; i <= getRows(); i++){
			//skips to a new line after each row is finished
			grid += "\n";
			
			//fills the first column to 3 characters
			if(i < 10){
				grid += i + "  |";
			}else{
				grid += i + " |";
			}
			
			//sets all the values of each part of the textExcel
			for(int j = 0; j < 12; j++){
				grid += textExcel[i-1][j].abbreviatedCellText() + "|";
			}
		}
		// skips to next line after finishing creating the grid
		grid += "\n";
		return grid;
		*/
		String grid = "   |";
		//fills in the top row with the letters
		for(int i = 0; i < getCols(); i++){
			//cast to character type for letters, add the 10 spaces between this and the next one
			grid += (char) ('A' + i) + "         |";
		}
		
		//fills in the grid
		for(int i = 1; i <= getRows(); i++){
			//makes new line at end of row and adds the number
			grid += "\n" + i;
			//fixes the spacing when the numbers hit double digits
			if(i >= 10){
				grid += " |";
			}else{
				grid += "  |";
			}
			
			//sets all the values of each part of the array
			for(int k = 0; k < getCols(); k++){
				//includes the dashed lines at the end, only lets first 10 characters show
				//Puts the cell with its values in the grid
				//i-1 because i started at 1 for numbering but arrays are zero based
				grid += textExcel[i-1][k].abbreviatedCellText() + "|";
			}
		}
		// skips to next line after finishing creating the grid
		grid += "\n";
		return grid;
	
	}
	
	public String inspectCell(String cellName){
		//creating an instance of spreadsheetLocation to use 
		//the getRow and getCol methods
		SpreadsheetLocation loc = new SpreadsheetLocation(cellName);
		//returns the full value of the cell
		return (getCell(loc).fullCellText());
	}
	
	public void clearCell(String location){
		
		//creating an instance of spreadsheetLocation to use 
		//the getRow and getCol methods
		SpreadsheetLocation loc = new SpreadsheetLocation(location);
		
		//remakes the cell into an empty cell to get rid of the value it had
		textExcel[loc.getRow()][loc.getCol()] = new EmptyCell();
	}
	
	public void newSheet(){
		//creates a new grid to make everything empty
		for(int i = 0; i < textExcel.length; i++){
			for( int j = 0; j < textExcel[i].length; j++){
				textExcel[i][j] = new EmptyCell();
			}
		}
	}
	
	public void setCellValue(String loc, String value){
		//creating an instance of spreadsheetLocation to use 
		//the getRow and getCol methods
		SpreadsheetLocation area = new SpreadsheetLocation(loc);
		//checks for quotes
		if(value.contains("\"")){
			textExcel[area.getRow()][area.getCol()] = new TextCell(value);
			//checks to see if it has a percentage
		}else if(value.contains("%")){
			textExcel[area.getRow()][area.getCol()] = new PercentCell(value);
			
			//checks for parenthesis
		}else if(value.endsWith(")")){
			textExcel[area.getRow()][area.getCol()] = new FormulaCell(value, textExcel);
			
			//if there isn't anything then it has to be a value cell
		}else{
			textExcel[area.getRow()][area.getCol()] = new ValueCell(value);
		}
		
	}
	
	public String cellType(Location loc){
		//tests for the type of cell and returns the type of cell as a string
		Cell cell = getCell(loc);
		if(cell instanceof TextCell){
			return "TextCell";
		}else if(cell instanceof PercentCell){
			return "PercentCell";
		}else if(cell instanceof ValueCell){
			return "ValueCell";
		}else{
			return "FormulaCell";
		}
	}
	
	private String saveData (String filename){ 

		PrintStream outputFile;
	
		try {
			outputFile = new PrintStream(new File(filename));
		}
	
		catch (FileNotFoundException e) {
			return "File not found: " + filename;
		}
		
		for(int i = 0; i < 12; i++){
			for(int j = 0; j < 20; j++){
				//makes the name or location of the cell
				String cell = ""; 
				cell += (char)(i + 'A');
				cell += j + 1;
				SpreadsheetLocation loc = new SpreadsheetLocation(cell);
				//if the cell isnt an empty cell, it sends the file the contents
				if(!(textExcel[j][i] instanceof EmptyCell)){
					outputFile.println(cell + "," + cellType(loc) + "," + textExcel[j][i].fullCellText());
				}
			}
		}
		outputFile.close();
		return "";
	}
	
	private String openData (String filename){

		Scanner outputFile;
		
		try {
			outputFile = new Scanner(new File(filename));
		}
		
		catch (FileNotFoundException e) {
			return "File not found: " + filename;
		}
		
		while(outputFile.hasNextLine()){
			//splits the line of the file by the commas
			String[] data = outputFile.nextLine().split(",");
			//adds the percentage to the percent cell value
			if(data[1].equals("PercentCell")){
				double holder = Double.parseDouble(data[2]);
				holder = holder * 100.0;
				data[2] = Double.toString(holder);
				data[2] += "%";
			}
			//sets the value of the cell
			setCellValue(data[0], data[2]);
		}
		outputFile.close();
		return getGridText();
	}
}
	
	
	
	
	
	
	

	
	
	
	

