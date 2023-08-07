import java.util.Scanner;
/*
- As chaves devem ser todas inteiras
- Você não precisa colocar os dados que os nós da árvore indexam, cada nó pode armazenar apenas a chave do nó, o fator de balanceamento e os ponteiros para as sub-árvores direita e esquerda
- As entradas do programa devem ser realizadas via teclado (ou seja, solicite ao usuário que digite a operação desejada e o valor a ser inserido). Por exemplo: “insert 10” pode ser uma forma de indicar a inserção da chave 10 na árvore
- A saída do programa também pode ser realizada com impressões, desde que seja possível ver o estado da árvore ao final de cada operação. Você pode utilizar o formato de impressão que preferir
- A cada operação de rotação realizada, imprima na tela a operação realizada e o motivo (ou seja, qual chave estava desbalanceada)
*/
public class Main {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    Tree tree = new Tree();
    boolean continueUI = true;

    while(continueUI){
      System.out.println("What do you want to do?"+
      "\n1 - Insert Value"+
      "\n2 - Delete Value"+
      "\n3 - Print Tree"+
      "\n4 - Finish");

      int action = Integer.parseInt(s.nextLine().trim());
      if(action == 1){
        System.out.println("What value do you want to insert?");
        int value = Integer.parseInt(s.nextLine().trim());
        tree.root = tree.insertNode(tree.root, value);
        tree.printTree(tree.root, "", false);
      }
      else if(action == 2){
        System.out.println("What value do you want to delete?");
        int value = Integer.parseInt(s.nextLine().trim());
        tree.root = tree.deleteNode(tree.root, value);
        tree.printTree(tree.root, "", false);
      }
      else if(action == 3){
        tree.printTree(tree.root, "", false);
      }
      else if(action == 4){
        continueUI = false;
      }
      else{
        System.out.println("Invalid Input Value");
      }
    }
  }
}
