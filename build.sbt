name := "student-data-handler"

version := "1.0"

lazy val `student-data-handler` = (project in file(".")).enablePlugins(PlayJava)

PlayKeys.externalizeResources := false

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( javaJdbc ,  cache , javaWs )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies += jdbc

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.5"

libraryDependencies += "org.json" % "org.json" % "chargebee-1.0"

libraryDependencies += "com.google.inject" % "guice" % "4.0"

libraryDependencies ++= Seq(javaJpa, "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final")

routesGenerator := InjectedRoutesGenerator

