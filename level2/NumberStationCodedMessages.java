import java.util.HashMap;
import java.util.Map;

public class NumberStationCodedMessages 
{
	public static void main(String[] args)
	{
		int[] l = {4, 3, 5, 7,8};
		int[] ans = solution(l,12);
		
		for(int i=0; i< ans.length; i++)
		{
			System.out.println(ans[i]);
		}
	}
	
	 public static int[] solution(int[] l, int t) 
	 {
		 Map<Integer,Integer> sums = new HashMap<>();
		 
		 int sum = 0;
		 
		 for(int i=0; i< l.length; i++)
		 {
			 sum+= l[i];
			 sums.put(sum, i);
		 }
		 
		 int[] ans = new int[2];
		 
		 int currentSum = 0;
		 for(int i=0; i< l.length; i++)
		 {
			 int currentElement = l[i];
			 currentSum += currentElement;
			 
			 int current = currentSum - currentElement;
			 int target = current + t;
			 
			 if(sums.containsKey(target))
			 {
				int secondIndex = sums.get(target);
				ans[0] = i;
				ans[1] = secondIndex;
				return ans;
			 }
		 }
		 
		 ans[0] = -1;
		 ans[1] = -1;
		 return ans;
	 }
}
