import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static boolean canPartition(int[] nums) {
        boolean val = canPartition_helper(nums,0,0,0);
        return val;
    }
    public static boolean canPartition_helper(int[] nums,int ind,int sum1,int sum2){
        if( sum1 == sum2 && ind != 0){
            return true;
        }
        if(ind >= nums.length){
            return false;
        }
        boolean inSubset1 = canPartition_helper(nums,ind+1,sum1+nums[ind],sum2);
        boolean inSubset2 = canPartition_helper(nums,ind+1,sum1,sum2+nums[ind]);
        return inSubset1 || inSubset2;
    }

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1,2,3,5}));
    }
}