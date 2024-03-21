package interfaces;

import domain.Word;

import java.io.IOException;
import java.util.List;

public interface WordWriter {
    List<Word> writeEntries(Word word) throws IOException;

}
