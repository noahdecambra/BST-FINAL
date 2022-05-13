package Base;

//Creating original BST with nodes and elements
public class Main
{
    //Main execution of code
    public static void main(String[] args)
    {
        DeleteNode tree = new DeleteNode();
        tree.addNode(40, "Team Manager");
        tree.addNode(15, "Head Coach");
        tree.addNode(5, "Assistant Coach");
        tree.addNode(25, "Team Captain");
        tree.addNode(50, "Player");
        tree.addNode(65, "Waterboy");
       
		System.out.println("The Maximum element of the tree is " + tree.findMax(tree.root));
		System.out.println("The Minimum element of the tree is " + tree.findMin(tree.root));
        System.out.println("The height of the tree is " + tree.maxHeight(tree.root));

        //Traversing the tree in order
        System.out.println("\nThe in order line is: ");
        tree.inOrderTraverseTree(tree.root);

        //Traversing the tree in order after deletion
        System.out.println("\nRemove Key 25");
        tree.remove(25);
        System.out.println(tree.findNode(25));

        System.out.println("\nThe in order line after deletion is: ");
        tree.inOrderTraverseTree(tree.root);

        System.out.println("\nThe pre-order line after deletion is: ");
        tree.preorderTraverseTree(tree.root);
        System.out.println("\nThe post-order line after deletion is: ");
        tree.postOrderTraverseTree(tree.root);
    }
}