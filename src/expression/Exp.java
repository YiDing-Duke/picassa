package expression;

import java.util.ArrayList;

import model.CommandExpression;
import model.Expression;
import model.RGBColor;

public class Exp extends CommandExpression{

	public Exp(ArrayList<Expression> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	public RGBColor evaluate (double evalX,double evalY)
    {
        return exponent(myOperands.get(0).evaluate(evalX,evalY), myOperands.get(1).evaluate(evalX,evalY));

    }
    public RGBColor exponent (RGBColor left, RGBColor right)
    {
        return new RGBColor(Math.pow(left.getRed() , right.getRed()), 
        		Math.pow(left.getGreen(), right.getGreen()),
        		Math.pow(left.getBlue() , right.getBlue()));
    }
}