## Multi JAR vs. Uberjar Handling of Resource Conflicts

This is an example project to complement an [article on JVM Deployment Strategies](https://worace.works/2021/04/13/jar-hell-part-2-jvm-deployment-strategies/).

It shows an example of dealing with a _resource file_ collision between 2 libraries in an Uberjar vs. Multi JAR context.

Project Structure:

```
tree .
.
├── app
│   ├── build.sbt
│   ├── project
│   │   ├── build.properties
│   │   ├── plugins.sbt
│   │   └── project
│   └── src
│       └── main
│           └── scala
│               └── com
│                   └── example
│                       └── App.scala
├── lib-a
│   ├── build.sbt
│   ├── project
│   │   └── build.properties
│   └── src
│       └── main
│           ├── resources
│           │   └── pizza.txt
│           └── scala
│               └── com
│                   └── example
│                       └── liba
│                           └── LibA.scala
├── lib-b
│   ├── build.sbt
│   ├── project
│   │   └── build.properties
│   └── src
│       └── main
│           ├── resources
│           │   └── pizza.txt
│           └── scala
│               └── com
│                   └── example
│                       └── libb
│                           └── LibB.scala
├── README.md
└── script.sh

26 directories, 14 files
```

`./app` is a very simple example application, and `lib-a` and `lib-b` are example libraries that we simulate by publishing them into the local Ivy cache.

`./script.sh` is a test script which publishes the 2 libraries locally, runs the app project via sbt (multi JAR Classpath) and then attempts to build an uberjar from it.

### Takeaways

The problem of resource collisions (i.e. 2 JARs containing resources of the same file name) doesn't go away when using a multi-JAR classpath configuration. Rather, you will simply silently get one of them at runtime (probably the first one) with others being ignored.

Building an uberjar has the benefit of moving this problem to build time, and ideally warning you about it in some way (for example the sbt-assembly plugin throws an error on this collision if you don't handle it).

#### Loading Single vs. Multi Resources

Like most people I have usually used the `public URL getResource(String name)` API on [java.lang.Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html#getResource-java.lang.String-). This returns a single URL to a resource from the classpath, relying on the "first one wins" behavior described above.

A reddit user also pointed out the ` public Enumeration<URL> getResources(String name)` API on [java.lang.ClassLoader](https://docs.oracle.com/javase/8/docs/api/java/lang/ClassLoader.html#getResources-java.lang.String-). This gives a list of resources matching a filename. However I think in practice it can be tricky to work with the URLs as they point to nested paths within Zipped JAR archives.

### Example Run Output

```
./script.sh
*** Running Example Script ***


*** Publishing lib-a to local ~/.ivy2 ***
# ...
[info]  published ivy to /home/worace/.ivy2/local/com.example/lib-a_2.12/0.1.0-SNAPSHOT/ivys/ivy.xml


*** Publishing lib-b to local ~/.ivy2 ***
# ...
[info]  published ivy to /home/worace/.ivy2/local/com.example/lib-b_2.12/0.1.0-SNAPSHOT/ivys/ivy.xml


*** Running App Example Via SBT run (multi JAR) ***
[info] running com.example.App
*** Running Example App.scala ***
*** Reading pizza.txt as Resource from lib-a ***
Lib A Pizza.txt Resource
# PROBLEM: the code from lib-b sees the resource file from lib-a
*** Reading pizza.txt as Resource from lib-b ***
Lib A Pizza.txt Resource


*** Building Assembly JAR ***
---> This will fail because no merge strategy is set for pizza.txt <---
# ...
[error] 1 error was encountered during merge
[error] java.lang.RuntimeException: deduplicate: different file contents found in the following:
```
