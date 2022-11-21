package liga.medical.medicalmonitoring.core.anti_solid.anti_i;

// этот класс тоже реалирует SomeInterface, но ему нужны не все методы, а лишь некоторые из них - нарушение принципа разделения интерфейсов
// решение: создать новый интерфейс, где будут только нужные этому классу методы
public class OtherClass implements SomeInterface {

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