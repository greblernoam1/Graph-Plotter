package hw6;
import static hw6.ArrayMath.*;

// I thought having axis objects would be useful, but it turns out they are quite simple and don't really add value
// If I were to write this again, I would probably just include the axis limits as fields (or a single field - a double array) in the plot class
// If, on the other hand, I was going to make the plot more complete, with ticks along the axis and labels at each tick, I might want an axis object
//    with the capability to draw itself.
public class Axis {															

	private double minVal;
	private double maxVal;
	private double[] tickLabels;							// I don't end up using this in the plotting code
	private final static int DEFAULT_NO_OF_SEGMENTS = 10;

	public Axis(double[] tickLabels){
		setLims(tickLabels);
	}
	
	public void setLims(double[] tickLabels){				
		minVal = min(tickLabels);
		maxVal = max(tickLabels);
		if (tickLabels.length == 2){						// not really used in plotting code
			tickLabels = linspace(minVal,maxVal,DEFAULT_NO_OF_SEGMENTS);
			
		}
		this.tickLabels = tickLabels;
	}
		
	public double[] getLims(){
		double[] lims = new double[2];
		lims[0] = minVal;
		lims[1] = maxVal;
		return lims;
	}
	
	public double getLL(){
		return minVal;
	}
	
	public double getUL(){
		return maxVal;
	}
	
	public double[] getTickLabels(){
		return tickLabels;
	}
	
	
}
