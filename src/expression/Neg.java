package expression;

import java.util.ArrayList;

import model.CommandExpression;
import model.Expression;
import model.RGBColor;

public class Neg extends CommandExpression{

	public Neg(ArrayList<Expression> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	public RGBColor evaluate (double evalX,double evalY)
    {
        return negtive(myOperands.get(0).evaluate(evalX,evalY));

    }
	public RGBColor negtive(RGBColor left) {
		return new RGBColor(-left.getRed(), 
                -left.getGreen() ,
                -left.getBlue());

	}
}