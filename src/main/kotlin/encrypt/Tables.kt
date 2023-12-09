package encrypt

object Tables {
    val IP = listOf(
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    )

    val reverseIP = listOf(
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25
    )

    val E = listOf(
        32, 1, 2, 3, 4, 5,
        4, 5, 6, 7, 8, 9,
        8, 9, 10, 11, 12, 13,
        12, 13, 14, 15, 16, 17,
        16, 17, 18, 19, 20, 21,
        20, 21, 22, 23, 24, 25,
        24, 25, 26, 27, 28, 29,
        28, 29, 30, 31, 32, 1
    )

    val S1 = listOf<UByteArray>(
        ubyteArrayOf(14u, 4u, 13u, 1u, 2u, 15u, 11u, 8u, 3u, 10u, 6u, 12u, 5u, 9u, 0u, 7u),
        ubyteArrayOf(0u, 15u, 7u, 4u, 14u, 2u, 13u, 1u, 10u, 6u, 12u, 11u, 9u, 5u, 3u, 8u),
        ubyteArrayOf(4u, 1u, 14u, 8u, 13u, 6u, 2u, 11u, 15u, 12u, 9u, 7u, 3u, 10u, 5u, 0u),
        ubyteArrayOf(15u, 12u, 8u, 2u, 4u, 9u, 1u, 7u, 5u, 11u, 3u, 14u, 10u, 0u, 6u, 13u)
    )
    val S2 = listOf<UByteArray>(
        ubyteArrayOf(15u, 1u, 8u, 14u, 6u, 11u, 3u, 4u, 9u, 7u, 2u, 13u, 12u, 0u, 5u, 10u),
        ubyteArrayOf(3u, 13u, 4u, 7u, 15u, 2u, 8u, 14u, 12u, 0u, 1u, 10u, 6u, 9u, 11u, 5u),
        ubyteArrayOf(0u, 14u, 7u, 11u, 10u, 4u, 13u, 1u, 5u, 8u, 12u, 6u, 9u, 3u, 2u, 15u),
        ubyteArrayOf(13u, 8u, 10u, 1u, 3u, 15u, 4u, 2u, 11u, 6u, 7u, 12u, 0u, 5u, 14u, 9u)
    )
    val S3 = listOf<UByteArray>(
        ubyteArrayOf(10u, 0u, 9u, 14u, 6u, 3u, 15u, 5u, 1u, 13u, 12u, 7u, 11u, 4u, 2u, 8u),
        ubyteArrayOf(13u, 7u, 0u, 9u, 3u, 4u, 6u, 10u, 2u, 8u, 5u, 14u, 12u, 11u, 15u, 1u),
        ubyteArrayOf(13u, 6u, 4u, 9u, 8u, 15u, 3u, 0u, 11u, 1u, 2u, 12u, 5u, 10u, 14u, 7u),
        ubyteArrayOf(1u, 10u, 13u, 0u, 6u, 9u, 8u, 7u, 4u, 15u, 14u, 3u, 11u, 5u, 2u, 12u)
    )
    val S4 = listOf<UByteArray>(
        ubyteArrayOf(7u, 13u, 14u, 3u, 0u, 6u, 9u, 10u, 1u, 2u, 8u, 5u, 11u, 12u, 4u, 15u),
        ubyteArrayOf(13u, 8u, 11u, 5u, 6u, 15u, 0u, 3u, 4u, 7u, 2u, 12u, 1u, 10u, 14u, 9u),
        ubyteArrayOf(10u, 6u, 9u, 0u, 12u, 11u, 7u, 13u, 15u, 1u, 3u, 14u, 5u, 2u, 8u, 4u),
        ubyteArrayOf(3u, 15u, 0u, 6u, 10u, 1u, 13u, 8u, 9u, 4u, 5u, 11u, 12u, 7u, 2u, 14u)
    )
    val S5 = listOf<UByteArray>(
        ubyteArrayOf(2u, 12u, 4u, 1u, 7u, 10u, 11u, 6u, 8u, 5u, 3u, 15u, 13u, 0u, 14u, 9u),
        ubyteArrayOf(14u, 11u, 2u, 12u, 4u, 7u, 13u, 1u, 5u, 0u, 15u, 10u, 3u, 9u, 8u, 6u),
        ubyteArrayOf(4u, 2u, 1u, 11u, 10u, 13u, 7u, 8u, 15u, 9u, 12u, 5u, 6u, 3u, 0u, 14u),
        ubyteArrayOf(11u, 8u, 12u, 7u, 1u, 14u, 2u, 13u, 6u, 15u, 0u, 9u, 10u, 4u, 5u, 3u)
    )
    val S6 = listOf<UByteArray>(
        ubyteArrayOf(12u, 1u, 10u, 15u, 9u, 2u, 6u, 8u, 0u, 13u, 3u, 4u, 14u, 7u, 5u, 11u),
        ubyteArrayOf(10u, 15u, 4u, 2u, 7u, 12u, 9u, 5u, 6u, 1u, 13u, 14u, 0u, 11u, 3u, 8u),
        ubyteArrayOf(9u, 14u, 15u, 5u, 2u, 8u, 12u, 3u, 7u, 0u, 4u, 10u, 1u, 13u, 11u, 6u),
        ubyteArrayOf(4u, 3u, 2u, 12u, 9u, 5u, 15u, 10u, 11u, 14u, 1u, 7u, 6u, 0u, 8u, 13u)
    )
    val S7 = listOf<UByteArray>(
        ubyteArrayOf(4u, 11u, 2u, 14u, 15u, 0u, 8u, 13u, 3u, 12u, 9u, 7u, 5u, 10u, 6u, 1u),
        ubyteArrayOf(13u, 0u, 11u, 7u, 4u, 9u, 1u, 10u, 14u, 3u, 5u, 12u, 2u, 15u, 8u, 6u),
        ubyteArrayOf(1u, 4u, 11u, 13u, 12u, 3u, 7u, 14u, 10u, 15u, 6u, 8u, 0u, 5u, 9u, 2u),
        ubyteArrayOf(6u, 11u, 13u, 8u, 1u, 4u, 10u, 7u, 9u, 5u, 0u, 15u, 14u, 2u, 3u, 12u)
    )
    val S8 = listOf<UByteArray>(
        ubyteArrayOf(13u, 2u, 8u, 4u, 6u, 15u, 11u, 1u, 10u, 9u, 3u, 14u, 5u, 0u, 12u, 7u),
        ubyteArrayOf(1u, 15u, 13u, 8u, 10u, 3u, 7u, 4u, 12u, 5u, 6u, 11u, 0u, 14u, 9u, 2u),
        ubyteArrayOf(7u, 11u, 4u, 1u, 9u, 12u, 14u, 2u, 0u, 6u, 10u, 13u, 15u, 3u, 5u, 8u),
        ubyteArrayOf(2u, 1u, 14u, 7u, 4u, 10u, 8u, 13u, 15u, 12u, 9u, 0u, 3u, 5u, 6u, 11u)
    )

    val sBlocks = listOf(S1, S2, S3, S4, S5, S6, S7, S8)

    val P = listOf(
        16, 7, 20, 21,
        29, 12, 28, 17,
        1, 15, 23, 26,
        5, 18, 31, 10,
        2, 8, 24, 14,
        32, 27, 3, 9,
        19, 13, 30, 6,
        22, 11, 4, 25
    )

    val G = listOf(
        57, 49, 41, 33, 25, 17, 9,
        1, 58, 50, 42, 34, 26, 18,
        10, 2, 59, 51, 43, 35, 27,
        19, 11, 3, 60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
        7, 62, 54, 46, 38, 30, 22,
        14, 6, 61, 53, 45, 37, 29,
        21, 13, 5, 28, 20, 12, 4
    )

    val shiftTable = listOf(1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1)

    val H = listOf(
        14, 17, 11, 24, 1, 5,
        3, 28, 15, 6, 21, 10,
        23, 19, 12, 4, 26, 8,
        16, 7, 27, 20, 13, 2,
        41, 52, 31, 37, 47, 55,
        30, 40, 51, 45, 33, 48,
        44, 49, 39, 56, 34, 53,
        46, 42, 50, 36, 29, 32
    )
}