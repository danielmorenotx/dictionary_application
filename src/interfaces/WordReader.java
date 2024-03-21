package interfaces;

import domain.Word;

import java.io.IOException;
import java.util.List;

public interface WordReader {
    public String readEntries(String exampleFile) throws IOException;

}
