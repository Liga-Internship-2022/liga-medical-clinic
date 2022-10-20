package liga.medical.medicalmonitoring.core.anti_solid.anti_l;

public class AntiL {

    public void someMethod(ParentClass parent) {
        // нарушаем принцип Барбары Лисков в случае, если в метод передан SubClass
        // класс наследник противоречит логике базового класса (ожидали получить какие-то данные, а получили исключение)
        String info = parent.getConnectionInfo();
        System.out.println(info);
        // ...
    }
}
