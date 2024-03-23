package linkedlists

class LinkedList<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0

    fun isEmpty(): Boolean {
        return size == 0
    }

    fun size(): Int {
        return size
    }

    fun push(value: T): LinkedList<T> {
        head = Node(value = value, next = head)
        if (tail == null) {
            tail = head
        }
        size++
        return this
    }

    fun append(value: T) {
        // 1
        if (isEmpty()) {
            push(value)
            return
        }

        // 2
        tail?.next = Node(value = value)

        // 3
        tail = tail?.next
        size++
    }

    fun insert(index: Int, value: T) {
        // 1
        if (index == 0) {
            push(value)
            return
        }

        // 2
        val afterNode = nodeAt(index - 1)
        val newNode = Node(value = value, next = afterNode?.next)
        afterNode?.next = newNode
        size++
    }

    fun insert(value: T, afterNode: Node<T>) {
        // 1
        if (tail == afterNode) {
            append(value)
            return
        }

        // 2
        afterNode.next = Node(value = value, next = afterNode.next)
        size++
    }

    fun pop() {
        head = head?.next
        if (head == null) {
            tail = null
        }
        size--
    }

    fun removeLast() {
        // 1
        val head = head ?: return

        // 2
        if (head.next == null) return pop()

        // 3
        size--

        // 4
        var prev = head
        var current = head
        var next = head.next

        while (next != null) {
            prev = current
            current = next
            next = current.next
        }

        // 5

        prev.next = null
        tail?.next = prev
    }

    fun removeAfter(node: Node<T>) {
        if (node.next == tail) {
            tail = node
        }

        if (node.next != null) {
            size--
        }

        node.next = node.next?.next
    }

    private fun nodeAt(index: Int): Node<T>? {
        // 1
        var currentNode = head
        var currentIndex = 0

        // 2
        while (currentNode != null) {
            if (currentIndex == index) {
                return currentNode
            }

            currentNode = currentNode.next
            currentIndex++
        }

        return null
    }

    override fun toString(): String {
        return if (isEmpty()) {
            "Empty list"
        } else {
            head.toString()
        }
    }
}