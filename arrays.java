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
        
    // -> max stock buy and sell at most two times

    int maxtwobuysell(int arr[],int size) {
    int first_buy = INT_MIN;
      int first_sell = 0;
      int second_buy = INT_MIN;
      int second_sell = 0;
       
      for(int i=0;i<size;i++) {
         
          first_buy = max(first_buy,-arr[i]);//we set prices to negative, so the calculation of profit will be convenient
          first_sell = max(first_sell,first_buy+arr[i]);
          second_buy = max(second_buy,first_sell-arr[i]);//we can buy the second only after first is sold
          second_sell = max(second_sell,second_buy+arr[i]);
       
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

 