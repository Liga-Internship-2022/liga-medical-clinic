package liga.medical.medicalmonitoring.core.anti_solid.anti_o;

// класс ShapeHandler
// должен уметь вычислять площадь фигур и тд
public class AntiO {

    public int getArea(Circle circle) {
        // circle.getArea(double radius);
        return 0;
    }

    public int getArea(Square square) {
        // square.getArea(double radius);
        return 0;
    }
}
// как нужно:
// нужно создать абстрактный класс Shape с необходимыми методами, в том числе int getArea(Shape shape)
// и каждая фигура будет расширять Shape и реализовывать свою логику
// таким образом мы не меняем этот класс, а расширяем функционал абстрактного класса