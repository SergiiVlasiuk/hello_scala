package example.partial_function

object FunctionCompositionTraining {
  //val fComposeG: String => String = f _ compose g _
  val fComposeG: String => String = f _ compose g
  //val fAndThenG = f _ andThen g _
  val fAndThenG = f _ andThen g

  val gAndThenH = g _ andThen h
  val gComposeH = g _ compose h

  val fAndThenGAndThenH: String => String = f _ andThen g andThen h
  val fAndThen_gAndThenH: String => String = f _ andThen gAndThenH
  val fAndThenG_AndThenH: String => String = fAndThenG andThen h

  val fAndThenGComposeH = f _ andThen g compose h
  val fAndThenG_ComposeH = fAndThenG compose h
  val fAndThen_gComposeH = f _ andThen gComposeH

  val fComposeGAndThenH = f _ compose g andThen h
  val fComposeG_AndThenH = fComposeG andThen h
  val fCompose_gAndThenH = f _ compose gAndThenH

  val fComposeGComposeH = f _ compose g compose h
  val fComposeG_ComposeH = fComposeG compose h
  val fCompose_GComposeH = f _ compose gComposeH
  //val fAndThenG: String => String = f _ andThen g

  def main(args: Array[String]): Unit = {
    println(fComposeG("  f _ compose g _  "))
    println(fAndThenG("  f _ andThen g _  "))
    println(gAndThenH("  g _ andThen h _  "))
    println(gComposeH("  g _ compose h _  "))
    println
    println(fAndThenGAndThenH("  f _ andThen g andThen h  "))
    println(fAndThen_gAndThenH("  f _ andThen gAndThenH  "))
    println(fAndThenG_AndThenH("  fAndThenG andThen h  "))
    println
    println(fAndThenGComposeH("  f _ andThen g compose h  "))
    println(fAndThenG_ComposeH("  fAndThenG compose h  "))
    println(fAndThen_gComposeH("  f _ andThen gComposeH  "))
    println
    println(fComposeGAndThenH("  f _ compose g andThen h  "))
    println(fComposeG_AndThenH("  fComposeG andThen h  "))
    println(fCompose_gAndThenH("  f _ compose gAndThenH  "))
    println
    println(fComposeGComposeH("  f _ compose g compose h  "))
    println(fComposeG_ComposeH("  fComposeG compose h  "))
    println(fCompose_GComposeH("  f _ compose gComposeH  "))
  }

  def f(s: String) = s" f($s) "
  def g(s: String) = s" g($s) "
  def h(s: String) = s" h($s) "
}
/*
 f( g(  f _ compose g _  ) )
 g( f(  f _ andThen g _  ) )
 h( g(  g _ andThen h _  ) )
 g( h(  g _ compose h _  ) )

 h( g( f(  f _ andThen g andThen h  ) ) )
 h( g( f(  f _ andThen gAndThenH  ) ) )
 h( g( f(  fAndThenG andThen h  ) ) )

 g( f( h(  f _ andThen g compose h  ) ) )
 g( f( h(  fAndThenG compose h  ) ) )
 g( h( f(  f _ andThen gComposeH  ) ) )

 h( f( g(  f _ compose g andThen h  ) ) )
 h( f( g(  fComposeG andThen h  ) ) )
 f( h( g(  f _ compose gAndThenH  ) ) )

 f( g( h(  f _ compose g compose h  ) ) )
 f( g( h(  fComposeG compose h  ) ) )
 f( g( h(  f _ compose gComposeH  ) ) )
 */
