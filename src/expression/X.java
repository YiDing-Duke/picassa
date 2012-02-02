package expression;

import java.util.ArrayList;

import model.Expression;
import model.RGBColor;
import model.XyExpression;

public class X extends XyExpression{

	public X(ArrayList<Expression> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	public RGBColor evaluate (double evalX,double evalY)
    {
        return x(evalX,evalY);

    }
	public RGBColor x(double evalX,double evalY){
		return new RGBColor(evalX);
	}
}
