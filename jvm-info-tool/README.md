# jvm-info-tool

## Description

This is a small tool designed to extract information directly from the
running **JVM**. The features included are:

* The JVM version;
* The **JVM** location (**JAVA_HOME**);
* Ability to test if the **JVM** is newer than a given version;

## Usage

The usage of this tool is very simple:

```
$ java -jar jvm-info-tool.jar (<command>|<java_version_string>)
```

where ``<command>`` can be:

* ``-version``: Displays the JVM vesion;
* ``-home``: Displays the JAVA_HOME;

and ``<java_version_string>`` is a valid **JVM** version string (e.g.: x.y.z)
that must be equal to or smaller than the current **JVM** version.

As a general rule, this program always sets the exit code to 0 on success
and non-zero on failure. Success messages, if any, are printed on **stdout**
while error messages are printed to **stderr**.

## Compatibility and design principles

This program was designed to be as small as possible and be able to run
perfectly on any **JVM** equal or greater than 1.5.

## How to compile this program

This program can be built using **Apache Maven** 3.6 or greater. Thus it
requires at least **JDK** 1.7 to run. However, in order to keep the
compatibility with **JVM** 1.5, it is necessary to keep the **JDK** 
version below 9 as the support for 1.5 was removed.

Given that, we suggest the use of **JDK** 8 to compile this tool and ensure
compatibility with 1.5 by limiting the APIs used and testing it using a
real **JVM** 1.5.

### Note about obfuscation

The build process of this program obfuscates the code in order to make
it as small as possible. Thus, obfuscation parameters that may end up
increasing the code size are not used.

## Licensing

This program is licensed under a 3-Clause BSD license.
