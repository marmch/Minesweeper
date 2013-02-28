import org.newdawn.slick.*;

import java.awt.Font;

public class Mine extends BasicGame
{
	int inputDelta = 0;
	final float SCALE = 2;
	final int BOMBGEN = 30;
	boolean youwon = false;
	static int numopen = 0;
	int numbombs = 0;
	int numflags = 0;
	static int grid[][] = new int[10][10];
	static int open[][] = new int[10][10];
	Image background, bomb, flag, one, two, three, four, five, six, seven, eight, hidden, blank, smiley;
	Image[][] imgrid = new Image[10][10];
	boolean smileypressed = false;
	boolean yousuck = false;
	Font font;
    TrueTypeFont trueTypeFont;
  public Mine()
  {
     super("Minesweeper");

	  
  }
  
  public void reset() {
	  numbombs = 0;
	  numopen = 0;
	  numflags = 0;
	  yousuck = false;
	  youwon = false;
	  for (int i = 0; i < grid.length; i++) {
		  for (int j = 0; j < grid.length; j++) {
			  grid[i][j] = 0;
			  open[i][j] = 0;
		  }
	  }
	  for(int i = 0; i < BOMBGEN; i++){
		  	double x = Math.random()*10;
		  	double y = Math.random()*10;
		  	grid[(int)x][(int)y] = -1;
		  }
		  for(int i = 0; i < 10; i++){
			  for(int j = 0; j < 10; j++){
				  if(grid[i][j]==-1){
					  numbombs++;
					  if(i==0 && j ==0){
						  grid[i+1][j+1] += (grid[i+1][j+1] == -1) ? 0 : 1;
						  grid[i+1][j] += (grid[i+1][j] == -1) ? 0 : 1;
						  grid[i][j+1] += (grid[i][j+1] == -1) ? 0 : 1;
					  }else if(i == 0 && j == 9){
						  grid[i+1][j] += (grid[i+1][j] == -1) ? 0 : 1;
						  grid[i][j-1] += (grid[i][j-1] == -1) ? 0 : 1;
						  grid[i+1][j-1] += (grid[i+1][j-1] == -1) ? 0 : 1;
					  }else if(i == 9 && j == 0){
						  grid[i-1][j] += (grid[i-1][j] == -1) ? 0 : 1;
						  grid[i][j+1] += (grid[i][j+1] == -1) ? 0 : 1;
						  grid[i-1][j+1] += (grid[i-1][j+1] == -1) ? 0 : 1;
					  }else if(i == 9 && j == 9){
						  grid[i-1][j] += (grid[i-1][j] == -1) ? 0 : 1;
						  grid[i][j-1] += (grid[i][j-1] == -1) ? 0 : 1;
						  grid[i-1][j-1] += (grid[i-1][j-1] == -1) ? 0 : 1;
					  }else if(i == 0){
						  grid[i+1][j+1] += (grid[i+1][j+1] == -1) ? 0 : 1;
						  grid[i+1][j] += (grid[i+1][j] == -1) ? 0 : 1;
						  grid[i][j+1] += (grid[i][j+1] == -1) ? 0 : 1;
						  grid[i][j-1] += (grid[i][j-1] == -1) ? 0 : 1;
						  grid[i+1][j-1] += (grid[i+1][j-1] == -1) ? 0 : 1;
					  }else if(i == 9){
						  grid[i-1][j] += (grid[i-1][j] == -1) ? 0 : 1;
						  grid[i][j+1] += (grid[i][j+1] == -1) ? 0 : 1;
						  grid[i-1][j+1] += (grid[i-1][j+1] == -1) ? 0 : 1;
						  grid[i][j-1] += (grid[i][j-1] == -1) ? 0 : 1;
						  grid[i-1][j-1] += (grid[i-1][j-1] == -1) ? 0 : 1;
					  }else if(j == 0){
						  grid[i-1][j] += (grid[i-1][j] == -1) ? 0 : 1;
						  grid[i][j+1] += (grid[i][j+1] == -1) ? 0 : 1;
						  grid[i-1][j+1] += (grid[i-1][j+1] == -1) ? 0 : 1;
						  grid[i+1][j+1] += (grid[i+1][j+1] == -1) ? 0 : 1;
						  grid[i+1][j] += (grid[i+1][j] == -1) ? 0 : 1;
					  }else if(j == 9){
						  grid[i-1][j] += (grid[i-1][j] == -1) ? 0 : 1;
						  grid[i][j-1] += (grid[i][j-1] == -1) ? 0 : 1;
						  grid[i-1][j-1] += (grid[i-1][j-1] == -1) ? 0 : 1;
						  grid[i+1][j] += (grid[i+1][j] == -1) ? 0 : 1;
						  grid[i+1][j-1] += (grid[i+1][j-1] == -1) ? 0 : 1;
					  }else{
						  grid[i-1][j] += (grid[i-1][j] == -1) ? 0 : 1;
						  grid[i-1][j-1] += (grid[i-1][j-1] == -1) ? 0 : 1;
						  grid[i-1][j+1] += (grid[i-1][j+1] == -1) ? 0 : 1;
						  grid[i][j-1] += (grid[i][j-1] == -1) ? 0 : 1;
						  grid[i][j+1] += (grid[i][j+1] == -1) ? 0 : 1;
						  grid[i+1][j] += (grid[i+1][j] == -1) ? 0 : 1;
						  grid[i+1][j-1] += (grid[i+1][j-1] == -1) ? 0 : 1;
						  grid[i+1][j+1] += (grid[i+1][j+1] == -1) ? 0 : 1;
					  }
				  }
			  }
		  }
  }
  
  @Override
  public void init(GameContainer gc) throws SlickException
  {
	  font = new Font("Verdana", Font.BOLD, 50);
	  trueTypeFont = new TrueTypeFont(font, true);
	  background = new Image("img\\background.png");
	  bomb = new Image("img\\bomb.png");
	  flag = new Image("img\\flag.png");
	  one = new Image("img\\one.png");
	  two = new Image("img\\two.png");
	  three = new Image("img\\three.png");
	  four = new Image("img\\four.png");
	  five = new Image("img\\five.png");
	  six = new Image("img\\six.png");
	  seven = new Image("img\\seven.png");
	  eight = new Image("img\\eight.png");
	  blank= new Image("img\\blank.png");
	  hidden = new Image("img\\hidden.png");
	  smiley = new Image("img\\smiley.png");

	  reset();
	  
	  
  }
 
  @Override
  public void update(GameContainer gc, int delta) throws SlickException
  {
	  inputDelta -= delta;
	  if(inputDelta < 0){
		  inputDelta -= delta;
		  Input input = gc.getInput();
		  int x = input.getMouseX();
		  int y = input.getMouseY();
		  if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && x >= 500 && x <= 500 + smiley.getWidth() && y >= 0 && y <= smiley.getHeight()){
			  smiley = new Image("img\\derpsmiley.png");
			  smileypressed = true;
		  }
		  else if (smileypressed){
			  smiley = new Image("img\\smiley.png");
			  smileypressed = false;
			  reset();
		  }
		  if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && x >= 50 && x < 50+10*SCALE*24 && y >= 100 && y < 100+10*SCALE*24 && !youwon){
			  int ax = (int) ((x-50)/(SCALE*24));
			  int ay = (int)((y-100)/(SCALE*24));
			  if(open[ax][ay]==0){
				  if(grid[ax][ay]==-1)
					  yousuck = true;
				  else{
					  floodfill(ax,ay);
				  }
			  }
			  if(numopen==grid.length*grid[0].length-numbombs)
				  youwon = true;  
		  }
		  if(input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON) && x >= 50 && x < 50+10*SCALE*24 && y >= 100 && y < 100+10*SCALE*24 && !youwon){
			  int ax = (int) ((x-50)/(SCALE*24));
			  int ay = (int)((y-100)/(SCALE*24));
			  if(open[ax][ay]==0){
				  open[ax][ay] = 2;
				  numflags++;
			  }
			  else if(open[ax][ay]==2){
				  open[ax][ay] = 0;
				  numflags--;
			  }
			  inputDelta = 200;
		  }
	  }
  }
 
  @Override
  public void render(GameContainer gc, Graphics g) throws SlickException
  {
	  g.setColor(Color.black);
	  background.draw();
	  smiley.draw(500,0);
	  for(int i = 0; i < 10; i++){
		  for(int j = 0; j < 10; j++){
			  float ax = 50+i*SCALE*24;
			  float ay = 100+j*SCALE*24;
			  if(open[i][j]==1 || yousuck || youwon){
				  switch(grid[i][j]){
				  	case -1 : bomb.draw(ax,ay,SCALE);break;
				  	case 0 : blank.draw(ax,ay,SCALE);break;
				  	case 1 : one.draw(ax,ay,SCALE);break;
				  	case 2 : two.draw(ax,ay,SCALE);break;
				  	case 3 : three.draw(ax,ay,SCALE);break;
				  	case 4 : four.draw(ax,ay,SCALE);break;
				  	case 5 : five.draw(ax,ay,SCALE);break;
				  	case 6 : six.draw(ax,ay,SCALE);break;
				  	case 7 : seven.draw(ax,ay,SCALE);break;
				  	case 8 : eight.draw(ax,ay,SCALE);break;
				  	default : System.out.println("ANUS");break;	
				  }
			  }
			  else if(open[i][j]==0)
				 hidden.draw(ax,ay,SCALE);
			  else
				 flag.draw(ax,ay,SCALE);
		  }
	  }
	  g.drawString("Bombs Remaining: " + (numbombs - numflags), 10, 70);
	  if(yousuck){
		  trueTypeFont.drawString(150, 250,"YOU SUCK", Color.red);
		  trueTypeFont.drawString(50, 310, "HUGE BLACK DICK", Color.red);
	  }
	  else if(youwon){
		  trueTypeFont.drawString(50, 250,"YOU DON'T SUCK", Color.red);
	  }
  }
 
  public static void main(String[] args) throws SlickException
  {
     AppGameContainer app = new AppGameContainer(new Mine());
 
     app.setDisplayMode(600, 600, false);
     app.start();
  }
  
  public static void floodfill(int x, int y){
	  if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && open[x][y]==0){
		  numopen++;
		  if(grid[x][y] > 0)
			  open[x][y] = 1;
		  else{
			  open[x][y] = 1;
			  floodfill(x-1,y-1);
			  floodfill(x-1,y+1);
			  floodfill(x+1,y);
			  floodfill(x+1,y-1);
			  floodfill(x+1,y+1);
			  floodfill(x-1,y);
			  floodfill(x,y+1);
			  floodfill(x,y-1);
		  }
	  }
  }
}