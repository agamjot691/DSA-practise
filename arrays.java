
import java.util.*;
public class arrays{
// question -> merge sorted arrays without space  O(m + n )
public int nextgap(int gap){
    if( gap<=1){
        return 0 ; 
    }
    return (gap / 2) + (gap %2);
    
}


public void merge(int arr1[], int arr2[], int n, int m) {
   int i = 0  , j = 0 , gap = n + m ; 
   
   for(gap = nextgap(gap) ; gap >0 ; gap = nextgap(gap)){
      for( i = 0 ; i + gap < n ; i++){
          if(arr1[i] > arr1[i + gap]){
              int temp = arr1[i + gap];
              arr1[i + gap] = arr1[i];
              arr1[i] = temp;  
              
          }
          
          
      }
      
      for(j = gap > n ? gap - n :0 ; j < m && i < n ; j++ , i++){
          
          if( arr1[i] > arr2[j] ){
              
              int temp = arr1[i];
              arr1[i] = arr2[j];
              arr2[j] = temp;
              
          }
          
          
          
      }
      
      if( j < m ){
         for( j = 0 ; j + gap < m ; j++){
          if(arr2[j] > arr2[j + gap]){
              int temp = arr2[j + gap];
              arr2[j + gap] = arr2[j];
              arr2[j] = temp;  
              
          }
          
          
      } 
          
      }
      
       
       
   }
}



/// -> merge intervals important 

 public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals , (a, b) -> a[0] - b[0]);
       int start = intervals[0][0];
        int end  = intervals[0][1];
        ArrayList<int[]>list = new ArrayList<>();
        
        for( int[] i : intervals){
            
            if( end >= i[0]){
                
                end  = Math.max( end , i[1]);
            }
            else{
                list.add(new int[]{start , end });
                start = i[0];
                end = i[1];
                
                
            }
            
        }
        
        list.add(new int[]{start , end });
        
        return list.toArray(new int[list.size()][]);
    }
        
    // -> max stock buy and sell at most two times

    int maxtwobuysell(int arr[],int size) {
    int first_buy = Integer.MIN_VALUE;
      int first_sell = 0;
      int second_buy = Integer.MIN_VALUE;
      int second_sell = 0;
       
      for(int i=0;i<size;i++) {
         
          first_buy = Math.max(first_buy,-arr[i]);//we set prices to negative, so the calculation of profit will be convenient
          first_sell = Math.max(first_sell,first_buy+arr[i]);
          second_buy = Math.max(second_buy,first_sell-arr[i]);//we can buy the second only after first is sold
          second_sell = Math.max(second_sell,second_buy+arr[i]);
       
    }
     return second_sell;
}


    //-> next permuatation
     public void nextPermutation(int[] nums) {
        int i = nums.length -2 ; 
        while( i >=0 && nums[i] >= nums[i + 1]) i --;
        if( i >=0){
        int j = nums.length - 1;
        while(  nums[j] <= nums[i])j--;
        swap(nums , i  , j);
        }
        reverse( nums , i + 1 , nums.length - 1);
        
        
        
    }
    
    
    public void swap( int[] nums , int i , int j ){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        
    }
    public void reverse( int []nums , int i , int j){
        while( i < j){
            swap( nums , i ++ , j -- );
        }
        
        
    }

    ///-> count inversions


     static long merge( int l , int m , int r , long[] arr ){
        int i = 0 , j = 0 , k = l ;
        long left[] = Arrays.copyOfRange( arr , l , m + 1 );
        long swap = 0 ; 
         long right[] = Arrays.copyOfRange( arr , m + 1 , r + 1 );
        while( i < left.length && j < right.length  ){
            if(left[i]  <=right[j]){
                arr[k++] = left[i++];
                
            }
            else{
              arr[k++] = right[j++];  
              swap = (m + 1 ) - ( l + i);    
            }
            
            
            
            
            
        }
        while( i < left.length ){
            arr[k++] = left[i++];
        }
        while( j < right.length ){
            arr[k++] = right[j++];
        }
        
        return swap;
        
        
    }
    
    
    static long mergesortAndCount( int l , int r , long arr[]){
        long count  = 0 ; 
        if( l < r){
            
            int m  = (l + r)/2;
            
            count +=mergesortAndCount( l , m , arr);
            count +=mergesortAndCount( m + 1 , r , arr);
            
            count += merge( l , m , r , arr);
            
            
            
            
        }
        
        
        return count ; 
        
        
    }
    
    
    static long inversionCount(long arr[], long N)
    {
      return  mergesortAndCount( 0 , arr.length - 1 , arr );
    }


    // count pairs with given sum (not two sum)

      int getPairsCount(int[] arr, int n, int k) {
    HashMap<Integer,Integer> m = new HashMap<>();
    int count = 0;
    for (int i = 0; i < n; i++) {
        if (m.containsKey(k - arr[i])) {
            count += m.get(k - arr[i]);
        }
        if(m.containsKey(arr[i])){
            m.put(arr[i], m.get(arr[i])+1);
        }
        else{
            m.put(arr[i], 1);
        }
    }
    return count;
    }
    // common elements
     ArrayList<Integer> commonElements(int ar1[], int ar2[], int ar3[], int n1, int n2, int n3) 
    {
      
        // Initialize starting indexes for ar1[], ar2[] and
        // ar3[]
        int i = 0, j = 0, k = 0;
          ArrayList<Integer> list = new ArrayList<>();
        // Iterate through three arrays while all arrays
        // have elements
        while (i < ar1.length && j < ar2.length
               && k < ar3.length) {
            // If x = y and y = z, print any of them and
            // move ahead in all arrays
        
            if (ar1[i] == ar2[j] && ar2[j] == ar3[k]) {
                if(!list.contains(ar1[i])){
                list.add(ar1[i]);
                }
                i++;
                j++;
                k++;
            }

            // x < y
            else if (ar1[i] < ar2[j])
                i++;

            // y < z
            else if (ar2[j] < ar3[k])
                j++;

            // We reach here when x > y and z < y, i.e., z
            // is smallest
            else
                k++;
        }
        return list;

}

// sub array with sum 0

static boolean findsum(int arr[],int n)
    {
      HashSet<Integer> set = new HashSet<>();
      int sum= 0 ; 
      for( int i = 0 ; i < arr.length ; i ++){
         sum+=arr[i];
         if(sum == 0 || arr[i] == 0 || set.contains(sum)){
             return true;
         }
          
          set.add(sum);
      }
      return false;
    }
// maximum product subarray 
   long maxProduct(int[] arr, int n) {
        
        long mxv = arr[0];
        long mnv = arr[0];
        long mp = arr[0];
        for( int i = 1 ; i <  n ; i ++){
            
            if( arr[i] < 0 ){
                long temp = mxv ; 
                mxv = mnv ; 
                mnv = temp;
            }
            mxv = Math.max( arr[i] , arr[i] * mxv);
            mnv = Math.min( arr[i] , arr[i] * mnv);
            mp = Math.max( mxv , mp);
            
        }
        
       return mp; 
        
    }


// longest subsequence present in an array O(n) approaches  space O(n)
   // approach 1 using hashset
	static int findLongestConseqSubseq1(int arr[], int n)
	{
	   int ans = 0 ;
	   HashSet<Integer> set = new HashSet<>();
	   
	   for( Integer i : arr){
	      set.add(i);    
	   }
	   for( int i = 0 ; i <  n ; i ++){
	     if(!set.contains(arr[i] - 1)){
	       int j = arr[i];
	       while( set.contains(j) ){
	           j++;
	       }
	       if( ans < j - arr[i]){
	           ans = j - arr[i];
	       }
	       
	     }
	   
	       
	       
	       
	       
	   }
	   return ans;
	}

   //approach 2 using priorityqueue


    static int findLongestConseqSubseq(int arr[], int N)
    {

        PriorityQueue<Integer> pq
            = new PriorityQueue<Integer>();
        for (int i = 0; i < N; i++) 
        {
            // adding element from 
            // array to PriorityQueue
            pq.add(arr[i]);
        }
        
        // Storing the first element 
        // of the Priority Queue
        // This first element is also 
        // the smallest element
        int prev = pq.poll();
        
        // Taking a counter variable with value 1
        int c = 1;
        
        // Storing value of max as 1
        // as there will always be
        // one element
        int max = 1;

        for (int i = 1; i < N; i++) 
        {
            // check if current peek 
            // element minus previous
            // element is greater then 
            // 1 This is done because
            // if it's greater than 1 
            // then the sequence
            // doesn't start or is broken here
            if (pq.peek() - prev > 1) 
            {
                // Store the value of counter to 1
                // As new sequence may begin
                c = 1;
                
                // Update the previous position with the
                // current peek And remove it
                prev = pq.poll();
            }
            
            // Check if the previous
            //  element and peek are same
            else if (pq.peek() - prev == 0) 
            {
                // Update the previous position with the
                // current peek And remove it
                prev = pq.poll();
            }
            // if the difference 
            // between previous element and peek is 1
            else 
            {
                // Update the counter
                // These are consecutive elements
                c++;
                 
                // Update the previous position
                //  with the current peek And remove it
                prev = pq.poll();
            }

            // Check if current longest 
            // subsequence is the greatest
            if (max < c) 
            {
                // Store the current subsequence count as
                // max
                max = c;
            }
        }

        return max;
    }
//trappingWater
    
    
 static long trappingWater(int arr[], int n) { 
             // left[i] contains height of tallest bar to the
        // left of i'th bar including itself
        int left[] = new int[n];

        // Right [i] contains height of tallest bar to
        // the right of ith bar including itself
        int right[] = new int[n];

        // Initialize result
        long water = 0;

        // Fill left array
        left[0] = arr[0];
        for (int i = 1; i < n; i++)
            left[i] = Math.max(left[i - 1], arr[i]);

        // Fill right array
        right[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--)
            right[i] = Math.max(right[i + 1], arr[i]);

        // Calculate the accumulated water element by element
        // consider the amount of water on i'th bar, the
        // amount of water accumulated on this particular
        // bar will be equal to min(left[i], right[i]) - arr[i] .
        for (int i = 0; i < n; i++)
            water += Math.min(left[i], right[i]) - arr[i];

        return water;
    }
  

//  

    public long findMinDiff (ArrayList<Long> a, long n, long m)
    {
       Collections.sort(a);
       long sum  = (int)1e9;
       for( int i = 0 ; i + m - 1< n ; i ++){
          sum = Math.min( sum , ( a.get((int)(i + m - 1) ) - a.get((int)(i )))); 
       }
       return sum ; 
    }



//smallest subarray sum with given value

public int minSubArrayLen(int target, int[] nums) {
    int start = 0 , minlength = Integer.MAX_VALUE ; 
      int sum = 0 ; 
      for( int end = 0 ; end < nums.length ; end++){
          sum +=nums[end];
          while(sum >=target ){
              
              minlength = Math.min( minlength  , end - start + 1);
              sum -= nums[start++];
              
          }
          
      }
      
      return minlength == Integer.MAX_VALUE ? 0 : minlength;
      
  }

  public static int minSwap(int arr[], int n, int k) {
 
    // Find count of elements which are
    // less than equals to k
    int count = 0;
    for (int i = 0; i < n; i++)
    if (arr[i] <= k)
        ++count;
 
    // Find unwanted elements in current
    // window of size 'count'
    int bad = 0;
    for (int i = 0; i < count; i++)
    if (arr[i] > k)
        ++bad;

    // Initialize answer with 'bad' value of
    // current window
    int ans = bad;
    for (int i = 0, j = count; j < n; i++, j++) {
 
    // Decrement count of previous window
    if (arr[i] > k)
        --bad;
 
    // Increment count of current window
    if (arr[j] > k)
        ++bad;
 
    // Update ans if count of 'bad'
    // is less in current window
    ans = Math.min(ans, bad);
    }
    return ans;
    
}


// median of two sorted arrays of unequal lentgth 


public double s(int[] ar1, int[] ar2) {
        
    // Current index of input array ar1[]
    int i = 0;
     int n = ar1.length ; 
        int m = ar2.length ; 
    // Current inde of input array ar2[]
    int j = 0;
    int count;
    double m1 = -1, m2 = -1;
     
    // Since there are (n+m) elements, 
    // There are following two cases 
    // if n+m is odd then the middle 
    //index is median i.e. (m+n)/2 
    if ((m + n) % 2 == 1)
    {
        for(count = 0;
            count <= (n + m) / 2;
            count++)
        {
            if (i != n && j != m)
            {
                m1 = (ar1[i] > ar2[j]) ?
                      ar2[j++] : ar1[i++];
            }
            else if (i < n)
            {
                m1 = ar1[i++];
            }
             
            // for case when j<m,
            else
            {
                m1 = ar2[j++];
            }
        }
        return m1;
    }
 
    // median will be average of elements
    // at index ((m+n)/2 - 1) and (m+n)/2
    // in the array obtained after merging
    // ar1 and ar2
    else
    {   
        for(count = 0;
            count <= (n + m) / 2;
            count++)
        {
            m2 = m1;
            if (i != n && j != m)
            {
                m1 = (ar1[i] > ar2[j]) ?
                      ar2[j++] : ar1[i++];
            }
            else if (i < n)
            {
                m1 = ar1[i++];
            }
             
            // for case when j<m,
            else
            {
                m1 = ar2[j++];
            }
        }
        return (m1 + m2) / 2 ;
    }

 }
}


 