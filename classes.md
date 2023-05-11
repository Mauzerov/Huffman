# Kotlin classes
---
### Basic Implementation
> pusta class
>```kotlin
>class ClassName
>```

> konstruktor
>```kotlin
>class ClassName {
>    init {
>
>    }
>}
>```

> destruktor \
> Kotlin już nie posiada destruktorów, rekomendowaną alternatywą jest używanie `.use`, co automatycznie wywołuje `.close` w momencie gdy `.use` przestanie się wykonywać
>```kotlin
>class ClassName : AutoCloseable {
>    override fun close() {
>        // Destructor logic
>    }
>}
>
>val result = ClassName().use {
>    // Use Object
>}
>```

> pola statyczne \
> pla statyczne klasy przechowywane są w `companion object` zdefiniowanym w zakresie klasy
> ```kotlin
> class ClassName {
>     companion object {
>         fun function() {}
>         const val KEY = "String_Key"
>         var count = 0
>     }
> }
>```

### Ważne różnice

- wszystko w Kotlinie jest na odwrót np. wszytko jest `public` i `final` a nie `private` i `open`
- aby umożliwić nadpisanie metody lub dziedziczenie klasy, trzeba oznaczyć ją jako `open`.
```kotlin
open class ClassName {
    open fun function() {

    }
}
```
- aby zapobiec dostępowi do pola klasy, trzeba oznaczyć wybrane pola jako `private`, `protected` lub `internal`
```kotlin
open class ClassName {
    private fun function() {

    }
}
```
### Konstruktory
> podstawowy konstruktor \
> w Kotlinie podstawowy konstruktor powinien znajdować się w definicji klasy pomiędzy nazwą klasy a dziedziczonymi elementami
>```kotlin
>class ClassName(number: Int) {
>    init {
>        // `number` jest widoczny w `init`
>    }
>}
>```
> podstawowe konstruktory mogą mieć niektóre/wszystkie pola zdeklarowane w deklaracji konstruktora poprzez `var` i `val` wraz z ich stopniem dostępu `private`, `protected`, `public`, `internal`
>```kotlin
>class ClassName(private val number: Int) {
>        
>}
>```
> drugorzędne konstruktory \
> w Kotlinie każdy konstruktor może być zdeklarowany za pomocą `constructor`
>```kotlin
>class ClassName {
>    constructor(number: Int) {
>
>    }
>}
>```
> jeśli nie możesz przypisać wartości do pola odrazu ani w konstruktorze, musisz użyć modyfikatora `lateinit var`. Pozwala to na przypisanie wartości w dowolnym miejscu w klasie, ale jeśli spróbujesz odczytać niezainicjalizowaną wartość, spotkasz się z blędem
>```kotlin
>class ClassName {
>    lateinit var uuid: String
>    init {
>        assignUUID()
>    }
>    private fun assignUUID() {
>        uuid = newUUID()
>    }
>    companion object {
>        fun newUUID() : String {
>            ...
>        }
>    }
>}
>```

### Inheritance

> w Kotlinie klazy mogą dziedziczyć i być dziedziczone 
> aby umożliwić dziedziczenie klasy, trzeba oznaczyć ją jako `open`.
>```kotlin
> open class ClassName
>```
> aby dziedziczyć od innej klasy, należy umieścić wybraną klase po podstawowym konstruktorze po `:`
>```kotlin
>class ClassName : OtherClass()
>```
> podczas dziedziczenia z innej klasy należy wywołać jej konstruktor, ale nie jest to konieczne w przypadku dziedziczenia z interfejsu
>```kotlin
>class ClassName : AnInterface
>```

### Abstract & Sealed

> w Kotlinie klasy i pola abstrakcyjne są definiowane z `abstract`
>```kotlin
>abstract class ClassName {
>    abstract fun function()
>}
>```
> Kotlin Sealed Classes nie pozwalają na wywołanie żadnego konstruktora spoza zakresu klasy
> są używane do tworzenia, jak sugeruje nazwa, zamkniętych klas bazowych dla obiektów i innych klas, na przykład.
>```kotlin
>sealed class WebErrorCode(val code: Int) {
>    object NotFound : WebErrorCode(404)
>    object BadRequest: WebErrorCode(400)
>    object Ok: WebErrorCode(200)
>}
>
>fun getResource() : WebErrorCode {
>    try {
>        ...
>    }
>    catch (e: FileNotFoundException) {
>        return WebErrorCode.NotFound
>    }
>    catch (e: Exception) {
>        return WebErrorCode.BadRequest
>    }
>    return WebErrorCode.Ok
>}
>
>when (getResource()) {
>    WebErrorCode.NotFound, WebErrorCode.BadRequest -> {
>            println("Unsuccessful")
>        }
>    WebErrorCode.Ok -> {
>            print("Successful")
>        }
>}
>```