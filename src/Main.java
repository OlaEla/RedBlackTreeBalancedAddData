package RedBlackTreeBalancedAddData.src;




    public class Main {
        public static void main(String[] args) {
            // Создаем экземпляр класса Tree
            Tree tree = new Tree();
            // Вставляем элементы от 1 до 20 в дерево
            for (int i = 1; i <= 20; i++)
                tree.insert(i);

            // Выводим дерево в консоль для отладки
            tree.print();
             
            
            }
        }   
