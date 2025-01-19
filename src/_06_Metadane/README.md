# 06 Metadane i adnotacje

## Zastosowanie: zalety i wady

Metadane - informacje uzupełniające kod źródłowy. Od Javy 5 stają się standardowym mechanizmem.

**Zastosowanie adnotacji:**

- poprawia niezawodności kodu (`@Override`);
- generowanie kodu pomocniczego;
- konfiguracja aplikacji (np. w `Spring`);
- generowanie plików pomocnicznych (deskryptory XML).

**Zalety:**

- uproszczenie kodu, zmniejszenie powtarzalnośc;
- ułatwienie konfiguracji dużych systemów.

**Wady:**

- możliwość tworzenia niestandardowych “dialektów” Javy;
- konieczność nauki adnotacji per framework;
- brak spójnych narzędzi do walidowania poprawności tworzonych adnotacji.

## Rodzaje adnotacji

### Wbudowane w Javę

Najważniejsze:

- `@Depracated` - element przestarzały;
- `@SuppressWarnings("type")` - wyłącza ostrzeżenia kompilatora;
- `@Override` - nadpisanie metody

```java
class Push extends JButton {
    @Override
    public Dimension getPrefferedSize() { // BŁĄD: literówka -> wykrycie błędu
        return new Dimension(100, 50);
    }
}
```

### Metaadnotacje (adnotacje dla adnotacji)

- `@Target` - określa, gdzie można użyć adnotacji (klasa, metoda, pole);
- `@Retention` - kontroluje czas życia adnotacji

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestMethod {}
```

- `@Documented` - włącza adnotację do dokumentacji (`JavaDoc`)
- `@Inherited` - umożliwia dziedziczenie adnotacji przez podklasy.

```java
@Inherited
@interface MyAnnotation {}

class ParentClass {
	@MyAnnotation
	void myMethod() {}
}

class ChildClass extends ParentClass {} // dziedziczy @MyAnnotation
```

### Adnotacje użytkownika

Pozwalają na **definiowanie własnej adnotacji:**

```java
@Retention(RetentionPolicy.RUNTIME)
@Target (ElementType.METHOD)
@interface LogExecutionTime {}
```

**Użycie**:

```java
public class Main {
    @LogExecutionTime
    public void process() {
        System.out.println("Processing...");
    }
}
```

**Przetwarzanie adnotacji refleksją:**

```java
import java.lang.reflect.Method;

public class AnnotationProcessor {
    public static void main(String[] args) throws Exception {
        Method method = Main.class.getMethod("process");
        if (method.isAnnotationPresent(LogExecutionTime.class)) {
            long start = System.nanoTime();
            method.invoke(new Main());
            long end = System.nanoTime();
            System.out.println("Execution time: " + (end - start) + " ns");
        }
    }
}
```

## Metaadnotacje i ich znaczenie

- `@Target` określa, gdzie można stosować adnotację

| Wartość | Opis |
| --- | --- |
| `ANNOTATION_TYPE` | Dla innych adnotacji |
| `PACKAGE` | Dla pakietów |
| `TYPE` | Dla klas i interfejsów |
| `METHOD` | Dla metod |
| `CONSTRUCTOR` | Dla konstruktorów |
| `FIELD` | Dla pól |
| `PARAMETER` | Dla parametrów |
| `LOCAL_VARIABLE` | Dla zmiennych lokalnych |

```java
@Target({ElementType.FIELD, ElementType.METHOD})
@interface FieldOrMethod {}
```

- `@Retention` - kontroluje jak długo adnotacja jest zachowywana

| Wartość | Opis |
| --- | --- |
| `SOURCE` | Adnotacja tylko w kodzie źródłowym, ignorowana przez kompilator. |
| `CLASS` | Adnotacja w `.class`, ale niedostępna w czasie wykonania |
| `RUNTIME` | Adnotacja dostępna w czasie wykonania (np. Spring) |

## Praktyczne zastosowanie

- `JUnit` - automatyczne testowanie

    ```java
    public class MyTest {
        @Test
        void testSomething() {
            assertEquals(2, 1 + 1);
        }
    }
    ```

- `Spring` - konfiguracja aplikacji

    ```java
    @RestController
    @RequestMapping("/users")
    public class UserController {
        @GetMapping("/{id}")
        public User getUser(@PathVariable Long id) { ... }
    }
    ```

- `Hibernate/JPA` - mapowanie encji

    ```java
    @Entity
    class User {
        @Id @GeneratedValue
        private Long id;
    
        @Column(name = "username", nullable = false)
        private String username;
    }
    ```


## Definiowanie adnotacji

Składnia:

```java
[dostęp] @interface Adnotacja {
    int nazwaDanej(); // deklaracja danych w adnotacji

    String text() default "Brak opisu"; // wartosc domyslna
}
```

Przykład użycia:

```java
@Adnotacja(nazwaDanej = 1, text = "Klasa warzyw")
class Warzywa {
 /// ...
}
```

Jeśli adnotacja ma jedną daną o nazwie `value`, można pominąć jej nazwę:

```java
public @interface JakaśAdnotacja {
    int value();
}
```

```java
@JakaśAdnotacja(111)  // Równoważne z @JakaśAdnotacja(value=111)
```

## Przykładowe zastosowanie adnotacji

W nowoczesnych aplikacjach w **Spring Boot**, adnotacje są powszechnie używane do walidacji danych wejściowych. Zamiast pisać kod ręcznie, możemy używać własnych adnotacji do walidacji.

**Definicja niestandardowej adnotacji**

```java
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)  // Można stosować do pól klasy
@Retention(RetentionPolicy.RUNTIME)  // Będzie dostępna w czasie wykonania
@Constraint(validatedBy = PeselValidator.class)  // Klasa walidująca
public @interface ValidPesel {
    String message() default "Niepoprawny numer PESEL";  // Domyślny komunikat błędu
    Class<?>[] groups() default {};  // Używane w grupach walidacyjnych
    Class<? extends Payload>[] payload() default {};  // Dodatkowe dane
}

```

**Implementacja walidatora dla PESEL**

```java
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeselValidator implements ConstraintValidator<ValidPesel, String> {
    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext context) {
        if (pesel == null || !pesel.matches("\\d{11}")) {
            return false;
        }

        // Prosty algorytm sprawdzający sumę kontrolną PESEL
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(pesel.charAt(i)) * weights[i];
        }

        int controlDigit = (10 - (sum % 10)) % 10;
        return controlDigit == Character.getNumericValue(pesel.charAt(10));
    }
}

```

**Zastosowanie w encji JPA**

```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Imię jest wymagane")
    private String name;

    @ValidPesel
    private String pesel;
}

```

**Walidacja w kontrolerze SpringBoot**

```java
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok("Użytkownik zapisany poprawnie");
    }
}

```