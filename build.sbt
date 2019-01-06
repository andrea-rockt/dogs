

val typeclass = project.in(file("typeclass")).settings(
  name := "dogs-typeclass",
  libraryDependencies ++= Dependencies.all,
  Settings.compilerPlugins,
  Settings.compilerOptions,
  Settings.base
)

val root = project.in(file(".")).settings(
  name := "dogs",  Settings.base
).aggregate(typeclass)

