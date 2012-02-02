package expression;

import java.util.ArrayList;

import model.Expression;
import model.RGBColor;
import model.XyExpression;


public class Y extends XyExpression{

	public Y(ArrayList<Expression> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	public RGBColor evaluate (double evalX,double evalY)
    {
        return y(evalX,evalY);

    }
	public  RGBColor y(double evalX,double evalY){
		return new RGBColor(evalY);
	}
}
