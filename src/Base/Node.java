package Base;

//Create a node that can be accessed
public class Node
{
    int key;
    String name;
    Node leftChild;
    Node rightChild;

    Node(int key, String name)
    {
        this.key = key;
        this.name = name;
    }

    public String toString()
    {
        return name + " has the key " + key;
    }
}