<h1 align="center">
  vamzer
</h1>

<h3 align="center">
  JVM CLI and library. | Quickly check if a string is a secret.
</h3>

<p align="center">
  <a href="https://github.com/chreeio/vamzer/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/chreeio/vamzer" alt="vamzer is distributed under The MIT License.">
  </a>
  <a href="https://github.com/chreeio/vamzer/actions?query=workflow%3A%22Build+Master%22">
    <img src="https://github.com/chreeio/vamzer/workflows/Build%20Master/badge.svg" alt="Master build status.">
  </a>
</p>

<h3 align="center">
  <a href="#Installation">Installation</a>
  <span> · </span>
  <a href="#Quickstart">Quickstart</a>
  <span> · </span>
  <a href="docs/README.md">Documentation</a>
</h3>

---

## Features

  * **Entire String Checking**. The built-in checkers will examine if the entire string is a secret, but not its substrings. For the rationale behind this behavior, please see the [Motivation](#motivation) section.
  * **Configurable Built-in Checkers**. vamzer offers a wide array of configurable checkers out of the box. Please refer to the [docs/built-in-checkers.md](docs/built-in-checkers.md) document for the available checkers and their usage.
  * **Custom Checkers**. You can use your own checkers with minimal effort.
  * **Multiplatform**. vamzer is a [Kotlin Multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html) library, with most of its code written in Common Kotlin.

## Installation

vamzer is available through the GitHub Package Registry, therefore, make sure to setup whatever package client you're using to be able to consume packages from the following repository:

~~~~
https://maven.pkg.github.com/chreeio/vamzer
~~~~

You can find more information regarding the configuration steps [here](https://docs.github.com/en/free-pro-team@latest/packages/using-github-packages-with-your-projects-ecosystem).

Then simply add the appropriate dependency:

  * [Maven POM](https://maven.apache.org/)
    ~~~~XML
    <dependency>
        <groupId>io.chree.vamzer</groupId>
        <artifactId>vamzer</artifactId>
        <version>1.0.0</version>
    </dependency>
    ~~~~
  * [Gradle Kotlin DSL](https://github.com/gradle/kotlin-dsl)
    ~~~~Kotlin
    implementation("io.chree.vamzer:vamzer:1.0.0")
    ~~~~
  * [Gradle Groovy DSL](https://gradle.org/)
    ~~~~Groovy
    implementation 'io.chree.vamzer:vamzer:1.0.0'
    ~~~~

## Quickstart

In the following example, we will execute all available checkers with their default configuration:

~~~~Java
import io.chree.vamzer.Vamzer;
import io.chree.vamzer.checker.SecretCheckResult;

class Application {
    public static void main(String[] args) {
        SecretCheckResult result = Vamzer.usingMultipleCheckers(Vamzer.Checkers.all())
                .checkIfSecret("The value you want to check.");

        result.throwIfSecretDetected();
    }
}
~~~~

When checking multiple values with the same set of checkers, you can prevent unnecessary instantiations using the following method:

~~~~Java
import io.chree.vamzer.Vamzer;
import io.chree.vamzer.checker.SecretCheckResult;
import io.chree.vamzer.checker.SecretChecker;

class Application {
    public static void main(String[] args) {
        SecretChecker checker = Vamzer.usingMultipleCheckers(Vamzer.Checkers.all());

        for (var value : List.of("a", "b", "c")) {
            SecretCheckResult result = checker.checkIfSecret(value);

            result.throwIfSecretDetected();
        }
    }
}
~~~~

## Motivation

[Chree](https://chree.io) is a continuous benchmarking platform, collecting the output of user-written benchmarks, called experiments. Such experiments consist of the following: input parameters, recorded metrics and metadata, called tags. A tag is essentially a typed key-value pair, for example: `git.branch: main`.

Tag values can come from three sources: hard-wired literal values, environment variables or command outputs. While the first one is somewhat safe, the latter two come with the risk of accidentally uploading some secret as a tag value. As we wanted to protect our users from such accidents, we decided to integrate secret checking into our experiment push pipeline.

Instead of reinventing the wheel, we first checked GitHub for existing solutions. The ones we found, however, are focused on scanning for secrets in files, git repositories or even git diffs (for example, the excellent [Yelp/detect-secrets](https://github.com/Yelp/detect-secrets) tool). Unfortunately, these libraries are not aligned with our requirements, as our use case features strings that might themselves be secrets. Therefore, we decided to create our own solution.

## Naming

As this library will happily inform you if a value is a secret, it should be no suprise that the word [*vamzer*](https://en.wiktionary.org/wiki/vamzer) is a Hungarian slang term meaning *snitch*.

## Acknowledgements

This is an open source project maintained by [Chree](https://chree.io). For more projects maintained or supported by Chree, please head over to the [Chree Open Source page](https://opensource.chree.io).

## Licence

vamzer is licensed under [The MIT License](LICENSE).
