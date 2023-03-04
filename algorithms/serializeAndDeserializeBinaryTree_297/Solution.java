package serializeAndDeserializeBinaryTree_297;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Codec {
    final String nn = "null";
    int cur = 0;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] preorder = data.split(",");
        cur = 0;
        return buildTree(preorder);
    }

    private TreeNode buildTree(String[] preorder) {
        if (preorder[cur].equals(nn)) {
            cur++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(preorder[cur]));
        cur++;
        root.left = buildTree(preorder);
        root.right = buildTree(preorder);
        return root;
    }

    private void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null").append(",");
            return;
        }

        sb.append(root.val).append(",");
        preorder(root.left, sb);
        preorder(root.right, sb);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
