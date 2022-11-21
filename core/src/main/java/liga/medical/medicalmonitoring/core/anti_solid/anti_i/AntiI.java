package liga.medical.medicalmonitoring.core.anti_solid.anti_i;

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

// класс OtherClass тоже реалирует SomeInterface, но ему нужны не все методы, а лишь некоторые из них - нарушение принципа разделения интерфейсов
// решение: создать новый интерфейс, где будут только нужные этому классу методы
