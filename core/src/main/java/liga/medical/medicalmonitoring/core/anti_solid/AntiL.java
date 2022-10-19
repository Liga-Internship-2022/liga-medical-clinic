package liga.medical.medicalmonitoring.core.anti_solid;

public class AntiL {

    public void someMethod(ParentClass parent) {

        ParentClass subClass = new SubClass();
        // нарушаем принцип Барбары Лискова
        // класс наследник противоречит логике базового класса (ожидали получить credentials, а получили исключение)
        String credentials = subClass.getConnectionCredentials();
        // ...
    }
}

abstract class ParentClass {
    public abstract String getConnectionCredentials();
}

class SubClass extends ParentClass {
    @Override
    public String getConnectionCredentials() {
        throw new RuntimeException("method not implemented");
    }
}
