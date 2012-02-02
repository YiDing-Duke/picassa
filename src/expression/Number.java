package expression;

import java.util.regex.Matcher;

import model.Expression;
import model.NumberExpression;
import model.RGBColor;

public class Number extends NumberExpression{
	


	public Number(RGBColor value) {
		super(value);
	//	this.position=position;
		// TODO Auto-generated constructor stub
	}

	public RGBColor evaluate (double evalX,double evalY)
    {
        return myValue;

    }

}
