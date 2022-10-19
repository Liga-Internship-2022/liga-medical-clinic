package liga.medical.medicalmonitoring.core.anti_solid;

public class AntiI implements SomeInterface {
    @Override
    public void method1() {
        // implemented
    }

    @Override
    public void method2() {
        // implemented
    }

    @Override
    public void method3() {
        // implemented
    }
}

// этот класс тоже реалирует SomeInterface, но ему нужны не все методы, а лишь некоторые из них - нарушение принципа разделения интерфейсов
// решение: создать новый интерфейс, где будут только нужные этому классу методы
class OtherClass implements SomeInterface {

    @Override
    public void method1() {
        // implemented
    }

    @Override
    public void method2() {
        // not implemented
    }

    @Override
    public void method3() {
        // not implemented
    }
}

interface SomeInterface {
    void method1();
    void method2();
    void method3();
}
