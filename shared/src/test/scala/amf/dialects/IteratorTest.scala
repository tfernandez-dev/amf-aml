package amf.dialects
import amf.core.model.domain.DomainElement
import amf.core.remote._
import org.scalatest.Matchers

import scala.concurrent.ExecutionContext


trait IteratorTest extends DialectTests with Matchers {

  val basePath = "shared/src/test/resources/vocabularies2/instances/"
  override implicit val executionContext: ExecutionContext = ExecutionContext.Implicits.global


  test("Domain element iterator in self encoded dialect instance") {
    parseInstance("dialect23.raml", "example23.raml", VocabularyYamlHint).map(baseUnit => {
      val ids =
      baseUnit.iterator().collect { case e: DomainElement => e.id }.toStream
      ids should contain inOrderOnly
        ("file://shared/src/test/resources/vocabularies2/instances/example23.raml",
        "file://shared/src/test/resources/vocabularies2/instances/example23.raml#/lets")
    })
  }

  test("findById in self encoded dialect instance") {
    parseInstance("dialect23.raml", "example23.raml", VocabularyYamlHint).map(baseUnit => {
      assert(baseUnit.findById("file://shared/src/test/resources/vocabularies2/instances/example23.raml#/lets").isDefined)
    })
  }



}
