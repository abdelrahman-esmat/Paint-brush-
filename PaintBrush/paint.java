package paint_brush;
import java.applet.Applet;
import java.util.Date;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.awt.Component.*;
import java.util.*;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Font;
import java.awt.Image;
import java.awt.Robot;
import java.awt.CheckboxGroup;
public class paint extends Applet implements MouseListener,MouseMotionListener
 
{	// Double buffering objects
	Image buffer;
	Graphics gg;
	// front end objects
	CheckboxGroup colorModes=new CheckboxGroup();
	Font F;
	Color outColor=Color.RED,inColor;
	Button redb,yellowb,blackb,whiteb,blueb,freeb,lineb,squareb,ovalb,undob,clearb,eraserb,
			colorsb,shapesb,utlb,coutlineb,scolorb,outColorDisplay,inColorDisplay;
	Checkbox outlineb,inlineb;
	//back end objects
		// limits and position 
		int xi=0;
		int yi=0;
		int xf=0;
		int yf=0;
		int size=0;
		int  ulimit=230;
		int  llimit=620;
		int  lelimit=0;
		int  rlimit=1280;
		// shape object
		public Shape newShape=new Line();	
		// the shapes carrying vector
		public Vector <Shape> vec = new Vector <Shape> ();
		// Flags
		boolean onFlag=true;
		boolean switchflag=true;
		boolean outLineelectFlag=true;
		boolean undoFlag=false;
		boolean clearFlag=false;
	
	public void init()
	{	setLayout(null);
		outColor=Color.BLACK;
		inColor=Color.RED;
		F=new Font("lol",Font.BOLD,10);
		//Buttons 
			//color buttons
				//red button
				redb= new Button();
				redb.setBackground(Color.RED);
				redb.setBounds(50,60,30,20);
				redb.addActionListener( new ActionListener(){
														public void actionPerformed(ActionEvent ev){
														if (outLineelectFlag) outColor=Color.RED;
														else inColor=Color.RED;
														newShape.setColors(outColor,inColor);
														repaint();
														}});
				add(redb);
				//yellow button
				yellowb= new Button();
				yellowb.setBackground(Color.YELLOW);
				yellowb.setBounds(90,60,30,20);
				yellowb.addActionListener( new ActionListener(){
														public void actionPerformed(ActionEvent ev){
														if (outLineelectFlag) outColor=Color.YELLOW;
														else inColor=Color.YELLOW;
														newShape.setColors(outColor,inColor);
														repaint();
														}});
				add(yellowb);
				//black button
				blackb= new Button();
				blackb.setBackground(Color.BLACK);
				blackb.setBounds(130,60,30,20);
				blackb.addActionListener( new ActionListener(){
														public void actionPerformed(ActionEvent ev){
														if (outLineelectFlag) outColor=Color.BLACK;
														else inColor=Color.BLACK;
														newShape.setColors(outColor,inColor);
														repaint();
														}});
				add(blackb);
				//white button
				whiteb= new Button();
				whiteb.setBackground(Color.WHITE);
				whiteb.setBounds(170,60,30,20);
				whiteb.addActionListener( new ActionListener(){
														public void actionPerformed(ActionEvent ev){
														if (outLineelectFlag) outColor=Color.WHITE;
														else inColor=Color.WHITE;
														newShape.setColors(outColor,inColor);
														repaint();
														}});
				add(whiteb);
				//blue button
				blueb= new Button();
				blueb.setBackground(Color.BLUE);
				blueb.setBounds(210,60,30,20);
				blueb.addActionListener( new ActionListener(){
														public void actionPerformed(ActionEvent ev){
														if (outLineelectFlag) outColor=Color.BLUE;
														else inColor=Color.BLUE;
														newShape.setColors(outColor,inColor);
														repaint();
														}});
				add(blueb);
			// Color modes
				//outline color
					F=new Font("lol",Font.BOLD,10);
					outlineb= new Checkbox("oultine", colorModes, true);
					outlineb.setBounds(100,110,10,10);
					outlineb.addItemListener( new ItemListener(){
															public void itemStateChanged(ItemEvent ie){
															if (outLineelectFlag){outLineelectFlag=false;}
															else {outLineelectFlag=true;}
															repaint();
															}});
					add(outlineb);
				
				//inline color 
					F=new Font("lol",Font.BOLD,10);
					inlineb= new Checkbox("inline", colorModes, false);
					inlineb.setBounds(100,135,10,10);
					inlineb.addItemListener( new ItemListener(){
															public void itemStateChanged(ItemEvent ie){
															if (outLineelectFlag){outLineelectFlag=false;}
															else {outLineelectFlag=true;}
															repaint();
															}});
					add(inlineb);
				
			// Shapes
				// FREE button 
					freeb= new Button("Free");
					freeb.setFont(F);
					freeb.setBackground(Color.WHITE);
					freeb.addActionListener( new ActionListener(){
														public void actionPerformed(ActionEvent ev){
														newShape=new Free();
														newShape.setColors(outColor,inColor);
														}});
					freeb.setBounds(450,60,50,40);
					
					add(freeb);
				// Line button 
					lineb= new Button("Line");
					lineb.setFont(F);
					lineb.setBackground(Color.WHITE);
					lineb.setBounds(510,60,50,40);
					lineb.addActionListener( new ActionListener(){
														public void actionPerformed(ActionEvent ev){
														newShape=new Line();
														newShape.setColors(outColor,inColor);
														}});
					add(lineb);
				// Square button 
					squareb= new Button("Rectange");
					squareb.setFont(F);
					squareb.setBackground(Color.WHITE);
					squareb.setBounds(570,60,55,40);
					squareb.addActionListener( new ActionListener(){
														public void actionPerformed(ActionEvent ev){
														newShape=new Rect();
														newShape.setColors(outColor,inColor);
														}});
					add(squareb);
				// Oval button 
					ovalb= new Button("Oval");
					ovalb.setFont(F);
					ovalb.setBackground(Color.WHITE);
					ovalb.setBounds(640,60,50,40);
					ovalb.addActionListener( new ActionListener(){
														public void actionPerformed(ActionEvent ev){
														newShape=new Oval();
														newShape.setColors(outColor,inColor);
														}});
					add(ovalb);
				// utilities
					//undo button
					undob= new Button("Undo");
					undob.setFont(F);
					undob.setBackground(Color.WHITE);
					undob.setBounds(450,135,50,40);
					undob.addActionListener( new ActionListener(){
														public void actionPerformed(ActionEvent ev){
														undoFlag=true;
														repaint();
														}});
					add(undob);
					//clear all button
					clearb= new Button("clear all");
					clearb.setFont(F);
					clearb.setBackground(Color.WHITE);
					clearb.setBounds(510,135,50,40);
					add(clearb);
					clearb.addActionListener( new ActionListener(){
														public void actionPerformed(ActionEvent ev){
														clearFlag=true;
														repaint();
														}});
					//eraser button
					eraserb= new Button("eraser");
					eraserb.setFont(F);
					eraserb.setBackground(Color.WHITE);
					eraserb.setBounds(570,135,50,40);
					eraserb.addActionListener( new ActionListener(){
														public void actionPerformed(ActionEvent ev){
														newShape=new Eraser();
														}});
					add(eraserb);
	// mouser listeners
	addMouseListener(this);
	addMouseMotionListener(this);
	// initializing colours
	newShape.setColors(outColor,inColor);
	}
	
	/*
	*
	*
	*/
	
	public void update(Graphics g){
		//create offscreen image
		buffer= createImage(getWidth(),getHeight());
		//choose offscreen image as our canvas
		gg=buffer.getGraphics();
		//paint on the offscreen
		paint(gg);
		//draw offscreen on the on screen
		g.drawImage(buffer,0,0,null);
		
	}
	
	/*
	*
	*
	*/
	
	public void paint(Graphics g)
	{	
		//constants 
			// canvas
				g.setColor(Color.WHITE);
				g.fillRect(0,0,getWidth(),getHeight());
				g.setColor(Color.GRAY);
				g.fillRect(0,0,getWidth(),230);
			//titles
				g.setColor(Color.WHITE);
				g.drawString("Please do not change window size",1000,200);
				g.drawString("Colours",50,50);
				g.drawString("Shapes",450,50);
				g.drawString("Utilities",450,125);
				g.drawString("Colour mode",50,100);
				g.drawString("Outline",50,120);
				g.drawString("Inline",50,145);
				g.drawString("Selected colour",50,175);
			//g.drawString("size is " + size,10,10);
			//selected colour
				g.setColor(Color.YELLOW);
				if(outLineelectFlag) g.fillRect(46,181,58,38);
				else g.fillRect(126,181,58,38);
				g.setColor(outColor);
				g.fillRect(50,185,50,30);
				g.setColor(inColor);
				g.fillRect(130,185,50,30);
				g.setColor(Color.BLACK);
				g.drawRect(130,185,50,30);
				g.drawRect(50,185,50,30);
			
			//limits
				g.setColor(Color.BLACK);
				g.drawLine(lelimit,ulimit,rlimit,ulimit);
				g.drawLine(400,0,400,ulimit);
				g.drawLine(lelimit,llimit+2,rlimit,llimit+2);
				if(clearFlag)
				{
					vec.clear();
					size=0;
					clearFlag=false;
				}
	
			// draw objects
				drawAll(g,vec);
				if(newShape.getInitialY()>231)
				newShape.draw(g);

	}
	
	/*
	*
	*
	*
	*/
	
	public void drawAll(Graphics g,Vector <Shape> vec)
	{
		if(undoFlag)
		{
			if(size>0)
			{
				vec.remove(size-1);
				size--;
				undoFlag=false;
			}
		}
		for (int i =0;i<size;i++)
		{
			System.out.println("drawing"+i);
			vec.get(i).draw(g);
		}
	}
	
	/*
	*
	*
	*
	*/
	
	public void mouseDragged(MouseEvent e) 
	{
		xf=e.getX();
		if(e.getY()>231) yf=e.getY();
		else yf=231;
		newShape.setFinalPoint(xf,yf);
		repaint();
	}
	
	/*
	*
	*
	*
	*/
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) 
	{
		xi=e.getX();
		yi=e.getY();
		newShape.setInitialPoint(xi,yi);
	}
	public void mouseReleased(MouseEvent e) 
	{
		xf=e.getX();
		if(e.getY()>231) yf=e.getY();
		else yf=231;
		newShape.setFinalPoint(xf,yf);
		if (!(xf==xi & yf==yi) & newShape.getInitialY()>231 ) 
		{	
			vec.add(newShape);
			size++;
		}
		switch(newShape.returnType())
		{
				case 0:
				newShape=new Line();
				newShape.setColors(outColor,inColor);
				break;
				case 1:
				newShape=new Rect();
				newShape.setColors(outColor,inColor);
				break;
				case 2:
				newShape=new Free();
				newShape.setColors(outColor,inColor);
				break;
				case 3:
				newShape=new Oval();
				newShape.setColors(outColor,inColor);
				break;
				case 4:
				newShape=new Eraser();
				newShape.setColors(outColor,inColor);
				break;
				default:
				newShape=new Line();
				newShape.setColors(outColor,inColor);
		}
		
		repaint();
	}
	abstract class Shape{
		abstract public void draw(Graphics g);
		abstract public int getInitialY();
		abstract public void setInitialPoint(int x,int y);
		abstract public void setFinalPoint(int x,int y);
		abstract public int returnType();
		abstract public void setColors(Color out,Color in);
		
	}
	public void Undo()
	{
		vec.remove(size);
	}
	class  Line extends Shape
	{
		private Color inColor;
		private Color outColor;
		private int xii=0;
		private int yii=0;
		private int xff=0;
		private int yff=0;
		public int getInitialY(){return yii;}
		
		public void setColors(Color out,Color in)
		{
			inColor=in;
			outColor=out;
		}
		public void setInitialPoint(int x,int y)
		{
			xii=x;
			yii=y;
		}
		public void setFinalPoint(int x,int y)
		{
			xff=x;
			yff=y;
		}

		public void draw(Graphics g)
		{
			g.setColor(outColor);
			g.drawLine(xii,yii,xff,yff);
		}
		public int returnType(){return 0;}
	}
	class  Rect extends Shape
	{
		
		private int xii=0;
		private int yii=0;
		private int xff=0;
		private int yff=0;
		private Color inColor;
		private Color outColor;
		public int getInitialY(){return yii;}
	
		
		public void setColors(Color out,Color in)
		{
			inColor=in;
			outColor=out;
		}
		
		public void setInitialPoint(int x,int y)
		{
			xii=x;
			yii=y;
		}
		public void setFinalPoint(int x,int y)
		{
			xff=x;
			yff=y;	
		}

		public void draw(Graphics g)
		{
			if(yff<231) yff=231;
			if(xff>=xii && yff>=yii)
			{
				g.setColor(outColor);
				g.fillRect(xii,yii,xff-xii,yff-yii);
				g.setColor(inColor);
				g.fillRect(xii+2,yii+2,xff-xii-4,yff-yii-4);
			}
			if(xii>xff && yii>yff)
			{
				g.setColor(outColor);
				g.fillRect(xff,yff,xii-xff,yii-yff);
				g.setColor(inColor);
				g.fillRect(xff+2,yff+2,xii-xff-4,yii-yff-4);
			}
			if(xff>xii && yff<yii)
			{
				g.setColor(outColor);
				g.fillRect(xii,yff,xff-xii,yii-yff);
				g.setColor(inColor);
				g.fillRect(xii+2,yff+2,xff-xii-4,yii-yff-4);
			}
			if(xff<xii && yff>yii)
			{
				g.setColor(outColor);
				g.fillRect(xff,yii,xii-xff,yff-yii);
				g.setColor(inColor);
				g.fillRect(xff+2,yii+2,xii-xff-4,yff-yii-4);
			}
		}
		public int returnType(){return 1;}
	}
	class  Free extends Shape
	{
		
		int size1=0;
		private Color inColor;
		private Color outColor;
		public int [] xx = new int [797440];
		public int [] yy = new int [797440];
		public int getInitialY(){return yy[0];}
		
		
		public void setColors(Color out,Color in)
		{
			inColor=in;
			outColor=out;	
		}
		
		public void setInitialPoint(int x,int y)
		{
			
			xx[size1]=x;
			yy[size1]=y;
			size1++;
		}
		public void setFinalPoint(int x,int y)
		{
			xx[size1]=x;
			yy[size1]=y;
			size1++;
		}

		public void draw(Graphics g)
		{
			g.setColor(outColor);
			for(int j=0;j<size1-1;j++)
			{
				g.drawLine(xx[j],yy[j],xx[j+1],yy[j+1]);
			}
			
			
		}
		public int returnType(){return 2;}
	}
	class  Oval extends Shape
		{
			
			private int xii=0;
			private int yii=0;
			private int xff=0;
			private int yff=0;
			private Color inColor;
			private Color outColor;
		public int getInitialY(){return yii;}
			
			public void setColors(Color out,Color in)
			{
				inColor=in;
				outColor=out;
			}
			
			public void setInitialPoint(int x,int y)
			{
				xii=x;
				yii=y;
			}
			public void setFinalPoint(int x,int y)
			{
				xff=x;
				yff=y;
			}

			public void draw(Graphics g)
			{	if(yff<231) yff=231;
				if(xff>=xii && yff>=yii)
				{
					g.setColor(outColor);
					g.fillOval(xii,yii,xff-xii,yff-yii);
					g.setColor(inColor);
					g.fillOval(xii+2,yii+2,xff-xii-4,yff-yii-4);
				}
				if(xii>xff && yii>yff)
				{
					g.setColor(outColor);
					g.fillOval(xff,yff,xii-xff,yii-yff);
					g.setColor(inColor);
					g.fillOval(xff+2,yff+2,xii-xff-4,yii-yff-4);
				}
				if(xff>xii && yff<yii)
				{
					g.setColor(outColor);
					g.fillOval(xii,yff,xff-xii,yii-yff);
					g.setColor(inColor);
					g.fillOval(xii+2,yff+2,xff-xii-4,yii-yff-4);
				}
				if(xff<xii && yff>yii)
				{
					g.setColor(outColor);
					g.fillOval(xff,yii,xii-xff,yff-yii);
					g.setColor(inColor);
					g.fillOval(xff+2,yii+2,xii-xff-4,yff-yii-4);
				}
			}
			public int returnType(){return 3;}
		}
	class  Eraser extends Shape
	{
		
		int size1=0;
		private final Color inColor=Color.WHITE;
		private final Color outColor=Color.WHITE;
		public int [] xx = new int [797440];
		public int [] yy = new int [797440];
		public int getInitialY(){return yy[0];}
		
		public void setColors(Color out,Color in){}
		
		public void setInitialPoint(int x,int y)
		{
			xx[size1]=x;
			yy[size1]=y;
			size1++;
		}
		public void setFinalPoint(int x,int y)
		{
			xx[size1]=x;
			yy[size1]=y;
			size1++;
		}

		public void draw(Graphics g)
		{
			g.setColor(outColor);
			for(int j=0;j<size1-1;j++)
			{
				g.fillRect(xx[j],yy[j],15,15);
			}
			
		}
		public int returnType(){return 4;}
	}
}
