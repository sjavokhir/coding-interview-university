package linkedlists

fun main() {
    val list = SinglyLinkedList<Int>()
    list.print()

    list
        .pushBack(3)
        .pushBack(4)
        .also { it.print() }

    list.valueFromEnd(2).also { println(it) }
}

data class ListNode<T>(
    val data: T,
    var next: ListNode<T>? = null
)

class SinglyLinkedList<T> {

    private var head: ListNode<T>? = null

    private var size = 0

    fun size(): Int {
        return size
    }

    fun isEmpty(): Boolean {
        return size == 0
    }

    fun nodeAt(index: Int): ListNode<T>? {
        if (index < 0 || index >= size) return null

        var currentNode = head
        var currentIndex = 0

        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }

        return currentNode
    }

    fun valueAt(index: Int): T? {
        return nodeAt(index)?.data
    }

    fun pushFront(value: T): SinglyLinkedList<T> {
        head = ListNode(value, head)
        size++
        return this
    }

    fun popFront(): T? {
        if (isEmpty()) return null
        val result = head?.data
        head = head?.next
        size--
        return result
    }

    fun pushBack(value: T): SinglyLinkedList<T> {
        if (isEmpty()) {
            pushFront(value)
            return this
        }

        val lastNode = nodeAt(size - 1)
        val newNode = ListNode(value, null)

        lastNode?.next = newNode
        size++

        return this
    }

    fun popBack(): T? {
        if (size == 1) {
            return popFront()
        }

        val node = nodeAt(size - 2) ?: return null
        val result = node.next?.data
        node.next = null
        size--
        return result
    }

    fun front(): T? {
        return head?.data
    }

    fun back(): T? {
        return valueAt(size - 1)
    }

    fun insert(index: Int, value: T) {
        val afterNode = nodeAt(index - 1) ?: return
        val newNode = ListNode(value, afterNode.next)

        afterNode.next = newNode
        size++
    }

    fun erase(index: Int) {
        if (index == 0) {
            popFront()
            return
        }

        val node = nodeAt(index - 1) ?: return
        node.next = node.next?.next
        size--
    }

    fun valueFromEnd(position: Int): T? {
        return valueAt(size - position)
    }

    fun reverse() {

    }

    fun print() {
        println("Size: ${size()}")
        println("Head: $head")
        println("-----------------------")
    }
}