package org.googlefoobar.level4;

import java.util.ArrayList;
import java.util.List;

public class FreeTheBunnyPrisoners 
{
	public static void main(String[] args) 
	{
		int[][] ans = solution(9,2);
		
		for(int i=0; i< ans.length; i++)
		{
			for(int j=0; j<ans[i].length; j++)
			{
				System.out.println(ans[i][j]);
			}
		}
	}
	
	public static int[][] solution(int num_buns, int num_required) 
    {
		if(num_required == 0)
		{
			return null;
		}
		else if(num_required == 1)
        {
        	return oneRequired(num_buns);
        }
        else if(num_buns == num_required)
        {
        	return distinctSolution(num_buns);
        }
        else if(num_required > num_buns)
        {
        	return null;
        }
        else
        {
        	return combinationSolution(num_buns,num_required);
        }
	}

	private static int[][] combinationSolution(int num_buns, int num_required) 
	{
		int numerator 			  = 1;
		int denomenator 		  = 1;
		int difference 			  = num_buns-num_required+1;
		
		for(int i=num_buns; i > 0; i--)
		{
			numerator *= i;
		}
		//System.out.println(numerator);
		
		for(int i=num_required - 1; i> 0 ;i--)
		{
			denomenator *= i;
		}
		//System.out.println(denomenator);
		
		for(int i=difference ; i> 0 ; i--)
		{
			denomenator *= i;
		}
		//System.out.println(denomenator);
		
		
		int distinctKeys  = numerator/denomenator ;
		//System.out.println(distinctKeys);
		
		int copyPerKey    = num_buns-num_required+1;
		//System.out.println(copyPerKey);
		
		int secondDimensionSize = distinctKeys*copyPerKey/num_buns;
		//System.out.println(secondDimensionSize);
		
		int[][] sol = new int[num_buns][secondDimensionSize];
		
		List<List<Integer>> lstKeys = new ArrayList<>();
		
		for(int i=0; i<num_buns; i++)
		{
			List<Integer> keys = new ArrayList<>();
			lstKeys.add(keys);
		}
		
		int[] keyRows = new int[copyPerKey];
		for(int i=1; i<=copyPerKey; i++)
		{
			keyRows[i-1] = i;
			//System.out.println("keyRows is:"+i+" and : "+i);
		}
		
		for(int i=0; i<distinctKeys; i++)
		{
			for(int j=0; j< keyRows.length; j++)
			{
				int row = keyRows[j];
				//System.out.println("Row is: "+row);
				lstKeys.get(row-1).add(i);
			}
			
			if(i != distinctKeys-1)
			{
				keyRows = getNextValidSequence(keyRows,num_buns);
			}
		}
		
		for(int i=0; i<lstKeys.size(); i++)
		{
			for(int j=0; j<lstKeys.get(i).size(); j++)
			{
				sol[i][j] = lstKeys.get(i).get(j);
			}
		}
		
		return sol;
	}

	private static int[] getNextValidSequence(int[] keyRows, int limit) 
	{
		for(int i= keyRows.length-1; i>=0 ; i--)
		{
			if(keyRows[i] < limit-(keyRows.length-1-i))
			{
				int correctVal = keyRows[i] + 1;
				keyRows[i] = correctVal;
				//System.out.println("i is: "+i+" and correctVal is: "+correctVal);
				i++;
				
				while(i < keyRows.length)
				{
					keyRows[i] = keyRows[i-1]+1;
					//System.out.println("i is: "+i+" and correctVal is: "+keyRows[i]);
					i++;
				}
				return keyRows;
			}
		}
		
		return keyRows;
	}

	private static int[][] oneRequired(int num_buns) 
	{
		int[][] sol = new int[num_buns][1];
		return sol;
	}
	
	private static int[][] distinctSolution(int num_buns) 
	{
		int[][] sol = new int[num_buns][1];
		
		for(int i=0; i< sol.length; i++)
		{
			sol[i][0] = i;
		}
		
		return sol;
	}
}
