val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "project",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,
    resolvers += "Maven Central" at "https://repo1.maven.org/maven2",


    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    // dodajemo: (rucno nadjen link na osnovu datog "https://repo1.maven.org/maven2/org/scalafx/scalafx_3/16-R25/scalafx_3-16-R25.jar")
    libraryDependencies += "org.scalafx" %% "scalafx" % "16.0.0-R25",
    libraryDependencies ++= {
      // Determine OS version of JavaFX binaries
      lazy val osName = System.getProperty("os.name") match {
        case n if n.startsWith("Linux") => "linux"
        case n if n.startsWith("Mac") => "mac"
        case n if n.startsWith("Windows") => "win"
        case _ => throw new Exception("Unknown platform!")
      }
      Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
        .map(m => "org.openjfx" % s"javafx-$m" % "16" classifier osName)
    }
  )
