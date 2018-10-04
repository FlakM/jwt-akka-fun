package com.example

object JwtFun extends App {

  import pdi.jwt._

  val token ="eyJhbGciOiJSUzI1NiIsImtpZCI6ImE5Y2RmY2ZkZTEzOTUyOWViYzg2ODNkMDFhODFiOGY0ODA5MTRiZTAifQ.eyJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjU1NTYvZGV4Iiwic3ViIjoiQ2djeE9EQTVNekl4RWdabmFYUnNZV0kiLCJhdWQiOiJleGFtcGxlLWFwcCIsImV4cCI6MTUzODY2NzcyMywiaWF0IjoxNTM4NTgxMzIzLCJhdF9oYXNoIjoiMlZZT1hoTGQ3Y1gzV2kzS1FMNndHZyIsImVtYWlsIjoibWFjaWVqLmphbi5mbGFrQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoiRmxha00ifQ.p_sNlK4OJCwiL--DGHxNHazwK0pkj2wXAvEfhwVw-5I9geRCQMXp6aYmWlPyFlSzj2qMdivdPrT9hSB7Qhusd6Xla0bkGHOW4voA4fT3n7W1myiMr1whmlUtSRJPiFgSVtdTOgxqWS8c6P4zWnu2onE3VWpOrfwiW3BObhse89P9AWSDjGfUeGno3gyb2VRBPX6D1-dS21Y78O1DahXw4MmTTye0CFgSmn5TAxTkRfxtfZnWNi81zsIeetiK67AVFTbh3wHg5WV6OufrTFzIrs5SgkczHXIGn0FAmi_LGUo5UrayuwHBZU7bbfiyL3CXksDoFjN--5sWQxKb0YtEDQ"

  val publicKey = "xpXIOfL9TKTe5kCIkCXHk1ooScffBgGq3OIMf7OKIo_fucqsSfC_mc7oziLpQH2hzJZ5ZDNHpjesxoKYLOmIi0uFxvOzFryVwPaBIcjiCdm6wIm3M5bHmdeKBsu_VQa_DnAMCq6dKf9jau_2IWMOL32MjreXu_2YmO_gFizaKuh1yxY-nbC4Z-rkVzLHCGkYZ8lhHUTprc5Fy_Twuwsi79dhaMSe9dUCp7rBAXTa90Urxs7bB7Ya9DPc17WOcHpOp39cXvspZnERm14brLBeutYieuzPKqbJFXjXB8GsyTWqrG167X6ihPCfkru893qHtCz8D8Q1J8Ch7IsHS5UJ7w"


  import java.security.{KeyFactory, PublicKey}
  import java.security.spec.RSAPublicKeySpec

  def buildRSAPublicKey(): PublicKey = {
    val modulus: java.math.BigInteger = new java.math.BigInteger(1, JwtBase64.decode(publicKey)) // n param in json
    val publicExponent = new java.math.BigInteger(1, JwtBase64.decode("AQAB")) // e param in json
    KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus, publicExponent))
  }


  val (header, claim, signature) = JwtJson4s.decodeRawAll(token, buildRSAPublicKey(), Seq(JwtAlgorithm.RS256)).get
  //val (header, claim, signature) = JwtJson4s.decodeAll(token, buildRSAPublicKey(), Seq(JwtAlgorithm.RS256)).get

  println(header)
  println(claim)
  println(signature)


}
