package org.googlefoobar.level3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindTheAccessCodes 
{
	public static void main(String[] args)
	{
		int[] l = {1,2,3,4,5,6};
		System.out.println(solution(l));
	}
	
	public static int solution(int[] l) 
    {
        if(l.length < 3)
        {
        	return 0;
        }
        
        Map<Integer,List<Integer>> multiplesMap = new HashMap<>();
        
        
        for(int i=0; i< l.length; i++)
        {
        	List<Integer> multiples = new ArrayList<>();
        	int key = i;
        	
        	for(int j= i+1; j< l.length; j++)
        	{
        		//System.out.println();
        		if(l[j]%l[i] == 0)
        		{
        			multiples.add(j);
        		}
        	}
        	multiplesMap.put(key, multiples);
        }
        
        //System.out.println(multiplesMap);
        
        int ans = 0;
        for(int i=0; i< l.length; i++)
        {
        	List<Integer> firstMultiples = multiplesMap.get(i);
        	
        	for(int j=0; j< firstMultiples.size(); j++)
        	{
        		List<Integer> secondMultiples = multiplesMap.get(firstMultiples.get(j));
        		ans += secondMultiples.size();
        	}
        }
		
		return ans;
    }

}
