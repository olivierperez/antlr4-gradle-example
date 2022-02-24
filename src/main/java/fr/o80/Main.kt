package fr.o80

import fr.o80.kotlin.KotlinLexer
import fr.o80.kotlin.KotlinParser
import fr.o80.kotlin.KotlinParserBaseListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

fun main() {
    val content = """package fr.test

class Toto {
    val tata = Tata()
    fun doSomething() {
        println("toto")
        tata.dodo()
    }
}
class Tata {
    fun dodo() {
    }
}

    """

    val lexer = KotlinLexer(CharStreams.fromString(content))
    val tokens = CommonTokenStream(lexer)
    val parser = KotlinParser(tokens)
    val parseTree = parser.kotlinFile()

    val packageHeader = parseTree.preamble().packageHeader().takeIf { it.childCount > 0 }

    if (packageHeader != null) {
        val packageName = packageHeader.identifier().text
        println("preamble: package $packageName")
    }
    parseTree.topLevelObject().filter { it.classDeclaration() != null }.forEach {
        println("Class: ${it.classDeclaration().simpleIdentifier().text}")
    }

    val listener = object : KotlinParserBaseListener() {

        private var inFunction = false

        override fun enterFunctionBody(ctx: KotlinParser.FunctionBodyContext) {
            inFunction = true
        }

        override fun enterPropertyDeclaration(ctx: KotlinParser.PropertyDeclarationContext) {
            println("enterPropertyDeclaration: ${ctx.variableDeclaration().text}")
            println("enterPropertyDeclaration: ${ctx.expression().text}")
        }

        override fun enterExpression(ctx: KotlinParser.ExpressionContext) {
            println("assignment? ${ctx.assignmentOperator()}")
            println("expression: line ${ctx.start.line} ${ctx.text}")
        }

        override fun exitFunctionBody(ctx: KotlinParser.FunctionBodyContext) {
            inFunction = false
        }
    }
    val treeWalker = ParseTreeWalker()
    treeWalker.walk(listener, parseTree)
}
