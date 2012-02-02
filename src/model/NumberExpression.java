package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expression.Number;
import expression.X;
import expression.Y;

public class NumberExpression extends Expression{
	public NumberExpression(RGBColor value) {
		super(value);
		// TODO Auto-generated constructor stub
	}


	private int myCurrentPosition;
    private String myInput;
    private static final Pattern DOUBLE_REGEX =
            Pattern.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");
    
	public boolean isThisKindOfExpression (String input)
    {
		myInput = input;
        Matcher doubleMatcher =
                DOUBLE_REGEX.matcher(myInput.substring(myCurrentPosition));
            return doubleMatcher.lookingAt();
    }
	
	public Expression parseExpression() {
		Matcher doubleMatcher = DOUBLE_REGEX.matcher(myInput);
        doubleMatcher.find(myCurrentPosition);
        String numberMatch =
            myInput.substring(doubleMatcher.start(), doubleMatcher.end());
        myCurrentPosition = doubleMatcher.end();
        skipWhiteSpace ();
     //   System.out.println(getPosition());
        // this represents the color gray of the given intensity
        double value = Double.parseDouble(numberMatch);
        RGBColor gray = new RGBColor(value);
        Number left = new Number(gray);

        return left;

	}
	
	 private void skipWhiteSpace ()
	    {
	        while (notAtEndOfString() && Character.isWhitespace(currentCharacter()))
	        {
	            myCurrentPosition++;
	        }
	    }

	    private char currentCharacter ()
	    {
	        return myInput.charAt(myCurrentPosition);
	    }

	    private boolean notAtEndOfString ()
	    {
	        return myCurrentPosition < myInput.length();
	    }

		@Override
		public RGBColor evaluate(double x, double y) {
			// TODO Auto-generated method stub
			return null;
		}

		public static ExpressionFactory getFactory()
		{
			return new ExpressionFactory(new NumberExpression(null));
		}

		@Override
		public int getPosition() {
			// TODO Auto-generated method stub
			return myCurrentPosition;
		}
}
