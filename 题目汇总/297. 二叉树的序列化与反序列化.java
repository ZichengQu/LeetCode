/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 /**
  * DFS
  */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sBuilder = serialize(root, new StringBuilder());
        return sBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList  = new LinkedList<>(Arrays.asList(dataArray));
        return deserialize(dataList);
    }

    private StringBuilder serialize(TreeNode root, StringBuilder sBuilder){
        if(root == null){
           sBuilder.append("null,");
        }else{
            sBuilder.append(root.val + ",");
            sBuilder = serialize(root.left, sBuilder);
            sBuilder = serialize(root.right, sBuilder);
        }
        return sBuilder;
    }

    private TreeNode deserialize(List<String> dataList){
        if("null".equals(dataList.get(0))){
            dataList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = deserialize(dataList);
        root.right = deserialize(dataList);

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));