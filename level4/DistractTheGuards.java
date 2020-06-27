package org.googlefoobar.level4;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DistractTheGuards 
{
	public static void main(String[] args) 
	{
		System.out.println(LocalDateTime.now());
		int[] bananaList = {1,1,1,1000};
		System.out.println(solution(bananaList));
		System.out.println(LocalDateTime.now());
	}
	
	public static int solution(int[] banana_list) 
	{
        Map<Integer,Set<Integer>> possiblePairs = new ConcurrentHashMap<>();
        
        int lowest 			= Integer.MAX_VALUE;
		int lowestIndex 	= 0;
		boolean matchExist  = false;
        
		for(int i=0; i<banana_list.length; i++)
        {
        	Set<Integer> indexPairs = new HashSet<>();
			
        	for(int j = 0; j< banana_list.length; j++)
            {
            	if(i == j)
            	{
            		continue;
            	}
				
            	if(isUnlimitedLoopable(banana_list[i],banana_list[j]))
            	{
            		indexPairs.add(j);
            	}
             }
        	
        	if(indexPairs.size() == 0)
        	{
        		continue ;
        	}
        	
        	if(indexPairs.size() < lowest)
        	{
        		lowest 		= indexPairs.size();
        		lowestIndex = i;
        		matchExist  = true;
        	}
			
			possiblePairs.put(i, indexPairs);
		}
		
		//System.out.println(possiblePairs);
		
		if(!matchExist)
		{
			return banana_list.length;
		}
		
		Set<Integer> usedKey = new HashSet<>();
		
		while(true)
		{
			Set<Integer> lstMatches = possiblePairs.get(lowestIndex);
			
			//System.out.println("Operation with: "+lowestIndex+" size is: "+lstMatches.size());
			
			int lowestMatchingSize  = Integer.MAX_VALUE;
			int lowestMatchingIndex = 0;
			boolean matchFound 		= false;		
			
			for(Integer i: lstMatches)
			{
				int size = possiblePairs.get(lowestIndex).size();
				
				if(size < lowestMatchingSize)
				{
					lowestMatchingSize  = size;
					lowestMatchingIndex = i;
					matchFound = true;
				}
			}
			
			if(matchFound)
			{
				usedKey.add(lowestIndex);
				usedKey.add(lowestMatchingIndex);
				
				//System.out.println("Match Found: "+lowestIndex);
				//System.out.println("Match Found: "+lowestMatchingIndex);
				
				possiblePairs.remove(lowestIndex);
				possiblePairs.remove(lowestMatchingIndex);
				
				Set<Integer> existingKeys = possiblePairs.keySet();
				//System.out.println("Existing keys is: "+existingKeys);
				
				int newLowest 		= Integer.MAX_VALUE;
				int newLowestIndex  = 0;
				boolean pairExist   = false;
				
				for(Integer i : existingKeys)
				{
					Set<Integer> existingKeyPairs = possiblePairs.get(i);
					
					if(existingKeyPairs.contains(lowestIndex))
					{
						existingKeyPairs.remove(lowestIndex);
					}
					
					if(existingKeyPairs.contains(lowestMatchingIndex))
					{
						existingKeyPairs.remove(lowestMatchingIndex);
					}
					
					if(existingKeyPairs.size() == 0)
					{
						possiblePairs.remove(i);
					}
					else
					{
						possiblePairs.put(i, existingKeyPairs);
						
						if(existingKeyPairs.size() < newLowest)
						{
							newLowest 	   =  existingKeyPairs.size();
							newLowestIndex =  i;
							pairExist      = true;
						}
					}
				}
				
				if(pairExist)
				{
					lowest 		= newLowest;
					lowestIndex = newLowestIndex;
				}
				else
				{
					//System.out.println("Break because PairDoesNotExist");
					break;
				}
			}
			else
			{
				//System.out.println("Break because NoMatchFound");
				break;
			}
		}
		
		return banana_list.length - usedKey.size() ;
    }
	
	public static int gcd(int x,int y)
	{
		BigInteger gcd = BigInteger.valueOf(x).gcd(BigInteger.valueOf(y));
		int integerGCD   = gcd.intValue();
		return integerGCD;
	}
	
	public static boolean isUnlimitedLoopable(int x,int y)
	{
		int sum = x + y;
		int gcd = gcd(x,y) ;
		
		int val = sum/gcd;
		
		if((val & val-1) != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

}
