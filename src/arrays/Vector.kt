package arrays

fun main() {
    val vector = Vector(8)

    vector.push(1)
    vector.push(3)
    vector.push(3)
    vector.push(5)
    vector.push(3)
    vector.push(11).also { vector.print() }

    vector.remove(3).also { vector.print() }
    vector.remove(11).also { vector.print() }
}

class Vector(
    private var capacity: Int,
) {

    private var data = IntArray(capacity)

    private var size = 0

    fun size(): Int {
        return size
    }

    fun capacity(): Int {
        return capacity
    }

    fun isEmpty(): Boolean {
        return size == 0
    }

    fun at(index: Int): Int {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index out of bounds")
        }

        return data[index]
    }

    fun push(item: Int) {
        if (capacity == size) {
            resize(capacity * 2)
        }

        data[size++] = item
    }

    fun insert(index: Int, item: Int) {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index out of bounds")
        }

        if (capacity == size) {
            resize(capacity * 2)
        }

        for (i in size downTo index + 1) {
            data[i] = at(i - 1)
        }

        data[index] = item
        size++
    }

    fun prepend(item: Int) {
        insert(0, item)
    }

    fun pop(): Int {
        if (size == 0) {
            throw NoSuchElementException("Vector is empty")
        }

        val lastItem = data[--size]

        data[size] = 0

        if (size > 0 && size == capacity / 4) {
            resize(capacity / 2)
        }

        return lastItem
    }

    fun delete(index: Int) {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index out of bounds")
        }

        for (i in index..<size - 1) {
            data[i] = data[i + 1]
        }

        size--
        data[size] = 0

        if (size > 0 && size == capacity / 4) {
            resize(capacity / 2)
        }
    }

    fun remove(item: Int) {
        var index = 0

        while (index < size) {
            if (data[index] == item) {
                delete(index)
            } else {
                index++
            }
        }
    }

    fun find(item: Int): Int {
        for (i in 0..<size) {
            if (data[i] == item) return i
        }

        return -1
    }

    private fun resize(capacity: Int) {
        val newStore = IntArray(capacity)

        for (i in 0..<size) {
            newStore[i] = data[i]
        }

        data = newStore
        this.capacity = capacity
    }

    fun print() {
        println("Vector: ${toString()}")
        println("Capacity: ${capacity()}")
        println("Size: ${size()}")
        println("-----------------------")
    }

    override fun toString(): String {
        return data.joinToString(", ")
    }
}