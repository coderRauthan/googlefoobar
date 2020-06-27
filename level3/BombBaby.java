package org.googlefoobar.level3;

import java.math.BigInteger;

public class BombBaby 
{
	public static void main(String[] args)
	{
		String x = "2";
		String y = "4";
		
		System.out.println(solution(x,y));
	}
	
	public static String solution(String x,String y) 
    {
		BigInteger currentM = new BigInteger(x);
		BigInteger currentF = new BigInteger(y);
		
		BigInteger step = new BigInteger("0");
		
		return findStep(currentM,currentF,step);
	}

	private static String findStep(BigInteger currentM, BigInteger currentF, BigInteger step) 
	{
		System.out.println("currentM is: "+currentM);
		System.out.println("currentF is: "+currentF);
		
		BigInteger minAllowed = new BigInteger("1");
		
		int isMImpossible = currentM.compareTo(minAllowed);
		int isFImpossible = currentF.compareTo(minAllowed);
		
		if(isMImpossible == -1)
		{
			return "impossible";
		}
		else if(isFImpossible == -1)
		{
			return "impossible";
		}
		else if(isMImpossible == 0)
		{
			BigInteger diff 		= currentF.subtract(minAllowed);
			step                    = step.add(diff);
			
			return step.toString();
		}
		else if(isFImpossible == 0)
		{
			BigInteger diff 		= currentM.subtract(minAllowed);
			step                    = step.add(diff);
			
			return step.toString();
		}
		else
		{
			if(currentM.compareTo(currentF) < 0)
			{
				BigInteger div = currentF.divide(currentM);
				BigInteger mod = currentF.mod(currentM);
				
				step           = step.add(div);
				
				return findStep(currentM,mod,step);
			}
			else if(currentM.compareTo(currentF) > 0)
			{
				BigInteger div = currentM.divide(currentF);
				BigInteger mod = currentM.mod(currentF);
				
				step           = step.add(div);
				
				return findStep(mod,currentF,step);
			}
			else
			{
				return "impossible";
			}
		}
	}

}
