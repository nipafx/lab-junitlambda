# Lab: JUnit-Lambda

_Toying around with the JUnit Lambda Prototype_

---

On November 18th _[JUnit Lambda](http://junit.org/junit-lambda.html)_ presented their first [prototype](https://github.com/junit-team/junit-lambda/wiki/Prototype).
I'm using this project to get to know the features and to demonstrate them.

## Compiling & Running

The prototype is only available in [Sonatype's snapshots repository](https://oss.sonatype.org/content/repositories/snapshots/org/junit/prototype/) and by default Maven will not access it.
Adding the following [profile](http://maven.apache.org/guides/introduction/introduction-to-profiles.html) to your `settings.xml` will allow you to instruct Maven to do just that:

```xml
<profile>
	<id>snapshots</id>
	<activation>
		<activeByDefault>false</activeByDefault>
	</activation>
	<repositories>
		<repository>
			<id>snapshots-repo</id>
			<url>https://oss.sonatype.org/content/repositories/snapshots</url>
			<releases>
				<enabled>false</enabled>
			</releases>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</repository>
	</repositories>
</profile>
```

This project should then compile with `mvn -P snapshots compile`.

The prototype [must be executed](https://github.com/junit-team/junit-lambda/wiki/Prototype-Running-Tests) with a bare-bones console runner and it is straight-forward to have Maven do that.
This way the surefire plugin is not used but that's no problem. The runner will print a lot of colorful messages to the console.

The tests can simply be run with `mvn test`.

## Further Reading

A list of things you could read if you're interested in the topic:

* first and foremost, there is of course [the official documentation](https://github.com/junit-team/junit-lambda/wiki/Prototype)
* there will be a [JUnit tag on my blog](http://blog.codefx.org/tag/junit/) collecting my posts about this

## Caveat Lector

Note that _JUnit Lambda_ is actively developed and very much a moving target.
Anything presented here must be carefully reexamined before basing any opinions or even decisions on it.
