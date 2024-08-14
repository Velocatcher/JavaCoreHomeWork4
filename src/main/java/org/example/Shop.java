package org.example;

//— Задание: Класс «Эмуляция интернет-магазина».
//        1. Написать классы покупатель (ФИО, возраст, телефон), товар (название, цена) и
//заказ (объект покупатель, объект товар, целочисленное количество).
//        2. Создать массив покупателей (инициализировать 2 элемента), массив товаров
//(инициализировать 5 элементов) и массив заказов (пустой на 5 элементов).
//        3. Создать статический метод «совершить покупку» со строковыми параметрами,
//соответствующими полям объекта заказа. Метод должен вернуть объект заказа.
//4. Если в метод передан несуществующий покупатель – метод должен выбросить
//        исключение CustomerException, если передан несуществующий товар, метод
//должен выбросить исключение ProductException, если было передано отри-
//цательное или слишком больше значение количества (например, 100), метод
//должен выбросить исключение AmountException.
//        5. Вызвать метод совершения покупки несколько раз таким образом, чтобы запол-
//нить массив покупок возвращаемыми значениями. Обработать исключения сле-
//дующим образом (в заданном порядке):
//        – если был передан неверный товар – вывести в консоль сообщение об ошиб-
//ке, не совершать данную покупку;
//– если было передано неверное количество – купить товар в количестве 1;
//        – если был передан неверный пользователь – завершить работу приложения
//с исключением.
//        6. Вывести в консоль итоговое количество совершённых покупок после выполне-
//ния основного кода приложения.

//В класс покупателя добавить перечисление с гендерами, добавить в сотрудника
//свойство “пол” со значением перечисления. добавить геттеры, сеттеры.
//Добавить перечисление с праздниками (нг, 08.03,23.02), в основной программе
//        написать метод, принимающий массив сотрудников и поздравляющий всех
//сотрудников с новым годом, женщин с 08.03, мужчин с 23.02.

public class Shop {

    public static class CustomerException extends RuntimeException {
        public CustomerException(String message) {
            super(message);
        }
    }

    public static class ProductException extends RuntimeException {
        public ProductException(String message) {
            super(message);
        }
    }

    public static class AmountException extends RuntimeException {
        public AmountException(String message) {
            super(message);
        }
    }

    private final static Customer[] people = {
            new Customer("Ivan", 20, "111 222 333"),
            new Customer("Petr", 30, "333-444-55-66"),
    };
    private final static Item[] items = {
            new Item("Ball", 100),
            new Item("Sandwich", 1000),
            new Item("Table", 10000),
            new Item("Car", 100000),
            new Item("Rocket", 10000000)
    };
    private static Order[] orders = new Order[5];

//    ************************HERE

    private static boolean isInArray(Object[] arr, Object o) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(o))
                return true;
        }
        return false;
    }

    public static Order buy(Customer who, Item what, int howMuch) {
        if (!isInArray(people, who))
            throw new CustomerException("Unknown customer: " + who);
        if (!isInArray(items, what))
            throw new ProductException("Unknown item: " + what);
        if (howMuch < 0 || howMuch > 100)
            throw new AmountException("Incorrect amount: " + howMuch);
        return new Order(who, what, howMuch);

    }

    public static void main(String[] args) {
        Object[][] info = {
                {people[0], items[0], 1}, //good
                {people[1], items[1], -1}, //bad amount -1
                {people[0], items[2], 150}, //bad amount >100
                {people[1], new Item("Flower", 10), 1}, //no item
                {new Customer("Fedor", 40, "+3-444-555-66-77"), items[3], 1}, //no customer
        };
        int capacity = 0;
        int i = 0;
        while (capacity != orders.length - 1 || i != info.length) {
            try {
                orders[capacity] = buy((Customer) info[i][0], (Item) info[i][1], (int) info[i][2]);
                capacity++;
            } catch (ProductException e) {
                e.printStackTrace();
            } catch (AmountException e) {
                orders[capacity++] = buy((Customer) info[i][0], (Item) info[i][1], 1);
            } catch (CustomerException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Orders made: " + capacity);
            }
            ++i;
        }

    }
}