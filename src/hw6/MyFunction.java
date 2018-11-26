/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw6;

import static hw6.ArrayMath.add;
import static hw6.ArrayMath.pow;

public class MyFunction implements Plottable2D{
    public double[] evaluate(double[] x) {
        return add(pow(x, 3), .1);
    }
}
