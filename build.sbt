version := "0.1"

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
	"org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
)
                  
publishMavenStyle := true

publishArtifact in Test := false

publishTo := Some(Resolver.file("file",  baseDirectory.value / "dist" ) )

