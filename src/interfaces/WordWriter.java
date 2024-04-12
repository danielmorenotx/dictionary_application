package interfaces;

import domain.Word;

import java.io.IOException;
import java.util.List;

public interface WordWriter {
    void writeEntries(List<Word> dictionaryEntries) throws IOException;
}
