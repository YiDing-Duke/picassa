package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expression.Color;
import expression.Div;
import expression.Exp;
import expression.Minus;
import expression.Mod;
import expression.Mul;
import expression.Neg;
import expression.Plus;

public class CommandParser implements ExpressionParser {
	private int myCurrentPosition;
    private String myInput;
    private static final Pattern PAREN_EXPRESSION_BEGIN_REGEX =
            Pattern.compile("\\(([a-z]+)"); 
	@Override
	public Expression parseExpression() {
		Expression result;
    	ArrayList<Expression> list=new ArrayList<Expression>();
        Matcher expMatcher = PAREN_EXPRESSION_BEGIN_REGEX.matcher(myInput);
        expMatcher.find(myCurrentPosition);
        String commandName = expMatcher.group(1);
        myCurrentPosition = expMatcher.end();
        skipWhiteSpace();
        
            if(commandName.equals("div")) {
                Expression left = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
                myCurrentPosition += left.getPosition();
                Expression right = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
                myCurrentPosition += left.getPosition();
                list.add(left);

                list.add(right);

                result= new Div(list);
            }
            else if(commandName.equals("exp")){
                Expression left = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
                myCurrentPosition += left.getPosition();
                Expression right = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
                myCurrentPosition += left.getPosition();
                list.add(left);

                list.add(right);
                result=new Exp(list);
            }
            else if(commandName.equals("plus")){
            	System.out.println(myInput.substring(myCurrentPosition));

                Expression left = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));

                myCurrentPosition += left.getPosition();
                Expression right = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
                myCurrentPosition += left.getPosition();
                list.add(left);

                list.add(right);
                result=new Plus(list);
            }
            else if(commandName.equals("minus")){
                Expression left = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
                myCurrentPosition += left.getPosition();
                Expression right = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
                myCurrentPosition += left.getPosition();
                list.add(left);

                list.add(right);
                result= new Minus(list);
            }
            else if(commandName.equals("mod")){
                Expression left = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
                myCurrentPosition += left.getPosition();
                Expression right = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
                myCurrentPosition += left.getPosition();
                list.add(left);

                list.add(right);
                result= new Mod(list);
            }
            else if(commandName.equals("mul")){
                Expression left = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
                myCurrentPosition += left.getPosition();
                Expression right = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
                myCurrentPosition += left.getPosition();
                list.add(left);

                list.add(right);
                result= new Mul(list);
            }
            else if(commandName.equals("neg")){
                Expression left = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
                myCurrentPosition += left.getPosition();


                result= new Neg(list);
            }
            else if(commandName.equals("color")){
            //	System.out.println(myInput.substring(myCurrentPosition));
                Expression left = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
            	//System.out.println(left.toString());

                myCurrentPosition +=  left.getPosition();
            	System.out.println(left.getPosition());

            	System.out.println(myCurrentPosition);
                Expression mid = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
            //	System.out.println(mid.toString());

                myCurrentPosition += mid.getPosition();
            	System.out.println(myCurrentPosition);

            //	System.out.println(myInput.substring(myCurrentPosition));
                Expression right = ExpressionFactory.getAndParseExpression(myInput.substring(myCurrentPosition));
           // 	System.out.println(right.toString());

                myCurrentPosition += right.getPosition();
            	System.out.println(myCurrentPosition);

           // 	System.out.println(myInput.substring(myCurrentPosition));
                list.add(left);
                list.add(mid);
                list.add(right);
                result= new Color(list);
            }
            else throw new ParserException("Expected close paren, instead found " +
                    myInput.substring(myCurrentPosition));
        
        skipWhiteSpace();
    if (currentCharacter() == ')')
    {
    	myCurrentPosition++;
    	return result;
    }
        else 
        {
            throw new ParserException("Expected close paren, instead found " +
                                      myInput.substring(myCurrentPosition));
        }
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
	public int getPosition() {

		return myCurrentPosition;
	}

}
