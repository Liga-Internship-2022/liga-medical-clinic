package liga.medical.medicalmonitoring.core.anti_solid;

import java.util.ArrayList;
import java.util.List;

public class AntiS {

    // аналог JdbcTemplate
    // по SRP следует разделить данный "универсальный" класс на классы, которые будут отвечать за что-то одно
    // например на класс ConnectionManager и SqlExecutor

    public boolean openConnection(String credentials) {
        // ...
        return false;
    }

    public boolean closeConnection() {
        // ...
        return false;
    }

    public boolean doInsert(String row) {
        // ...
        return false;
    }

    public List<String> doSelect(String row) {
        // ...
        return new ArrayList<>();
    }

    public boolean doUpdate(String row) {
        // ...
        return false;
    }

    public boolean doDelete(String row) {
        // ...
        return false;
    }
}
