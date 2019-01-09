package dogs.instances

import dogs.laws.discipline.MonadTests

class MonadSpec extends DogsSuite {




  checkAll("List", MonadTests[List].monad[String,String,String])
  checkAll("Vector", MonadTests[Vector].monad[String,String,String])

}
