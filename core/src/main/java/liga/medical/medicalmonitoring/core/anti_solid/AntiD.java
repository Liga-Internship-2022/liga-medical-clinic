package liga.medical.medicalmonitoring.core.anti_solid;

// данный класс привязан к SimpleWriter и к OtherWriter (depends on)
// решение: сделать так, чтобы данный класс зависел от интерфейса Writer (абстракции), а не от его реализаций
public class AntiD {
    private final SimpleWriter simpleWriter;
    // мы захотели сменить реализацию на OtherWriter => сталкиваемся с лишними проблемами
    private final OtherWriter otherWriter;

    public AntiD(SimpleWriter simpleWriter, OtherWriter otherWriter) {
        this.simpleWriter = simpleWriter;
        this.otherWriter = otherWriter;
    }
}

class SimpleWriter {
    public void write() {
    }
}

class OtherWriter {
    public void write() {
    }
}
