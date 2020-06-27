package org.googlefoobar.level5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DisorderlyEscape 
{
	public static void main(String[] args) 
	{
		String sol = solution(2,3,4);
		System.out.println(sol);
	}
	
	static List<List<Integer>> widthPartitions = new ArrayList<>();
	static List<List<Integer>> heightPartitions = new ArrayList<>();
	
	public static String solution(int w, int h, int s) 
    {
		if(w == h)
		{
			getPartitions(w,w,"","wh");
		}
		else
		{
			getPartitions(w,w,"","w");
			getPartitions(h,h,"","h");
		}
		
		/*for(int i=0; i< widthPartitions.size(); i++)
		{
			System.out.println(widthPartitions.get(i));
		}*/
		//System.out.println("heightPartitions is: "+heightPartitions);
		
		BigInteger result = BigInteger.ZERO;
		for(int i=0; i<widthPartitions.size(); i++)
		{
			List<Integer> lstWidth = widthPartitions.get(i);
			
			for(int j=0; j< heightPartitions.size(); j++)
			{
				BigInteger val = cycleCount(widthPartitions.get(i),w).multiply(cycleCount(heightPartitions.get(j),h));
				//System.out.println("val is: "+val);
				
				List<Integer> lstHeight = heightPartitions.get(j);
				//int sum          = 0;
				
				BigInteger sum          = BigInteger.ZERO;
				
				for(int k=0; k<lstWidth.size(); k++)
				{
					for(int l=0; l< lstHeight.size(); l++)
					{
						
						//System.out.println("gcd is: "+GCD(lstWidth.get(k),lstHeight.get(l)));
						sum = sum.add(BigInteger.valueOf(GCD(lstWidth.get(k),lstHeight.get(l))));
						//System.out.println("sum is: "+sum);
					}
				}
				
				//System.out.println(lstWidth);
				//System.out.println(lstHeight);
				//System.out.println("sum is: "+sum);
				BigInteger finalValue = val.multiply(expo(s,sum));
				//System.out.println("finalValue is: "+finalValue);
				result = result.add(finalValue);
				//System.out.println("result is: "+result);
			}
		}
		
		result = result.divide((fact(w).multiply(fact(h))));
		//System.out.println("before result is: "+result);
		
		return String.valueOf(result);
    }

	private static BigInteger expo(int s, BigInteger sum) 
	{
		BigInteger result = BigInteger.ONE;
		BigInteger base   = BigInteger.valueOf(s);
		
		for(BigInteger i= BigInteger.ONE; i.compareTo(sum) <= 0; i=i.add(BigInteger.ONE))
		{
			result = result.multiply(base);
		}
		
		return result;
	}

	private static BigInteger cycleCount(List<Integer> list, int val) 
	{
		BigInteger result = fact(val);
		
		Map<Integer,Integer> counts = new HashMap<>();
		
		for(int i=0; i< list.size(); i++)
		{
			int key = list.get(i);
			
			int keyCount = counts.getOrDefault(key, 0);
			
			counts.put(key, keyCount+1);
		}
		
		Set<Integer> keys = counts.keySet();
		
		for(Integer key : keys)
		{
			BigInteger bIKey = BigInteger.valueOf(key);
			int keyCount = counts.get(key);
			
			BigInteger factValue = fact(keyCount);
			
			BigInteger sum = BigInteger.ONE;
			
			for(int i=1; i<= keyCount; i++)
			{
				sum = sum.multiply(bIKey);
			}
			
			sum = sum.multiply(factValue);
			result = result.divide(sum);
		}
		
		//System.out.println("cc is: "+result);
		return result;
	}

	public static void getPartitions(int n, int max, String prefix,String requestType) 
	{
        if (n == 0) 
        {
            //System.out.println(prefix);
            
            String[] strArray = prefix.split(" ");
            List<Integer> lst = new ArrayList<Integer>();
            
            for(String s: strArray)
            {
            	if(!s.isEmpty())
            	{
            		int i = Integer.valueOf(s);
                	lst.add(i);
            	}
            }
            
            if("wh".equals(requestType))
            {
            	widthPartitions.add(lst);
            	heightPartitions.add(lst);
            }
            else if("w".equals(requestType))
            {
            	widthPartitions.add(lst);
            }
            else if("h".equals(requestType))
            {
            	heightPartitions.add(lst);
            }
            
            return ;
        }

        for (int i = Math.min(max, n); i >= 1; i--) 
        {
        	getPartitions(n-i, i, prefix + " " + i,requestType);
        }
		return ;
    }
	
	
	private static BigInteger fact(int a) 
	{
	        BigInteger result = BigInteger.ONE;
	        
	        while (a > 1) 
	        {
	            result = result.multiply(BigInteger.valueOf(a));
	            a--;
	        }
	        
	        return result;
	 }
	
	 private static Integer GCD(int a,int b)
	 {
		 if(a < b)
		 {
			 return GCD(b,a);
		 }
		 
		 int mod = a%b;
		 
		 if(mod == 0)
		 {
			 return b;
		 }
		 else
		 {
			 return GCD(b,mod);
		 }
	  }

}
