 package model;

import java.util.ArrayList;
import java.util.List;

public class ExpressionFactory {
	
	Expression myExpression;
	public ExpressionFactory(Expression expression)
    {
        myExpression = expression;
    }
	public boolean isThisKindOfExpression(String input)
	{
		return myExpression.isThisKindOfExpression(input);
	}
	public Expression ParseExpression(String input)
	{
		return myExpression.parseExpression();
	}
	
public static Expression getAndParseExpression(String input){
    	
    	List<ExpressionFactory> expressionKinds = new ArrayList<ExpressionFactory>();
    	expressionKinds.add(CommandExpression.getFactory());
    	expressionKinds.add(XyExpression.getFactory());
    	expressionKinds.add(NumberExpression.getFactory());
    	for(ExpressionFactory expressionkind: expressionKinds)
    	{
    		if(expressionkind.isThisKindOfExpression(input))
    			return expressionkind.ParseExpression(input);
    	}
    	return null;
    	
    }

}
