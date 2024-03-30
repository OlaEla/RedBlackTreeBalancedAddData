package RedBlackTreeBalancedAddData.src;

public class Tree {
    Node root; // Корневой узел дерева

    // Внутренний статический класс для представления узла дерева
    class Node {
        int value; // Значение узла
        Node left; // Ссылка на левого потомка
        Node right; // Ссылка на правого потомка
        Color color; // Цвет узла
    }

    // Перечисление для представления цвета узла (красный или черный)
    enum Color {
        BLACK,
        RED
    }
    // Вставляет новый элемент в дерево
    public void insert(int value) {
        if (root != null) {
            insert(root, value); // Рекурсивная вставка в непустое дерево
            root = balance(root); // Балансировка дерева после вставки
        } else {
            // Если дерево пустое, создаем новый корневой узел
            root = new Node();
            root.value = value;
        }
        root.color = Color.BLACK; // Корень дерева всегда должен быть черным
    }
    // Рекурсивная вставка нового узла в дерево.
    private void insert(Node currentNode, int value) {
        if (currentNode.value != value) {
            // Вставка в правое поддерево
            if (currentNode.value < value) {
                if (currentNode.right == null) {
                    currentNode.right = new Node();
                    currentNode.right.value = value;
                    currentNode.right.color = Color.RED; // Новый узел красного цвета
                } else {
                    insert(currentNode.right, value); // Рекурсивная вставка
                    currentNode.right = balance(currentNode.right); // Балансировка правого поддерева
                }
            } else {
                // Вставка в левое поддерево
                if (currentNode.left == null) {
                    currentNode.left = new Node();
                    currentNode.left.value = value;
                    currentNode.right.color = Color.RED; // Новый узел красного цвета
                } else {
                    insert(currentNode.left, value); // Рекурсивная вставка
                    currentNode = balance(currentNode.left); // Балансировка левого поддерева
                }
            }

        }
    }

    // Находит узел с заданным значением в дереве.
    public Node find(int value) {
        return find(root, value);
    }
    // Рекурсивный поиск узла в дереве.
    private Node find(Node node, int value) {
        if (node == null) {
            return null; // Значение не найдено
        }
        if (node.value == value) {
            return node; // Значение найдено
        }
        if (node.value < value) {
            return find(node.right, value); // Поиск в правом поддереве
        } else {
            return find(node.left, value); // Поиск в левом поддереве
        }
    }

    // Выполняет малый левый поворот вокруг указанного узла.
    private Node leftRotate(Node node) {
        Node cur = node.right;
        node.right = cur.left;
        cur.left = node;
        cur.color = node.color;
        node.color = Color.RED;
        return cur;
    }

    // Выполняет малый правый поворот вокруг указанного узла.
    private Node rightRotate(Node node) {
        Node cur = node.left;
        node.left = cur.right;
        cur.right = node;
        cur.color = node.color;
        node.color = Color.RED;
        return cur;
    }

    // Меняет цвета узла и его дочерних узлов.
    private void swapColors(Node node) {
        node.color = (node.color == Color.RED ? Color.BLACK : Color.RED);
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }

    // Производит балансировку дерева после вставки или удаления узла.
    private Node balance(Node node) {
        boolean needRebalance = true;
        Node newRoot = node;
        do {
            needRebalance = false;

            // Случай 1: Правый дочерний узел красный, левый черный
            if (newRoot.right != null && newRoot.right.color == Color.RED
                    && (newRoot.left == null || newRoot.left.color == Color.BLACK)) {
                newRoot = leftRotate(newRoot); // Малый левый поворот
                needRebalance = true;
            }

            // Случай 2: Левый дочерний узел и его левый потомок красные
            if (newRoot.left != null && newRoot.left.color == Color.RED && newRoot.left.left != null
                    && newRoot.left.left.color == Color.RED) {
                newRoot = rightRotate(newRoot); // Малый правый поворот
                needRebalance = true;
            }

            // Случай 3: Оба дочерних узла красные
            if (newRoot.left != null && newRoot.left.color == Color.RED && newRoot.right != null && newRoot.right.color == Color.RED) {
                swapColors(newRoot); // Смена цветов
                needRebalance = false;
            }
        } while (needRebalance); // Повторяем балансировку, пока необходимо
        return newRoot;

    }

    // Выводит дерево в консоль для отладки и проверки.
    public void print() {
        print(root, 0);
    }

    // Рекурсивный метод для вывода дерева в консоль.
    private void print(Node node, int depth) {
        if (node == null) {
            return;
        }

        print(node.left, depth + 4); // Рекурсивный вывод левого поддерева

        // Вывод значения и цвета текущего узла
        for (int i = 0; i < depth; i++)
            System.out.print(" ");
        System.out.println("value; " + node.value + " {color: " + node.color + "}");

        print(node.right, depth + 4); // Рекурсивный вывод правого поддерева
    }
}