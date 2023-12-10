package encrypt

import encrypt.Tables.E
import encrypt.Tables.G
import encrypt.Tables.H
import encrypt.Tables.IP
import encrypt.Tables.P
import encrypt.Tables.reverseIP
import encrypt.Tables.sBlocks
import encrypt.Tables.shiftTable

@OptIn(ExperimentalUnsignedTypes::class)
class DesEcbImpl : DesEcb {
    override fun encrypt(bytes: UByteArray, key: UByteArray): UByteArray {
        var message = bytes.take(8).toUByteArray()
        message = replace64(message, IP)
        println("replaced64 message: ${message.toBytes()} +")

        val sliced = separate(message)
        var L = sliced[0]
        var R = sliced[1]
        println("firstSlice L: ${L.toBytes()} R: ${R.toBytes()} +")

        repeat(16) { col ->
            val l = R
            val r = L.xor(encryption(R, K(key, col))).takeLast(4).toUByteArray()
            println("${col + 1} iterate l: ${l.toBytes()} r: ${r.toBytes()} +")

            L = l
            R = r
        }

        var result = L + R
        result = replace64(result, reverseIP)
        println("final result: ${result.toBytes()}")
        return result
    }

    override fun decrypt(bytes: UByteArray, key: UByteArray): UByteArray {
        var message = bytes.take(8).toUByteArray()
        message = replace64(message, reverseIP)

        val sliced = separate(message)
        var L = sliced[1]
        var R = sliced[0]

        repeat(16) { col ->
            val r = L
            val l = R.xor(encryption(L, K(key, 15 - col))).takeLast(4).toUByteArray()

            L = l
            R = r
        }

        var result = R + L
        result = replace64(result, IP)
        return result
    }

    private fun replace64(initial: UByteArray, table: List<Int>): UByteArray {
        var s = ""
        initial.forEach { s += it.toBinary(8) }
        var bytes = ""
        table.forEach { bytes += s[it - 1] }
        val num = bytes.toULong(2)
        return UByteArray(8) {
            (num shl it * 8 shr (7 * 8)).toUByte()
        }
    }

    private fun replace32to48(initial: UByteArray, table: List<Int>): UByteArray {
        var s = ""
        initial.forEach { s += it.toBinary(8) }
        var bytes = ""
        table.forEach { bytes += s[it - 1] }
        val num = bytes.toULong(2)
        return UByteArray(6) {
            (num shl 16 shl it * 8 shr (7 * 8)).toUByte()
        }
    }

    private fun replace48forKey(initial: UByteArray, table: List<Int>): UByteArray {
        var s = ""
        initial.forEach { s += it.toBinary(8) }
        var bytes = ""
        table.forEach { bytes += s[it - 1] }
        val num = bytes.toULong(2)
        return UByteArray(6) {
            (num shl 16 shl it * 8 shr (7 * 8)).toUByte()
        }
    }

    private fun replace32(initial: UByteArray, table: List<Int>): UByteArray {
        var s = ""
        initial.forEach { s += it.toBinary(8) }
        var bytes = ""
        table.forEach { bytes += s[it - 1] }
        val num = bytes.toUInt(2)
        val result = UByteArray(4) {
            (num shl it * 8 shr (3 * 8)).toUByte()
        }
        println("result replace32: ${result.toBytes()} +")
        return result
    }

    private fun separate(initial: UByteArray): List<UByteArray> =
        listOf(initial.take(4).toUByteArray(), initial.takeLast(4).toUByteArray())

    private fun encryption(initial: UByteArray, key: UByteArray): UByteArray {
        var array = replace32to48(initial, E)
        println("Upper: ${array.toBytes()} +")
        println("Xor with key: ${key.toBytes()}")
        array = array.xor(key).takeLast(6).toUByteArray()
        println("After xor: ${array.toBytes()} +")

        val bBlocks = brokeBBlock(array)
        println("B blocks: ${bBlocks.toBytes()} +")
        var sBlocks =
            bBlocks.mapIndexed { index: Int, Ubyte: UByte -> convertS(Ubyte, index) }.toUByteArray()
        println("S blocks: ${sBlocks.toBytes()} +")
        sBlocks = plusSBlocks(sBlocks)
        println("Plus S blocks: ${sBlocks.toBytes()} +")
        return replace32(sBlocks, P)
    }

    private fun brokeBBlock(initial: UByteArray): UByteArray {
        var s = ""
        initial.forEach { s += it.toBinary(8) }
        val num = s.toULong(2)
        val bytes = UByteArray(8) {
            (num shl 16 shl it * 6 shr (7 * 8 + 2)).toUByte()
        }
        return bytes
    }

    private fun convertS(bBlock: UByte, num: Int): UByte {
        val s = bBlock.toBinary(6)
        val y = (s[0].toString() + s.last()).toInt(2)
        val x = s.drop(1).dropLast(1).toInt(2)
        println("S$num y: $y x: $x")
        return sBlocks[num][y][x]
    }

    private fun K(key: UByteArray, numIterate: Int): UByteArray {
        println("Key : ${key.toBytes()} iterate: $numIterate  0+ ")
        val newKey = replace64(key, G)
        println("Key after replace: ${newKey.toBytes()}")

        var bytes = ""
        newKey.forEach { bytes += it.toBinary(8) }
        val num = bytes.toULong(2)
        var leftKey = UByteArray(4) {
            (num shl 4 shl it * 8 shr (7 * 8)).toUByte()
        }
        var rightKey = UByteArray(4) {
            (num shl (32) shl it * 8 shr (7 * 8)).toUByte()
        }
        println("leftKey: ${leftKey.toBytes()} +")
        println("rightKey: ${rightKey.toBytes()} +")
        repeat(numIterate+1) {
            leftKey = cycleShift28(leftKey, shiftTable[it])
            rightKey = cycleShift28(rightKey, shiftTable[it])
        }
        println("leftKey after shift: ${leftKey.toBytes()} +")
        println("rightKey after shift: ${rightKey.toBytes()} +")

        val plusKey = plusKeys(leftKey, rightKey)
        println("combineKey: ${plusKey.toBytes()} +")
        val result = replace48forKey(plusKey, H)
        println("replace combineKey: ${result.toBytes()} +")
        return result
    }

    private fun cycleShift28(initial: UByteArray, col: Int): UByteArray {
        var bytes = ""
        initial.forEach { bytes += it.toBinary(8) }
        bytes = bytes.drop(4)
        bytes = bytes.drop(col) + bytes.take(col)
        var num = bytes.toUInt(2)
        return UByteArray(4) {
            (num shl (32) shl it * 8 shr (3 * 8)).toUByte()
        }
    }

    private fun plusKeys(key1: UByteArray, key2: UByteArray): UByteArray {
        var bytesLeft = ""
        var bytesRight = ""
        key1.forEachIndexed { index, Ubyte -> bytesLeft += if (index == 0) Ubyte.toBinary(4) else Ubyte.toBinary(8) }
        key2.forEachIndexed { index, Ubyte -> bytesRight += if (index == 0) Ubyte.toBinary(4) else Ubyte.toBinary(8) }
        var bytes = bytesLeft + bytesRight
        val num = bytes.toULong(2)
        return UByteArray(7) {
            (num shl 8 shl it * 8 shr (7 * 8)).toUByte()
        }
    }

    private fun plusSBlocks(sBlocks: UByteArray): UByteArray {
        var bytes = ""
        sBlocks.forEach { bytes += it.toBinary(4) }
        val num = bytes.toUInt(2)
        return UByteArray(4) {
            (num shl it * 8 shr (3 * 8)).toUByte()
        }
    }

    private fun UByteArray.xor(array: UByteArray): UByteArray {
        var s1 = ""
        var s2 = ""
        this.forEach { s1 += it.toBinary(8) }
        array.forEach { s2 += it.toBinary(8) }

        val num = s1.toULong(2) xor s2.toULong(2)
        val bytes: UByteArray = UByteArray(8) {
            (num shl it * 8 shr (7 * 8)).toUByte()
        }
        return bytes
    }
}

fun UByte.toBinary(len: Int): String {
    return String.format("%" + len + "s", this.toString(2)).replace(" ".toRegex(), "0")
}

fun UByteArray.toBytes(): String {
    var s = ""
    this.forEach { s += " " + it.toBinary(8) }
    return s
}
