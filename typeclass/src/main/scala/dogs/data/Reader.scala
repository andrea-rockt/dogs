package dogs.data

case class Reader[A, B](run: A => B)


