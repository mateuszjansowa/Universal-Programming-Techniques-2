package _06_Metadane;

@interface Opis {
    int nazwaDanej(); // deklaracja danych w adnotacji

    String text() default "Brak opisu"; // wartosc domyslna
}

@interface simpleValue {
    int value();
}

public class CreatingAnnotations {

}

@Opis(nazwaDanej = 1, text = "Klasa warzyw")
class Warzywa {
    @simpleValue(100)
    public int parseValue() {
        return 100;
    }
}
