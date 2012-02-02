package expression;

import java.util.ArrayList;

import model.CommandExpression;
import model.Expression;
import model.RGBColor;

public class Color extends CommandExpression{

	public Color(ArrayList<Expression> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	public RGBColor evaluate (double evalX,double evalY)
    {
        return color(myOperands.get(0).evaluate(evalX,evalY), myOperands.get(1).evaluate(evalX,evalY),myOperands.get(2).evaluate(evalX,evalY));

    }
	public RGBColor color(RGBColor left,RGBColor mid,RGBColor right){
		return new RGBColor(left.getRed(), 
                mid.getGreen() ,
                right.getBlue());
	}
}
