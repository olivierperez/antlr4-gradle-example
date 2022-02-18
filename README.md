# Antlr4 + Gradle + Kotlin

Sample project that uses **Antlr4** to analyze a language code thanks to Kotlin.

The language parsed here is named "Chat", it is used to describe chat lines like:
```
Olivier says: LUL
Leo shouts: This is not fun
Olivier says: sorry
```

## Generate Antlr sources

`gradlew build` or just `gradlew generateGrammarSource`

## Versions

|Tool|Version|
|-|-|
|Antlr4|4.9.3|
|Gradle|7.4|
|Kotlin|1.6.10|
