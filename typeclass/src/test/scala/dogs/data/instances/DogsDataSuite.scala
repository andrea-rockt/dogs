package dogs.data.instances

import dogs.instances.DogsSuite

trait DogsDataSuite extends DogsSuite with AllInstances {
  type SomeD = String

  type SomeA = String
  type SomeB = Int
  type SomeC = Double

  type Fixed = String
}