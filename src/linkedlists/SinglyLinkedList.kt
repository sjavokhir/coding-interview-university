package linkedlists

fun main() {
    val list = SinglyLinkedList<Int>()
    list.print()

    list
        .pushBack(3)
        .pushBack(4)
        .pushBack(5)
        .also { it.print() }

    list.reverse().also { list.print() }
}

class SinglyLinkedList<T> {

    private var head: Node<T>? = null

    fun size(): Int {
        var currentNode = head
        var count = 0

        while (currentNode != null) {
            count++
            currentNode = currentNode.next
        }

        return count
    }

    fun isEmpty(): Boolean {
        return head == null
    }

    fun nodeAt(index: Int): Node<T>? {
        var currentNode = head
        var currentIndex = 0

        while (currentNode != null) {
            if (currentIndex == index) {
                return currentNode
            }

            currentNode = currentNode.next
            currentIndex++
        }

        return null
    }

    fun valueAt(index: Int): T? {
        return nodeAt(index)?.value
    }

    fun pushFront(value: T): SinglyLinkedList<T> {
        head = Node(value, head)
        return this
    }

    fun popFront(): T? {
        if (isEmpty()) return null
        val result = head?.value
        head = head?.next
        return result
    }

    fun pushBack(value: T): SinglyLinkedList<T> {
        if (isEmpty()) {
            pushFront(value)
            return this
        }

        val newNode = Node(value, null)
        var currentNode = head
        while (currentNode?.next != null) {
            currentNode = currentNode.next
        }
        currentNode?.next = newNode

        return this
    }

    fun popBack(): T? {
        if (isEmpty()) return null

        if (head?.next == null) return popFront()

        var currentNode = head
        var previousNode: Node<T>? = null

        while (currentNode?.next != null) {
            previousNode = currentNode
            currentNode = currentNode.next
        }

        val result = currentNode?.value
        previousNode?.next = null

        return result
    }

    fun front(): T? {
        return head?.value
    }

    fun back(): T? {
        var currentNode = head
        while (currentNode?.next != null) {
            currentNode = currentNode.next
        }
        return currentNode?.value
    }

    fun insert(index: Int, value: T) {
        if (index == 0) {
            pushFront(value)
            return
        }

        val afterNode = nodeAt(index - 1) ?: return
        val newNode = Node(value, afterNode.next)
        afterNode.next = newNode
    }

    fun erase(index: Int) {
        if (index == 0) {
            popFront()
            return
        }

        val afterNode = nodeAt(index - 1) ?: return
        afterNode.next = afterNode.next?.next
    }

    fun valueFromEnd(position: Int): T? {
        return valueAt(size() - position)
    }

    fun reverse() {
        var previousNode: Node<T>? = null
        var nextNode: Node<T>?
        var currentNode = head

        while (currentNode != null) {
            nextNode = currentNode.next
            currentNode.next = previousNode
            previousNode = currentNode
            currentNode = nextNode
        }

        head = previousNode
    }

    fun removeValue(value: T) {
        if (isEmpty()) return

        if (head?.value == value) {
            head = head?.next
            return
        }

        var currentNode = head
        var previousNode: Node<T>? = null

        while (currentNode != null) {
            if (currentNode.value == value) {
                previousNode?.next = currentNode.next
                return
            }
            previousNode = currentNode
            currentNode = currentNode.next
        }
    }

    fun print() {
        println("Size: ${size()}")
        println("Head: $head")
        println("-----------------------")
    }
}