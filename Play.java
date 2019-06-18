import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Play extends JPanel implements KeyListener,ActionListener{
	private boolean play = false;
	private int score = 0;
	private int totalBricks=50;
	private Timer timer;
	private int delay = 8;
	private int playerX = 350;
	private int ballX = 120;
	private int ballY = 360;
	private int ballXdir = -1;
	private int ballYdir = -2;
	private MapGenerator map;
	public Play()
	{
		map=new MapGenerator(5,10);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g){
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//brick
		map.draw((Graphics2D)g);
		
		//border
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//Paddle
		g.setColor(Color.GREEN);
		g.fillRect(playerX, 550, 100, 8);
		
		//Ball
		g.setColor(Color.yellow);
		g.fillOval(ballX, ballY, 20, 20);
		
		//Score
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score, 590, 30);
		
		if(ballY > 590)
		{
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,25));
			g.drawString("Game Over", 190, 300);
		}
		
		if(totalBricks == 0)
		{
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,25));
			g.drawString("You Win", 190, 300);
		}
		
		g.dispose();
	}

	private void setFocusTraversalKeyEnable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		
		if(play){
			if(new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(playerX,550,100,8)))
			{
				ballYdir = -ballYdir;
			}
			
			A:for(int i=0 ; i < map.map.length ; i++)
			{
				for(int j=0 ; j < map.map[0].length ; j++)
				{
					if(map.map[i][j] > 0){
							
						int brickX = j * map.bWidht + 80;
						int brickY = i * map.bHeight + 50;
						int brickWid = map.bWidht;
						int brickHit = map.bHeight;
						
						Rectangle rect = new Rectangle(brickX,brickY,brickWid,brickHit);
						Rectangle ballrect = new Rectangle(ballX,ballY,20,20);
						Rectangle brickrect = rect;
						
						if(ballrect.intersects(brickrect))
						{
							map.setBrickValue(0,i,j);
							totalBricks--;
							score += 5;
							
							if(ballX + 19 <= brickrect.x || ballX + 1 >= brickrect.x + brickrect.width)
							{
								ballXdir = -ballXdir;
							}
							else
							{
								ballYdir = -ballYdir;
							}
							
							break A;
						}
					}
				}
			}
			
			ballX += ballXdir;
			ballY += ballYdir;
			if(ballX < 0)
			{
				ballXdir = -ballXdir;
			}
			if(ballY < 0)
			{
				ballYdir = -ballYdir;
			}
			if(ballX > 670)
			{
				ballXdir = -ballXdir;
			}
			
		}
		
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(playerX > 600)
				playerX = 600;
			else
				moveRight();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(playerX < 10)
				playerX = 10;
			else
				moveLeft();
		}
	}
	
	public void moveRight()
	{
		play = true;
		playerX += 20;
	}
	
	public void moveLeft()
	{
		play = true;
		playerX -= 20;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	

}
