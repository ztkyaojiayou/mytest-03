package 数据结构与算法.LeetCode题解.回溯_递归_记忆化搜索;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 * <p>
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class 单词搜索79 {
    int rows;
    int cols;
    boolean[][] isVisited;
    int curWord_index;//深度搜索得到的路径的下标

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        rows = board.length;
        cols = board[0].length;
        isVisited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, i, j, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int cur_row, int cur_col, String word) {
        //递归出口
        //1.目标单词找到（即当前字符串的最大下标=目标目标单词的最大下标），此时结束递归，返回true
        if (curWord_index == word.length() - 1) {//当下标等于目标单词的最大下标时，就表示已经找到该单词了
            return true;
        }
        //2.若当前位置越界，当前位置的字母与目标单词的该位置的字母不相等，或当前位置已经访问过了时，都应该结束递归，此时返回false
        if (cur_row < 0 || cur_row >= board.length || cur_col < 0 || cur_col >= board[0].length
                || board[cur_row][cur_col] != word.charAt(curWord_index)|| isVisited[cur_row][cur_col]) {
            return false;
        }
        //做选择
        curWord_index++;
        isVisited[cur_row][cur_col] = true;
        //下一层递归/即开始深度优先搜索
        boolean res = dfs(board, cur_row + 1, cur_col, word)
                || dfs(board, cur_row - 1, cur_col, word)
                || dfs(board, cur_row, cur_col + 1, word)
                || dfs(board, cur_row, cur_col - 1, word);
        //再看搜索结果
        if (!res) {
            //撤销
            curWord_index--;
            isVisited[cur_row][cur_col] = false;
        }
        return res;
    }
}


