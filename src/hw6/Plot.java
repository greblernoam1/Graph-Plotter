package hw6;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Plot extends JPanel{

	private static final int NUMBER_OF_POINTS = 100;						// default number of points. Probably should be something user can set.
	private double[] xData; // default domain
	private double[] yData;	// default data to plot
	private Axis xAxis = new Axis(new double[] {0,1});						// It's not required to have an "Axis" class; it's just how I chose to do it
	private Axis yAxis = new Axis(new double[] {0,1});						// default axes go from 0 to 1
	private String xLabel = "x Axis";												// default for X label (empty)
	private String yLabel = "y Axis";												// default for Y label (empty)
	private String title = "Plot Title";												// default for title (empty)
	private Color lineColor = Color.RED;									// default line color
	private Font myFont = new Font("Times New Roman", Font.PLAIN, 20);		// default font
	
	//private JFrame frame;					// the JFrame in which the plot will appear
	private Plottable2D myFunction;                         // the function that will be plotted

	private int width = 800;				// width of plot in pixels
	private int height = 500;				// height of plot in pixels
	private int axisPad = 0;				// padding between axes and edges of window
	private int originX;					// X position (in pixels) of the intersection of the two axes measured from LEFT (not necessarily corresponding with x = 0, y = 0)
	private int originY;					// Y position (in pixels) of the intersection of the two axes measured from TOP
	private int axisEndX;					// X Position of end of X axis
	private int axisEndY;					// Y Position of end of Y axis
	private int axisWidth;					// AxisEndX - originX
	private int axisHeight;					// originY - AxisEndY - think about why these are reversed (positive/negative)
	private int xLabelY;					// Y coordinate of the x Label
	private int yLabelX;					// X coordinate of the y Label
	private int thickness = 1;

	public Plot(){
//		frame = new JFrame();									// create the JFrame				
//		
//                frame.add(this);										// add this instance of Plot to the JFrame
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setDisplaySize();
	}
	
	public void setDisplaySize(){
		this.setPreferredSize(new Dimension(width,height));		// set size of Plot
		
		originX = axisPad;										// intersection of axes should be axisPad (50) away from the LEFT edge
		originY = height - axisPad;								// intersection of axes should be axisPad (50) away from the BOTTOM edge (height - axisPad from the TOP)
		axisEndX = width - axisPad;								// etc...
		axisEndY = axisPad;
		axisWidth = width - axisPad*2;
		axisHeight = height - axisPad*2;
		xLabelY = height - axisPad/2;
		yLabelX = axisPad/2;
//		
//		frame.pack();
//		frame.setVisible(true);
		this.repaint();										// probably could have just repainted the Plot - this.repaint()
	}
	
	public void paintComponent(Graphics g){						// "this.repaint()" requests for this method to be called
		g.setFont(myFont);										
		clearPlot(g);
		drawAxes(g);
		drawLabels((Graphics2D) g);								// Remember, g is really an instance of Graphics2D, a subclass of Graphics. I need it to be a Graphics2D reference so I can use Graphics2D methods.
		if (yData != null){
                    drawData(g);
                }
	}
	
	private void clearPlot(Graphics g){							// clear the whole plot before plotting the axes, line, and labels 
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
	}
	
	private void drawAxes(Graphics g){
		g.setColor(Color.BLACK);													// dip the paintbrush in black
		g.drawLine(originX,originY,axisEndX,originY);								// draw the axis lines
		g.drawLine(originX,originY, originX, axisEndY);

		//g.drawString("" + xAxis.getLL(), coord2pix(xAxis.getLL(),'x'),  xLabelY);	// draw axis values at the endpoints
		//g.drawString("" + xAxis.getUL(), coord2pix(xAxis.getUL(),'x'),  xLabelY);
		//g.drawString("" + yAxis.getLL(), axisPad/3,  coord2pix(yAxis.getLL(),'y'));	// axisPad/3 looks better than yLabelX. Really this should be centered in the axis padding based on the string width.
		//g.drawString("" + yAxis.getUL(), axisPad/3,  coord2pix(yAxis.getUL(),'y'));
                
	}
        
        public String[] label(){
            String [] labe = new String [3];
            labe[0]  = title;
            labe[1]  = xLabel;
            labe[2]  = yLabel;
            
            
            return labe;
        }
        
        public double[] limits(){
            double [] limit = new double[4];
            double [] xlimit = xAxis.getLims();
            double [] ylimit = yAxis.getLims();
            limit[0] = xlimit[0];	
            limit[1] = xlimit[1];
            limit[2] = ylimit[0];	
            limit[3] = ylimit[1];
            
            return limit;
        }
	
	public void drawLabels(Graphics2D g){
		g.setColor(Color.BLACK);
		
		FontMetrics fm = g.getFontMetrics();								// found this in the Java API. It helps us measure a string's height/width as it will appear onscreen
		int xLabelWidth = fm.stringWidth(xLabel);							// we need the string width if we want to center it with respect to an axis
		int yLabelWidth = fm.stringWidth(yLabel);
		int yLabelHeight = fm.getHeight();									// the height of the font would be useful if we wanted to, say, position text a certain distance from an axis, or center it within the axis padding. But it's not used here.
		int xCenter = coord2pix((xAxis.getLL() + xAxis.getUL())/2, 'x');	// calculate the center of the axis
		int yCenter = coord2pix((yAxis.getLL() + yAxis.getUL())/2, 'y');
		//g.drawString(xLabel, xCenter-xLabelWidth/2, xLabelY);				// draw the text at that center, shifting by half the string width
		//g.drawString(title, xCenter-xLabelWidth/2, axisPad/2 );
		//frame.setTitle(title);
		
		// Here is code for writing the y label vertically. I found help at http://greybeardedgeek.net/2009/05/15/rotated-text-in-java-swing-2d/
		AffineTransform at = new AffineTransform();							
		at.rotate(-Math.PI/2);
		Font oldFont = g.getFont();
		Font newFont = oldFont.deriveFont(at);
		g.setFont(newFont);
		//g.drawString(yLabel,  yLabelX, yCenter+yLabelWidth/2);
		g.setFont(oldFont);		
	}
	
        public void setThickness(int t){
            thickness = t;
        }
        
	public void drawData(Graphics g){												// drawing the line is the easy part!
            Graphics2D g2 = (Graphics2D)g;
            g.setColor(lineColor);
            g.setClip(originX,axisEndY,axisWidth,axisHeight);
            g2.setStroke(new BasicStroke(thickness));
            g.drawPolyline(coord2pix(xData, 'x'), coord2pix(yData,'y'), xData.length);	
                
	}
        
        
        public void changeColor(Color col){
                lineColor = col;
                
                
        }
	
	private int coord2pix(double coord, char xy){									// this method converts axis coordinates (doubles) to the appropriate pixel locations on screen
		int pix = 0;
		if (xy == 'x'){
			double[] lims = xAxis.getLims();
			double x0 = lims[0];
			double x1 = lims[1];
			double dx = x1-x0;
			pix = originX + (int) Math.round(axisWidth/dx * (coord - x0));
		}
		else{
			double[] lims = yAxis.getLims();
			double y0 = lims[0];
			double y1 = lims[1];
			double dy = y1-y0;
			pix = originY - (int) Math.round(axisHeight/dy * (coord - y0));
		}	
		return pix;
	}
	
	private int[] coord2pix(double[] coord, char xy){								// I overload the same method to work with an array of doubles for convenience
		int[] pix = new int[coord.length];
		for (int i = 0; i < coord.length; i++)
			pix[i] = coord2pix(coord[i],xy);
		return pix;
	}
	
	public void setLineColor(Color lineColor){
		this.lineColor = lineColor;
	}
	
	public void setXLabel(String xLabel){
		this. xLabel = xLabel;
	}
	public void setYLabel(String yLabel){
		this. yLabel = yLabel;
	}
	public void setTitle(String title){
		this. title = title;
	}
	
	public void setDisplaySize(int width, int height){
		this.width = width;
		this.height = height;
		setDisplaySize();
	}
		
	public void setAxis(double[] axis){
		double x0 = axis[0];
		double x1 = axis[1];
		double y0 = axis[2];
		double y1 = axis[3];
		xAxis.setLims(new double[] {x0,x1});
		yAxis.setLims(new double[] {y0,y1});
		setData();
		this.repaint();

	}
	
	public void setFunction(Plottable2D myFunction){
		this.myFunction = myFunction;
		setData();
		this.repaint();

	}
	
	public void setData(){
		xData = ArrayMath.linspace(xAxis.getLL(), xAxis.getUL(), NUMBER_OF_POINTS);
		if (myFunction!=null){
                    yData = myFunction.evaluate(xData);
                }
	}
	
	
}