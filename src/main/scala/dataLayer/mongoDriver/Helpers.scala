package dataLayer.mongoDriver

import org.mongodb.scala.{Document, Observable, ObservableFuture}

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Helpers:

  /**
   * Classe implicit per convertire un oggetto di tipo Observable[Generics] in String
   * @param observable
   * @tparam C
   */
  implicit class GenericObservable[C](val observable: Observable[C]) extends ImplicitObservable[C] :
    override val converter: C => String = doc => doc.toString

  /**
   * Abstract class to manage the asynchronous processing of an Observable object and convert its result in String
   * @tparam C
   */
  trait ImplicitObservable[C]:

    val observable: Observable[C]
    val converter: (C) => String

    /**
     * This method retrieves data from database from the observable received in input by its class constructor. It returns a Seq[C] object filled with data stored in the database
     * @return a Seq[C] of data retrieved from the database
     */
    def results(): Seq[C] = Await.result(observable.toFuture(), Duration(10, TimeUnit.SECONDS))

    /**
     * This method computes the Seq[C] and converts each element in String
     * @param initial
     * @return the data in the Seq[C] converted in String 
     */
    def results(initial: String = ""): String =
      var result = ""
      if initial.length > 0 then print(initial)
      results().foreach(res => result += converter(res) + "\n")
      result

