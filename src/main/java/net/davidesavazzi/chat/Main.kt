package net.davidesavazzi.chat

import net.davidesavazzi.mylanguage.ChatBaseListener
import net.davidesavazzi.mylanguage.ChatLexer
import net.davidesavazzi.mylanguage.ChatParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

fun main() {
    val content = """Olivier says: LUL
        |Leo shouts: This is not fun
        |Olivier says: sorry
    """.trimMargin()

    val lexer = ChatLexer(CharStreams.fromString(content))
    val tokens = CommonTokenStream(lexer)
    val parser = ChatParser(tokens)
    val parseTree = parser.chat()

    val listener = object : ChatBaseListener() {
        // TODO Implement it
    }

    val treeWalker = ParseTreeWalker()
    treeWalker.walk(listener, parseTree)
}
