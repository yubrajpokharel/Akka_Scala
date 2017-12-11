name := "HelloAkka"

version := "1.0"

scalaVersion := "2.12.1"

resolvers += "Typesage Repository" at
    "http://repo.typesafe.com/typesage/releases/"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.12"
