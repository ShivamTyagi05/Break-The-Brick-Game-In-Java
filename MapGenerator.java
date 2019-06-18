import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;


public class MapGenerator {
	public int map[][];
	public int bWidht;
	public int bHeight;
	public MapGenerator(int row, int col){
		map = new int[row][col];
		for(int i=0 ; i < map.length ; i++)
		{
			for(int j=0 ; j < map[0].length ; j++)
			{
				map[i][j] = 1;
			}
		}
		bWidht=540/col;
		bHeight=150/row;
	}
	
	public void draw(Graphics2D g)
	{
		for(int i=0 ; i < map.length ; i++)
		{
			for(int j=0 ; j < map[0].length ; j++)
			{
				if(map[i][j]>0)
				{
					g.setColor(Color.white);
					g.fillRect(j*bWidht+80, i*bHeight+50, bWidht, bHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*bWidht+80, i*bHeight+50, bWidht, bHeight);
				
				}
			}
		}
	}
	
	public void setBrickValue(int value,int row,int col)
	{
		map[row][col]=value;
	}
}
