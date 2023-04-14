import java.util.PriorityQueue
import kotlin.properties.Delegates

// Node Declaration
internal abstract class MyHuffmanBaseNode {
    var data = 0
    operator fun compareTo(other: MyHuffmanBaseNode) : Int = other.data - this.data
}

internal class MyHuffmanNode : MyHuffmanBaseNode() {
    var left: MyHuffmanBaseNode? = null
    var right: MyHuffmanBaseNode? = null
    var code by Delegates.notNull<Int>()
}

internal class MyHuffmanNodeLeaf : MyHuffmanBaseNode() {
    var char by Delegates.notNull<Char>()
}
// Frequency table creation
internal fun getFreqTable(string: String) : Map<Char, Int> {
    return mutableMapOf<Char, Int>().apply {
        string.forEach { this[it] = (this[it]?:0) + 1 }
    }
}

// Hash Map Wrapper
internal fun createHuffmanTree(freqTable: Map<Char, Int>) : MyHuffmanBaseNode = createHuffmanTree(freqTable.keys, freqTable.values)

@OptIn(ExperimentalStdlibApi::class)
internal fun createHuffmanTree(
    charArray: Collection<Char>,
    freqArray: Collection<Int>
) : MyHuffmanBaseNode {
    // Initialize Priority Queue
    val queue = PriorityQueue(charArray.size, MyHuffmanBaseNode::compareTo)

    for (i in 0 ..< charArray.size) {
        queue.add(MyHuffmanNodeLeaf().apply {
            char = charArray.elementAt(i)
            data = freqArray.elementAt(i)
        })
    }
    // Create Huffman Tree
    while (queue.size > 1) {
        val first = queue.poll()
        val second = queue.poll()

        // Add new Node merging two least frequent elements
        queue.add(
            MyHuffmanNode().apply {
                left = first
                right = second
                data = first.data + second.data
            }
        )
    }
    // Get Root of a tree - last remaining element of a queue
    val root = queue.poll()
    // Assign Codes
    fun traverse(node: MyHuffmanNode) {
        (node.left as? MyHuffmanNode)?.let {
            it.code = 0 // 0 if left
            traverse(it)
        }
        (node.right as? MyHuffmanNode)?.let {
            it.code = 1 // 1 if right
            traverse(it)
        }
    }
    (root as? MyHuffmanNode)?.let { traverse(it) }
    return root
}

fun main() {
    val result = createHuffmanTree(
        getFreqTable(readln())
    )
    return
}