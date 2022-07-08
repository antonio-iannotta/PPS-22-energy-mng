package dataLayer.mongoDriver

import org.mongodb.scala.{Document, Observable, ObservableFuture}

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Helpers:

  implicit class DocumentObservable[C](val observable: Observable[Document]) extends ImplicitObservable[Document] :
    override def converter(doc: Document): String = doc.toJson

  implicit class GenericObservable[C](val observable: Observable[C]) extends ImplicitObservable[C] :
    override def converter(doc: C): String = doc.toString
  
  trait ImplicitObservable[C]:

    val observable: Observable[C]
    val converter: (C) => String

    def results(): Seq[C] = Await.result(observable.toFuture(), Duration(10, TimeUnit.SECONDS))

    def results(initial: String = ""): String =
      var result = ""
      results().foreach(res => result += converter(res) + "\n")
      result

