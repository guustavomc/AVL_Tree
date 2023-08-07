public class Tree {
    Node root;

    //BR:Calcula o FB, altura do node da esquerda - altura do node da direita
    //US: Calculate FB, left node height - right node height
    int balanceFactor(Node currentNode){
        if(currentNode == null){
            return 0;
        }
        else{
            return height(currentNode.left) - height(currentNode.right);
        }
    }

    //BR: Retorna a altura do Node, utilizado para validar a altura, principalmente quando os nodes nao tem informacao(estao null)
    //US: Returns the height of the Node, used to validate the height, especially when the nodes have no information (they are null)
    int height(Node currentNode){
        if(currentNode == null){
            return 0;
        }
        else{
            return currentNode.height;
        }
    }

    Node rotateRight(Node currentNode){
        System.out.println("Node with key " + currentNode.key + " Rotate to the right as balance factor is: " + balanceFactor(currentNode));

        Node LS = currentNode.left;
        Node B = LS.right;

        LS.right = currentNode;
        currentNode.left = B;



        if(height(currentNode.left) > height(currentNode.right)){
            currentNode.height = height(currentNode.left) + 1;
        }
        else{
            currentNode.height = height(currentNode.right) + 1;
        }

        if(height(LS.left) > height(LS.right)){
            LS.height = height(LS.left) + 1;
        }
        else{
            LS.height = height(LS.right) + 1;
        }
        return LS;
    }

    Node rotateLeft(Node currentNode){
        System.out.println("Node with key " + currentNode.key + " Rotate to the left as balance factor is: " + balanceFactor(currentNode));

        Node RS = currentNode.right;
        Node B = RS.left;

        RS.left = currentNode;
        currentNode.right = B;



        if( height(currentNode.right) > height(currentNode.left)){
            currentNode.height = height(currentNode.right)+1;
        }
        else{
            currentNode.height = height(currentNode.left)+1;
        }

        if(height(RS.right) > height(RS.left)){
            RS.height = height(RS.right)+1;
        }
        else{
            RS.height = height(RS.left)+1;
        }
        return RS;
    }
  
    //BR: Retorna a altura do Node, utilizado para ver se a altura é maior na sub arvore da esquerda ou da direita
    //US: Returns the height of the Node, used to see if the height is greater in the left or right subtree
    int nodeHeight(Node currentNode){
        if(currentNode == null){
            return 0;
        }
        
        if(height(currentNode.right) > height(currentNode.left)){
            return height(currentNode.right);
        }
        else{
            return height(currentNode.left);
        }
    }

    Node insertNode(Node currentNode, int key){
        if(currentNode == null){
            return (new Node(key));
        }
        if(currentNode.key > key){
            currentNode.left = insertNode(currentNode.left, key);
        }
        else if(currentNode.key < key){
            currentNode.right = insertNode(currentNode.right, key);
        }
        else{
            return currentNode;
        }

        //BR: Atualiza a altura do node atual e verifica seu fator de balanceamento, se o node não estiver balanceado vai ser corrigido 
        //US:Update the height of current Node and check its balance factor, if node is not balanced correct it

        currentNode.height = nodeHeight(currentNode)+1;
        int BF = balanceFactor(currentNode);

        //BR: Se estiver desbalanceado para a esquerda é necessário fazer rotação para a direita
        //US: If it is unbalanced to the left it is necessary to rotate it to the right
        if(BF > 1){
            if(key < currentNode.left.key){
                return rotateRight(currentNode);
            }
            /*BR: se o Node estiver desbalanceado para a esquerda e o seu filho da esquerda estiver tendendo para a direita,
            primeiro iremos fazer rotação a esquerda do filho da esquerda e em seguida fazer a rotação a direita do Node
            US: if the Node is skewed to the left and its left child is biased to the right,
            first we will rotate the left child to the left and then rotate the Node to the right
            */
            else if(key > currentNode.left.key){
                currentNode.left = rotateLeft(currentNode.left);
                return rotateRight(currentNode);
            }
        }
        //BR: Se estiver desbalanceado para a direita é necessário fazer rotação para a esquerda
        //US: If it is unbalanced to the right it is necessary to rotate it to the left
        else if(BF < -1){
            if(key> currentNode.right.key){
                return rotateLeft(currentNode);
            }
            /*BR: se o Node estiver desbalanceado para a direita e o seu filho da direita estiver tendendo para a esquerda,
            primeiro iremos fazer rotação a direita do filho da direita e em seguida fazer a rotação a esquerda do Node
            US:if the Node is skewed to the right and its child on the right is biased to the left,
            first we will rotate the right child to the right and then rotate the Node to the left
            */
            else if(key< currentNode.right.key){
                currentNode.right = rotateRight(currentNode.right);
                return rotateLeft(currentNode);
            }
        }
        return currentNode;
    }

    void printTree(Node currentNode, String currentPrint, boolean isRight) {
        if (currentNode != null) {
          System.out.print(currentPrint);
          if (isRight) {
            System.out.print("R----");
            currentPrint += "   ";
          } 
          else {
            System.out.print("L----");
            currentPrint += "|  ";
          }
          System.out.println(currentNode.key);
          printTree(currentNode.left, currentPrint, false);
          printTree(currentNode.right, currentPrint, true);
        }
      }

    Node smallestNode(Node currentNode) {
        Node current = currentNode;
        while (current.left != null)
          current = current.left;
        return current;
      }

    
    Node deleteNode (Node currentNode, int key){
        if(currentNode == null){
            return currentNode;
        }
        if(key < currentNode.key){
            currentNode.left = deleteNode(currentNode.left, key);
        }
        else if(key > currentNode.key){
            currentNode.right = deleteNode(currentNode.right, key);
        }
        //BR: Encontra o nó que precisa ser deletado
        //US: Finds the node that needs to be deleted
        else{
            if((currentNode.left == null) || (currentNode.right == null)){
                Node tempNode = null;
                //BR: Valida se é direita ou esquerda que é null
                //US: Validates if it is right of left that is null
                if (tempNode == currentNode.left){
                    tempNode = currentNode.right;
                }
                else{
                    tempNode = currentNode.left;
                }
                //BR: Se ambos forem nulos, o valor do nó atual será alterado para nulo
                //US: If both are null the value of current node will be turned to null
                if(tempNode == null){
                    tempNode = currentNode;
                    currentNode = null;
                }
                else{
                currentNode = tempNode;
                }
            }
            /*BR: Se os filhos direito ou esquerdo não forem nulos, ele localizará a chave no lado direito do currentNode.
            Depois disso, ele substituirá o currentNode pelo valor menor e excluirá o nó com o valor menor para evitar valores duplicados
            US: If the right or left sons are not null it will locade the key to the right side on the currentNode.
            After that it will substitute the currentNode with the smalles value and proceed to delete the node with the smalles value to avoid duplicated values
            */
            else{
                Node tempNode = smallestNode(currentNode.right);
                currentNode.key = tempNode.key;
                currentNode.right = deleteNode(currentNode.right, tempNode.key);
            }  

        }
        if(currentNode == null){
            return currentNode;
        }
        //BR: //Atualiza a altura do nodo atual e verifica seu fator de balanceamento, se o nodo não estiver balanceado corrija-o
        //US: Update the height of current Node and check its balance factor, if node is not balanced correct it
        currentNode.height = nodeHeight(currentNode)+1;
        int BF = balanceFactor(currentNode);

        //BR: Se estiver desbalanceado para a esquerda é necessário fazer rotação para a direita
        //US: If it is unbalanced to the left it is necessary to rotate it to the right
        if(BF > 1){
            if(balanceFactor(currentNode.left) >= 0){
                return rotateRight(currentNode);
            }
             /*BR: se o Node estiver desbalanceado para a esquerda e o seu filho da esquerda estiver tendendo para a direita,
            primeiro iremos fazer rotação a esquerda do filho da esquerda e em seguida fazer a rotação a direita do Node
            US: if the Node is unbalanced to the left and its left child is biased to the right,
            first we will rotate the left child to the left and then rotate the Node to the right
            */
            else{
                currentNode.left = rotateLeft(currentNode);
                return rotateRight(currentNode);
            }
            
        }
        //BR: Se estiver desbalanceado para a direita é necessário fazer rotação para a esquerda
        //US: If it is unbalanced to the right, it must be rotated to the left.
        else if(BF < -1){
            if(balanceFactor(currentNode.right) <= 0){
                return rotateLeft(currentNode);
            }
             /*BR: se o Node estiver desbalanceado para a direita e o seu filho da direita estiver tendendo para a esquerda,
            primeiro iremos fazer rotação a direita do filho da direita e em seguida fazer a rotação a esquerda do Node
            US: if the Node is skewed to the right and its child on the right is biased to the left,
            first we will rotate the right child to the right and then rotate the Node to the left
            */
            else{
                currentNode.right = rotateRight(currentNode);
                rotateLeft(currentNode);
            }
            
        }
        return currentNode;
    }

}
