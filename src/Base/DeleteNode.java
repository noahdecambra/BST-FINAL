package Base;

//The goal of this is to delete a node from an already defined binary search tree
import java.util.LinkedList;
import java.util.Queue;

public class DeleteNode
{   
    Node root;
    
    public void addNode(int key, String name)
    {
        //Create a new Node and initialize it
        Node newNode = new Node(key, name);

        //If there is no root this becomes root
        if (root == null)
        {
            root = newNode;
        } 
        else
        {
            //Set root as the Node as start for traversing the tree
            Node focusNode = root;

            //Future parent for our new Node
            Node parent;
            while (true)
            {
                //root is the top parent, so start there
                parent = focusNode;

                //Check if the new node should go on the left side of the parent node
                if (key < focusNode.key)
                {
                    //Switch focus to the left child
                    focusNode = focusNode.leftChild;

                    //If the left child has no children
                    if (focusNode == null)
                    {
                        //then place the new node on the left of it
                        parent.leftChild = newNode;
                        return;
                    }
                }
                else
                {
                    //If we get here put the node on the right
                    focusNode = focusNode.rightChild;

                    //If the right child has no children
                    if (focusNode == null)
                    {
                        //then place the new node on the right of it
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public Node findNode(int key)
    {
        //Start at the top of the tree
        Node focusNode = root;

        //While node is not found the Node keep looking
        while (focusNode.key != key)
        {
            //If search left
            if (key < focusNode.key)
            {
                //Shift the focus Node to the left child
                focusNode = focusNode.leftChild;
            }
            else
            {
                //Shift the focus Node to the right child
                focusNode = focusNode.rightChild;
            }
            //Node wasn't found
            if (focusNode == null)
                return null;
        }
        return focusNode;
    }

    public boolean remove(int key)
    {
        //Start at the top of the tree
        Node focusNode = root;
        Node parent = root;

        //If the list is empty return false
        if (root == null)
        {
                return false;
        }

        //When searching for a Node this will tell us whether to search to the right or left
        boolean isItALeftChild = true;

        //While node is not found the Node keep looking
        while (focusNode.key != key)
        {
            parent = focusNode;
  
            //If we should search to the left
            if (key < focusNode.key)
            {
                isItALeftChild = true;

                //Shift the focus Node to the left child
                focusNode = focusNode.leftChild;
            }
            else
            {
                //Greater than focus node so go to the right
                isItALeftChild = false;

                //Shift the focus Node to the right child
                focusNode = focusNode.rightChild;
            }

            //The node wasn't found
            if (focusNode == null)
                return false;
        }

        //If Node doesn't have children delete it
        if (focusNode.leftChild == null && focusNode.rightChild == null)
        {
            //If root delete it
            if (focusNode == root)
                root = null;

            //If it was marked as a left child of the parent delete it in its parent
            else if (isItALeftChild)
                parent.leftChild = null;

            //Vice versa for the right child
            else
                parent.rightChild = null;
        }

        //If no right child
        else if (focusNode.rightChild == null)
        {
            if (focusNode == root)
                root = focusNode.leftChild;

            //If focus Node was on the left of parent move the focus Nodes left child up to the parent node
            else if (isItALeftChild)
                parent.leftChild = focusNode.leftChild;

            //Vice versa for the right child
            else
                parent.rightChild = focusNode.leftChild;
        }

        //If no left child
        else if (focusNode.leftChild == null)
        {
            if (focusNode == root)
                root = focusNode.rightChild;

            //If focus Node was on the left of parent move the focus Nodes right child up to the parent node
            else if (isItALeftChild)
                parent.leftChild = focusNode.rightChild;

            //Vice versa for the left child
            else
                parent.rightChild = focusNode.rightChild;
        }

        //Two children so need to find the deleted nodes replacement
        else
        {
            Node replacement = getReplacementNode(focusNode);
            //If the focusNode is root replace root with the replacement
            if (focusNode == root)
                root = replacement;

            //If the deleted node was a left child make the replacement the left child
            else if (isItALeftChild)
                parent.leftChild = replacement;

            //Vice versa if it was a right child
            else
                parent.rightChild = replacement;
                replacement.leftChild = focusNode.leftChild;
        }
        return true;
    }

    public Node getReplacementNode(Node replacedNode)
    {
        Node replacementParent = replacedNode;
        Node replacement = replacedNode;
        Node focusNode = replacedNode.rightChild;

        //While there are no more left children
        while (focusNode != null)
        {
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.leftChild;
        }

        //If the replacement isn't the right child move the replacement into the parents leftChild slot and move the replaced nodes right child into the replacements rightChild
        if (replacement != replacedNode.rightChild)
        {
            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacedNode.rightChild;
        }
        return replacement;
    }

    //Checks the value of the node and compares it to the left and right node if available to find the max
    public int findMax(Node node)
	{
		if (node == null)
			return Integer.MIN_VALUE;
	
		int res = node.key;
		int lres = findMax(node.leftChild);
		int rres = findMax(node.rightChild);
	
		if (lres > res)
			res = lres;
		if (rres > res)
			res = rres;
		return res;
	}
    
    //Checks the value of the node and compares it to the left and right node if available to find the minimum
	public int findMin(Node node)
    {
        if (node == null)
            return Integer.MAX_VALUE;

        int res = node.key;
        int lres = findMin(node.leftChild);
        int rres = findMin(node.rightChild);

        if (lres < res)
            res = lres;
        if (rres < res)
            res = rres;
        return res;
    }
	
	int maxHeight(Node node)
    {
        if (node == null)
            return 0;
        else
        {
            int lHeight = maxHeight(node.leftChild);
            int rHeight = maxHeight(node.rightChild);
            if (lHeight > rHeight)
                return (lHeight + 1);
            else
                return (rHeight + 1);
        }
    }

    //Breadth search
	static void bSearch(Node node)
    {
		Queue<Node> q = new LinkedList<Node>();
		q.add(node);
	
        while (!q.isEmpty())
        {
            node = q.remove();
            System.out.print(" " + node.key);
            
            if (node.leftChild != null)
            {
                q.add(node.leftChild);
			}
            if (node.rightChild != null)
            {
                q.add(node.rightChild);
			}
		}
	}

    //Deep search
    static void dSearch(Node node)
    {
		if (node == null)
		{
			return;
		}
		System.out.println(node.key);
		dSearch(node.leftChild);
		dSearch(node.rightChild);
	}

    //All nodes are visited in ascending order
    //Recursion is used to go to one node and then go to its child nodes and so forth
    public void inOrderTraverseTree(Node focusNode)
    {
        if (focusNode != null)
        {            
            preorderTraverseTree(focusNode.leftChild);
            
            System.out.println(focusNode);
            
            preorderTraverseTree(focusNode.rightChild);
        }
    }

    public void preorderTraverseTree(Node focusNode)
    {

        if (focusNode != null)
        {
            System.out.println(focusNode);
            
            preorderTraverseTree(focusNode.leftChild);
            preorderTraverseTree(focusNode.rightChild);
        }
    }
 
    public void postOrderTraverseTree(Node focusNode)
    {
        if (focusNode != null)
        {
            preorderTraverseTree(focusNode.leftChild);
            preorderTraverseTree(focusNode.rightChild);
            
            System.out.println(focusNode);
        }
    }
}