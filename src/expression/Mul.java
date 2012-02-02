package expression;

import java.util.ArrayList;

import model.CommandExpression;
import model.Expression;
import model.RGBColor;


public class Mul extends CommandExpression{

	public Mul(ArrayList<Expression> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	public RGBColor evaluate (double evalX,double evalY)
    {
        return multiply(myOperands.get(0).evaluate(evalX,evalY), myOperands.get(1).evaluate(evalX,evalY));

    }
	public RGBColor multiply (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() * right.getRed(), 
                            left.getGreen() * right.getGreen(),
                            left.getBlue() * right.getBlue());
    }

}
