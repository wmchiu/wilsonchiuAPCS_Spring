package textExcel;

public abstract class RealCell implements Cell {
	private String text;
	private int length;
	
	public RealCell(String command){
		text= command;
		
	}
	public double getDoubleValue(){
		return Double.parseDouble(text);
	}
	
	
	@Override
	public String abbreviatedCellText() {
		return (text + "          ").substring(0, 10);
	}

	@Override
	public String fullCellText() {
		return ("\"" + text.substring(0, length) + "\"");
	}

}
