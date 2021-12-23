package 数据结构与算法.LeetCode题解.动态规划;

/**
 * (类比85题：最大矩形）
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4
 */

/**
 * 可以使用动态规划降低时间复杂度。
 *
 * 先建立一个相同规格的二维数组dp。
 * 其中的每一个元素 dp(i,j) 表示以 (i,j) 为右下角，且只包含 1 的正方形的边长的最大值。
 * 如果我们能计算出所有dp(i,j) 的值，那么其中的最大值即为矩阵中只包含 1 的正方形的边长最大值，其平方即为最大正方形的面积。
 *
 * 那么如何计算 dp 中的每个元素值呢？对于每个位置 (i,j)，检查在矩阵中该位置的值：
 * 如果该位置的值是 0，则 dp(i, j) = 0，因为当前位置不可能在由 1 组成的正方形中；
 * 如果该位置的值是 1（也即这是前提），则 dp(i,j) 的值由其上方、左方和左上方的三个相邻位置
 * 的 dp 值 dp(i−1, j)，dp(i, j−1)，dp(i−1, j−1)决定。
 * 具体而言，当前位置的元素值等于以”三个相邻位置的元素为右下角的边长的最大值"中的最小值加 1（画图可知），状态转移方程如下：
 *
 * dp(i, j)=min(dp(i−1, j), dp(i−1, j−1), dp(i, j−1))+1
 *
 * (如果读者对这个状态转移方程感到不解，可以参考 1277. 统计全为 1 的正方形子矩阵的官方题解，其中给出了详细的证明)
 *
 * 此外，还需要考虑边界条件：
 * 如果 i 和 j 中至少有一个为 0，则以位置 (i,j) 为右下角的最大正方形的边长只能是 1，因此此时 dp(i, j) = 1。
 */
public class 面积最大的正方形的面积221 {
        public int maximalSquare(char[][] nums) {
            //0.特判
            int maxSide = 0;
            if (nums == null || nums.length == 0 || nums[0].length == 0) {
                return maxSide;
            }
            //1.先计算该矩阵/二维数组的行和列的值（老生常谈了）
            int m = nums.length;//行
            int n = nums[0].length;//列

            //2.再定义一个与原矩阵大小相同的二维数组，用于状态方程
            //其中，dp[i][j] 表示 以 (i, j)(i,j) 为右下角，且只包含 1 的正方形的边长的最大值
            int[][] dp = new int[m][n];

            //3.再确定初始情况，易知：
            //（1）前提条件：当前值matrix[i][j]必须为1，因为如果该位置的值是 0，则 dp(i, j) = 0，因为当前位置不可能在由 1 组成的正方形中
            //（2）初始情况：如果 i 和 j 中至少有一个为 0，则以位置 (i,j) 为右下角的最大正方形的边长只能是 1，因此此时 dp(i, j) = 1。
            for (int i = 0; i < m; i++) {//使用双重循环进行遍历
                for (int j = 0; j < n; j++) {
                    if (nums[i][j] == '1') {//易知这是前提，若该值不是1，而是0的话，则该值不可能构成全1正方形嘛！（对于为0的情况则可以直接跳过）
                        //3.1初始情况
                        if (i == 0 || j == 0) {
                            dp[i][j] = 1;
                        } else {//3.2一般情况，需要使用状态方程转化到初始情况再求解
                            dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                        }
                        //4.每求出一个dp值，就要与原来的最大边长相比较，选出/更新真正的最大边长值，直到遍历完毕，最大边长也就求出来啦！
                        maxSide = Math.max(maxSide, dp[i][j]);
                    }
                }
            }
            //5.最后返回该最大边长对应的正方形的面积即为所求的最大面积
            int maxSquare = maxSide * maxSide;
            return maxSquare;
            //return maxSide * maxSide;
        }
    }
