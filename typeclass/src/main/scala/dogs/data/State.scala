package dogs.data

case class State[S, A](run: S => (S,A))
