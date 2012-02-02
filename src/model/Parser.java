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
import expression.Number;
import expression.Plus;
import expression.X;
import expression.Y;


/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Due to the nature of the language being parsed, a recursive descent parser 
 * is used 
 *   http://en.wikipedia.org/wiki/Recursive_descent_parser
 *   
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class Parser
{
    // double is made up of an optional negative sign, followed by a sequence 
    // of one or more digits, optionally followed by a period, then possibly 
    // another sequence of digits
    private static final Pattern DOUBLE_REGEX =
        Pattern.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");
    // expression begins with a left paren followed by the command name, 
    // which is a sequence of alphabetic characters
    private static final Pattern PAREN_EXPRESSION_BEGIN_REGEX =
            Pattern.compile("\\(([a-z]+)");
    private static final Pattern XY_EXPRESSION_BEGIN_REGEX =
            Pattern.compile("(x|y)");

    // different possible kinds of expressions

 /*   private static enum ParenType
    {
        PLUS,PAREN_EXPRESSION,XY_EXPRESSION,SIN_EXPRESSION,TRI_EXPRESSION
    }
*/
    // state of the parser
    private int myCurrentPosition;
    private String myInput;


    /**
     * Converts given string into expression tree.
     * 
     * @param input expression given in the language to be parsed
     * @return expression tree representing the given formula
     */
    public Expression makeExpression (String input)
    {
        myInput = input;
        Expression result = parseExpression();
        skipWhiteSpace();
        if (notAtEndOfString())
        {
            throw new ParserException("Unexpected characters at end of the string: " +
                                      myInput.substring(myCurrentPosition),
                                      ParserException.Type.EXTRA_CHARACTERS);
        }
        return result;
    }
//need subclass



    private Expression parseExpression ()
    {
    	skipWhiteSpace();
    	if (isNumber())          return parseNumber();
        if(isParenExpression())  return parseParenExpression();
        if(isXyExpression())     return parseXyExpression();

        else                     throw new ParserException("Unexpected Character " + currentCharacter());
    }


    private boolean isNumber ()
    {
        Matcher doubleMatcher =
            DOUBLE_REGEX.matcher(myInput.substring(myCurrentPosition));
        return doubleMatcher.lookingAt();
    }
    
	private boolean isXyExpression ()
    {
        Matcher xyMatcher =
        	XY_EXPRESSION_BEGIN_REGEX.matcher(myInput.substring(myCurrentPosition));
        return xyMatcher.lookingAt();
    }

/*	private boolean isSinExpression ()
    {
        Matcher doubleMatcher =
        	SIN_EXPRESSION_BEGIN_REGEX.matcher(myInput.substring(myCurrentPosition));
        return doubleMatcher.lookingAt();
    }
*/	
    private boolean isParenExpression ()
    {
        Matcher expMatcher =
        	PAREN_EXPRESSION_BEGIN_REGEX.matcher(myInput.substring(myCurrentPosition));
        return expMatcher.lookingAt();
    }
/*    
    private boolean isTriExpression ()
    {
        Matcher expMatcher =
        	TRI_EXPRESSION_BEGIN_REGEX.matcher(myInput.substring(myCurrentPosition));
        return expMatcher.lookingAt();
    }
*/    

// need subclass
    private Expression parseNumber ()
    {
        Matcher doubleMatcher = DOUBLE_REGEX.matcher(myInput);
        doubleMatcher.find(myCurrentPosition);
        String numberMatch =
            myInput.substring(doubleMatcher.start(), doubleMatcher.end());
        myCurrentPosition = doubleMatcher.end();
        // this represents the color gray of the given intensity
        double value = Double.parseDouble(numberMatch);
        RGBColor gray = new RGBColor(value);
        return new Number(gray);
    }
    
    private Expression parseXyExpression() {
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

/*    private double parseTriNumber ()
    {
        Matcher doubleMatcher = DOUBLE_REGEX.matcher(myInput);
        doubleMatcher.find(myCurrentPosition);
        String numberMatch =
            myInput.substring(doubleMatcher.start(), doubleMatcher.end());
        myCurrentPosition = doubleMatcher.end();
        // this represents the color gray of the given intensity
        return Double.parseDouble(numberMatch);
        
    }
*/
 /*   private Expression parseSinExpression() {
		// TODO Auto-generated method stub
    	Matcher expMatcher = SIN_EXPRESSION_BEGIN_REGEX.matcher(myInput);
        expMatcher.find(myCurrentPosition);
        String commandName = expMatcher.group(1);
        myCurrentPosition = expMatcher.end();

        Expression left = parseExpression();
        skipWhiteSpace();
        if (currentCharacter() == ')')
        {
            myCurrentPosition++;
            return new Neg(commandName, left);
        }
        else
        {
            throw new ParserException("Expected close paren, instead found " +
                                      myInput.substring(myCurrentPosition));
        }
	}
*/
    private Expression parseParenExpression ()
    {
    	Expression result;
    	ArrayList<Expression> list=new ArrayList<Expression>();
        Matcher expMatcher = PAREN_EXPRESSION_BEGIN_REGEX.matcher(myInput);
        expMatcher.find(myCurrentPosition);
        String commandName = expMatcher.group(1);
        myCurrentPosition = expMatcher.end();
        
        
            if(commandName.equals("div")) {
                Expression left = parseExpression();
                Expression right = parseExpression();
                list.add(left);
                list.add(right);

                result= new Div(list);
            }
            else if(commandName.equals("exp")){
                Expression left = parseExpression();
                Expression right = parseExpression();
                list.add(left);
                list.add(right);
                result=new Exp(list);
            }
            else if(commandName.equals("plus")){
                Expression left = parseExpression();
                Expression right = parseExpression();
                list.add(left);
                list.add(right);
                result=new Plus(list);
            }
            else if(commandName.equals("minus")){
                Expression left = parseExpression();
                Expression right = parseExpression();
                list.add(left);
                list.add(right);
                result= new Minus(list);
            }
            else if(commandName.equals("mod")){
                Expression left = parseExpression();
                Expression right = parseExpression();
                list.add(left);
                list.add(right);
                result= new Mod(list);
            }
            else if(commandName.equals("mul")){
                Expression left = parseExpression();
                Expression right = parseExpression();
                list.add(left);
                list.add(right);
                result= new Mul(list);
            }
            else if(commandName.equals("neg")){
                Expression left = parseExpression();
                list.add(left);
                result= new Neg(list);
            }
            else if(commandName.equals("color")){
                Expression left = parseExpression();
                Expression mid = parseExpression();
                Expression right = parseExpression();
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

/*	private Expression parseTriExpression() {
		Matcher expMatcher = TRI_EXPRESSION_BEGIN_REGEX.matcher(myInput);
        expMatcher.find(myCurrentPosition);
        String commandName = expMatcher.group(1);
        myCurrentPosition = expMatcher.end();
        
        Expression left = parseExpression();
        Expression mid = parseExpression();
        Expression right = parseExpression();
        
      
   //     Expression left = parseExpression();
   //     Expression mid = parseExpression();

     //   Expression right = parseExpression();
        skipWhiteSpace();
        if (currentCharacter() == ')')
        {
            myCurrentPosition++;
            return new Color(commandName, left, mid, right);

        }
        else
        {
            throw new ParserException("Expected close paren, instead found " +
                                      myInput.substring(myCurrentPosition));
        }
        
        
	}
	*/
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
}
