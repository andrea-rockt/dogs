

val typeclass = project.in(file("typeclass")).settings(
  name := "dogs-typeclass",
  libraryDependencies ++= Dependencies.all,
  Settings.compilerPlugins,
  Settings.compilerOptions,
  Settings.base
)

val examples = project.in(file("examples")).settings(
  name := "dogs-examples",
  libraryDependencies ++= Dependencies.all,
  Settings.compilerPlugins,
  Settings.compilerOptions,
  Settings.base
).dependsOn(typeclass)


val root = project.in(file(".")).settings(
  name := "dogs",  Settings.base
).aggregate(typeclass, examples)

