package ch.fhnw.algd2.tree.solution;

//******************************************************************************
//  FHNW.ALGD2  -  Excercise 4 : Testing Binary Search Trees                   *
// --------------------------------------------------------------------------  *
//  version 2                                                             vtg  * 
//******************************************************************************
public class main_binTree {

  public static void main(String[] args) {
    int[] values = {12, 19, 31, 37, 43, 49, 51, 55, 67, 70, 78, 81, 87, 92, 99};
    BinaryTree bt = new BinaryTree(values);
    //BinaryTree bt = new BinaryTree();
    bt.show();
    System.out.println("------------------------------------");

//    bt.testInternalSearch(55);
//    System.out.println("---------------------------");
//    bt.testInternalSearch(37);
//    System.out.println("---------------------------");
//    bt.testInternalSearch(81);
//    System.out.println("---------------------------");
//    bt.testInternalSearch(0);

    //bt.insert(13);
    bt.insert(50);
    bt.show();
    System.out.println("------------------------------------");

    //bt.remove(51);
    //bt.remove(49);
    bt.remove(55);
    bt.remove(81);

    bt.show();

  }

}
