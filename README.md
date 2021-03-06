:exclamation::exclamation::exclamation:
With the release of _[JUnit 5](https://junit.org/junit5/)_, the _[JUnit Lambda](http://junit.org/junit-lambda.html)_ became obsolete and so did this repo - see my [JUnit 5 demo project](https://github.com/nipafx/demo-junit-5) instead.

# Lab: JUnit-Lambda

On November 18th 2015, the _[JUnit Lambda](http://junit.org/junit-lambda.html)_ ptoject presented their first prototype.
I'm using this project to get to know the features and to demonstrate them.

:mega:
**For the demonstration have a look at [the test classes](https://github.com/CodeFX-org/lab-junitlambda/tree/master/src/test/java/org/codefx/lab/junitlambda).**
:mega:

## Version

Because the prototype is still changing a lot, this project depends on a specific build at any point in time.
This build number is hardocded in the `pom.xml`.

**Current Version**: :nine::five:

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
This way the surefire plugin is not used but that's no problem.
The runner will print a lot of colorful messages to the console.

The tests can simply be run with `mvn -P snapshots test`.

## Further Reading

A list of things you could read if you're interested in the topic:

* first and foremost, there is of course [the official documentation](https://github.com/junit-team/junit-lambda/wiki/Prototype)
* there will be a [JUnit tag on my blog](http://blog.codefx.org/tag/junit/) collecting my posts about this

## Caveat Lector

Note that _JUnit Lambda_ is actively developed and very much a moving target.
Anything presented here must be carefully reexamined before basing any opinions or even decisions on it.
