package org.googlefoobar.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PleasePasstheCodedMessages 
{
	public static void main(String[] args) 
	{
		int[] l = {2,2}; 
		
		System.out.println(solution(l));
	}
	
	public static int solution(int[] l)
	{
		List<Integer> zeroRemainder = new ArrayList<>();
		List<Integer> oneRemainder  = new ArrayList<>();
		List<Integer> twoRemainder  = new ArrayList<>();
		
		for(int i=0; i< l.length; i++)
		{
			int val = l[i];
			
			if(val%3 == 0)
			{
				zeroRemainder.add(val);
			}
			else if(val%3 == 1)
			{
				oneRemainder.add(val);
			}
			else
			{
				twoRemainder.add(val);
			}
		}
		
		if(oneRemainder.size()%3 == twoRemainder.size()%3)
		{
			zeroRemainder.addAll(oneRemainder);
			zeroRemainder.addAll(twoRemainder);
		}
		else
		{
			Collections.sort(oneRemainder);
			Collections.sort(twoRemainder);
			
			int common 			= Math.min(oneRemainder.size(),twoRemainder.size());
			int commonElements  = 2*common;
			
			int oneRemainderQuotient = (oneRemainder.size()/3) * 3;
			int oneRemainderModulus  = oneRemainder.size()%3;
			
			int twoRemainderQuotient = (twoRemainder.size()/3) * 3;
			int twoRemainderModulus  = twoRemainder.size()%3;
			
			
			int quotient = Math.max(oneRemainderQuotient, twoRemainderQuotient);
			int modulus  = Math.min(oneRemainderModulus, twoRemainderModulus);
			int elements = quotient+modulus;
			
			if(commonElements > elements)
			{
				for(int i=oneRemainder.size()-1; i >= oneRemainder.size() - common; i--)
				{
					zeroRemainder.add(oneRemainder.get(i));
				}
				
				for(int i=twoRemainder.size()-1; i >= twoRemainder.size() - common; i--)
				{
					zeroRemainder.add(twoRemainder.get(i));
				}
			}
			else
			{
				int oneLimit = oneRemainderQuotient+modulus;
				for(int i=oneRemainder.size()-1; i >= oneRemainder.size() - oneLimit; i--)
				{
					zeroRemainder.add(oneRemainder.get(i));
				}
				
				int twoLimit = twoRemainderQuotient+modulus;
				for(int i=twoRemainder.size()-1; i >= twoRemainder.size() - twoLimit; i--)
				{
					zeroRemainder.add(twoRemainder.get(i));
				}
			}
		}
		
		if(zeroRemainder.size() < 1)
		{
			return 0;
		}
		
		Collections.sort(zeroRemainder);
		
		int ans = 0;
		for(int i=zeroRemainder.size()-1; i>= 0; i--)
		{
			ans = ans * 10 + zeroRemainder.get(i);
		}
		
		return ans;
	}

}
