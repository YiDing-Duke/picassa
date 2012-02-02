package expression;

import java.util.ArrayList;

import model.CommandExpression;
import model.Expression;
import model.RGBColor;

public class Mod extends CommandExpression{

	public Mod(ArrayList<Expression> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	public RGBColor evaluate (double evalX,double evalY)
    {
        return modulus(myOperands.get(0).evaluate(evalX,evalY), myOperands.get(1).evaluate(evalX,evalY));

    }
    public RGBColor modulus (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() % right.getRed(), 
                            left.getGreen() % right.getGreen(),
                            left.getBlue() % right.getBlue());
    }
}