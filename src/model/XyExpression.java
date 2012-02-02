package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expression.X;
import expression.Y;

public class XyExpression extends Expression{

	public XyExpression(ArrayList<Expression> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	private int myCurrentPosition;
    private String myInput;
    private static final Pattern XY_EXPRESSION_BEGIN_REGEX =
            Pattern.compile("(x|y)");
    
	public boolean isThisKindOfExpression (String input)
    {
		myInput = input;
        Matcher xyMatcher =
        	XY_EXPRESSION_BEGIN_REGEX.matcher(myInput.substring(myCurrentPosition));
        return xyMatcher.lookingAt();
    }
	
	public Expression parseExpression() {
		// TODO Auto-generated method stub
    	Matcher expMatcher = XY_EXPRESSION_BEGIN_REGEX.matcher(myInput);
        expMatcher.find(myCurrentPosition);
        String commandName = expMatcher.group(1);
        myCurrentPosition = expMatcher.end();

     //   Expression left = parseExpression();
   //     Expression mid = parseExpression();

  //      Expression right = parseExpression();
        skipWhiteSpace();
       if(commandName.equals("x"))
            return new X(null);
       else return new Y(null);

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
			return new ExpressionFactory(new XyExpression(null));
		}

		@Override
		public int getPosition() {
			// TODO Auto-generated method stub
			return myCurrentPosition;
		}


		

    
}
