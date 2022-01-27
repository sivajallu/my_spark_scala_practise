package week8

object Program1 {

  def showEmpDetails(sno: Int, name: String, company: String): Unit = {
    //println(" sno = " + sno + " name = " + name + " company = " + company);
    val formattedString = f" sno = $sno name = $name  company = $company"
    val str = raw"hello how are you \n are you good";
    println(formattedString)
    println(str)
  }

  def compareTwoStrings(first: String, second: String): Boolean = {
    var returnVal = false;
    if (first == second) {
      returnVal = (first == second);
    }
    return returnVal;
  }

  def main(args: Array[String]): Unit = {
    val sno: Int = 1781468;
    val name: String = "Sivakumar Jallu";
    val company = "Wells fargo";

    //showEmpDetails(sno, name, company);
    val returnVal = compareTwoStrings("siva", "siva")

    if (returnVal) {
      println("both are same strings")
    } else println("both are not same strings")

    returnVal match {
      case true => println("both are same strings")
      case false => println("both are not same strings")
      case _ => println("None")
    }
    displayStarsDesc();
    println(executeBlock());
    println({
      var x = 10; x + 20
    })
  }

  def displayStarsAsc(): Unit = {
    for (x <- 1 to 5) {
      println("")
      for (y <- 1 to x) {
        print(" * ")
      }
    }
  }

  def executeBlock() = {
    val stat = {
      val x = 10;
      x + 20;
      56
    }
    stat;
  }

  def displayStarsDesc(): Unit = {
    var count = 0
    val total = 5;
    var x = 0
    for (x <- 1 to total) {
      count = count + 1
      for (y <- 1 to total - x) {
        print("   ")
      }
      for (z <- 1 to count) {
        print(" * ")
      }
      println("")
    }
  }
}
