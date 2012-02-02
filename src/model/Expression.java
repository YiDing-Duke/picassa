package model;

import java.util.ArrayList;

import model.ParserException.Type;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical 
 * functions and the leaves represent constant values.
 *
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
public abstract class Expression
{
    protected RGBColor myValue;
    protected String myCommand;
    protected ArrayList<Expression> myOperands;


    /**
     * Create expression representing the given constant value
     */
    public Expression (RGBColor value)
    {
        myValue = value;

    }

    public Expression (ArrayList<Expression> list)
    {

    	myOperands = list;

    }

    /**
     * Create expression representing the given operation between the
     * two given sub-expressions.
     */

    


    /**
     * @return value of expression
     */
    

    public  abstract RGBColor evaluate (double x,double y);


 /*       if (myCommand == null)
        {
            return myValue;
        }
        else
        {
            if (myCommand.equals("plus"))
                return ColorCombinations.add(myOperand1.evaluate(), myOperand2.evaluate());
            else if (myCommand.equals("minus"))
                return ColorCombinations.subtract(myOperand1.evaluate(), myOperand2.evaluate());
            else if (myCommand.equals("mul"))
                return ColorCombinations.multiply(myOperand1.evaluate(), myOperand2.evaluate());
            else if (myCommand.equals("div"))
                return ColorCombinations.divide(myOperand1.evaluate(), myOperand2.evaluate());
            else if (myCommand.equals("mod"))
            	return ColorCombinations.modulus(myOperand1.evaluate(), myOperand2.evaluate());
            else if (myCommand.equals("exp"))
            	return ColorCombinations.exponent(myOperand1.evaluate(), myOperand2.evaluate());
            else if (myCommand.equals("neg"))
            	return ColorCombinations.negtive(myOperand1.evaluate());
            else if (myCommand.equals("minus"))
                return ColorCombinations.subtract(myOperand1.evaluate(), myOperand2.evaluate());
            else if (myCommand.equals("color"))
                return ColorCombinations.color(myOperand1.evaluate(), myOperand2.evaluate(),myOperand3.evaluate());
            
            
            
            else
                throw new ParserException("Unknown Command " + myCommand, Type.UNKNOWN_COMMAND);
        }
    }
*/

    /**
     * @return string representation of expression
     */
    public String toString ()
    {
        StringBuffer result = new StringBuffer();
        if (myCommand == null)
        {
            result.append(myValue); 
        }
        else
        {
            result.append("(");
            result.append(" " + myCommand + " ");
            for(Expression k: myOperands)
            {
            result.append(k.toString());  
            }
            result.append(")");
        }
        return result.toString();
    }
    
    public abstract boolean isThisKindOfExpression(String input);
    public abstract Expression parseExpression();
    public String getMyCommand(){
    	return myCommand;
    }
	public abstract int getPosition();

}
