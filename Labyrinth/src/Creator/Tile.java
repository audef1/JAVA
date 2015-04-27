package Creator;

/**
 * Created by FirstROW on 07.05.14.
*/

public class Tile{

	public Integer row;
	public Integer column;
	public int type;
	public Tile parent;
	
	//types: | 0. wall | 1. way | 3. start | 4. end | 5. path |

	public Tile(Integer x, Integer y, Tile p){
		this.row = x;
		this.column = y;
		this.parent = p;
	}
	
	public Tile opposite(){
		if(this.row != parent.row)
			return new Tile(this.row + this.row.compareTo(parent.row),this.column,this);
		if(this.column != parent.column)
			return new Tile(this.row,this.column+this.column.compareTo(parent.column),this);
		return null;
	}
}