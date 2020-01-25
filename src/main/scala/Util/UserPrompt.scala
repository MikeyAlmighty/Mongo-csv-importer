package Util

import Util.File.FileHelper
import Util.Models.UserInput
import cats.data.EitherT
import cats.implicits._
import monix.eval.Task
import Util.Logging.log

object UserPrompt {

  /** Prompts a user for input
    *
    * @return UserInput object
    */
  def promptUser(): Task[Either[Exception, UserInput]] = {
    try {
      log.debug("Prompting user for input...")
      println("Please enter the path to the csv files: ")
      (for {
        directory <- EitherT.fromEither[Task](FileHelper.toDirectory(scala.io.StdIn.readLine()))
        skipItems <- EitherT.fromEither[Task](addSkipItems(List.empty[String]))
        res       <- EitherT(Task.wanderUnordered(skipItems)(FileHelper.findFile(directory, _)).map { items =>
          val (errors, files) = items.separate
          val userInput = UserInput(directory, files)
          errors.headOption.toLeft(userInput)
        })
      } yield res).value

    } catch {
      case e: Exception =>
        val message = s"Could not read user input: ${e.getMessage}"
        log.error(message)
        Task.now(Left(new Exception(message)))
    }
  }

  /** Prompts the user to add files to skip
    *
    * @param inputItems the items that will skipped
    * @return skipped items list
    */
  def addSkipItems(inputItems: List[String]): Either[Exception, List[String]] = {
    try {
      log.debug("Prompting user to enter files to skip")
      println("Please enter any files to skip (enter :q to exit) eg. filename.extension: ")
      val input = scala.io.StdIn.readLine()
      if (input.equals(":q"))
        Right(inputItems)
      else addSkipItems(inputItems :+ input)
    } catch {
      case e: Exception =>
        val message = "Could not read user input: "+e.getMessage
        log.error(message)
        Left(new Exception(message, e))
    }
  }

}
