##Arithmetic

For the next section, we're going to take a different tack with the solutions. We'll declare a new class, S99Int, and an implicit conversion from regular Ints. The arithmetic1 file contains the starting definitions for this section. Each individual solution will show the relevant additions to the S99Int class. The full class will be given at the end of the section.

###P31 (**) Determine whether a given integer number is prime.

    scala> 7.isPrime
    res0: Boolean = true

###P32 (**) Determine the greatest common divisor of two positive integer numbers.
    Use Euclid's algorithm.

    scala> gcd(36, 63)
    res0: Int = 9

###P33 (*) Determine whether two positive integer numbers are coprime.
    Two numbers are coprime if their greatest common divisor equals 1.

    scala> 35.isCoprimeTo(64)
    res0: Boolean = true

###P34 (**) Calculate Euler's totient function phi(m).
    Euler's so-called totient function phi(m) is defined as the number of positive integers r (1 <= r <= m) that are coprime to m.

    scala> 10.totient
    res0: Int = 4

###P35 (**) Determine the prime factors of a given positive integer.
    Construct a flat list containing the prime factors in ascending order.

    scala> 315.primeFactors
    res0: List[Int] = List(3, 3, 5, 7)

###P36 (**) Determine the prime factors of a given positive integer (2).
    Construct a list containing the prime factors and their multiplicity.

    scala> 315.primeFactorMultiplicity
    res0: List[(Int, Int)] = List((3,2), (5,1), (7,1))

    Alternately, use a Map for the result.

    scala> 315.primeFactorMultiplicity
    res0: Map[Int,Int] = Map(3 -> 2, 5 -> 1, 7 -> 1)

###P37 (**) Calculate Euler's totient function phi(m) (improved).
    See problem P34 for the definition of Euler's totient function. If the list of the prime factors of a number m is known in the form of problem P36 then the function phi(m>) can be efficiently calculated as follows: Let [[p1, m1], [p2, m2], [p3, m3], ...] be the list of prime factors (and their multiplicities) of a given number m. Then phi(m) can be calculated with the following formula:

    phi(m) = (p1-1)*p1(m1-1) * (p2-1)*p2(m2-1) * (p3-1)*p3(m3-1) * ...

    Note that ab stands for the bth power of a.
###P38 (*) Compare the two methods of calculating Euler's totient function.
    Use the solutions of problems P34 and P37 to compare the algorithms. Try to calculate phi(10090) as an example.
###P39 (*) A list of prime numbers.
    Given a range of integers by its lower and upper limit, construct a list of all prime numbers in that range.

    scala> listPrimesinRange(7 to 31)
    res0: List[Int] = List(7, 11, 13, 17, 19, 23, 29, 31)

###P40 (**) Goldbach's conjecture.
    Goldbach's conjecture says that every positive even number greater than 2 is the sum of two prime numbers. E.g. 28 = 5 + 23. It is one of the most famous facts in number theory that has not been proved to be correct in the general case. It has been numerically confirmed up to very large numbers (much larger than Scala's Int can represent). Write a function to find the two prime numbers that sum up to a given even integer.

    scala> 28.goldbach
    res0: (Int, Int) = (5,23)

###P41 (**) A list of Goldbach compositions.
    Given a range of integers by its lower and upper limit, print a list of all even numbers and their Goldbach composition.

    scala> printGoldbachList(9 to 20)
    10 = 3 + 7
    12 = 5 + 7
    14 = 3 + 11
    16 = 3 + 13
    18 = 5 + 13
    20 = 3 + 17

    In most cases, if an even number is written as the sum of two prime numbers, one of them is very small. Very rarely, the primes are both bigger than, say, 50. Try to find out how many such cases there are in the range 2..3000.

    Example (minimum value of 50 for the primes):

    scala> printGoldbachListLimited(1 to 2000, 50)
    992 = 73 + 919
    1382 = 61 + 1321
    1856 = 67 + 1789
    1928 = 61 + 1867

The file containing the full class for this section is arithmetic.scala.
