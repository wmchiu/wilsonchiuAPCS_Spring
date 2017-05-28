
package textExcel;

public class SpreadsheetLocation implements Location
{ 
	
	private String location;
	public int getRow()
    {
	
		int row = Integer.parseInt(location.substring(1));
        return row - 1;
    }

    public int getCol()
    {
  
    	int col = location.charAt(0);
    	return col - 65;
    }
    
    public SpreadsheetLocation(String cellName)
    {
        location = cellName;
    }


}
