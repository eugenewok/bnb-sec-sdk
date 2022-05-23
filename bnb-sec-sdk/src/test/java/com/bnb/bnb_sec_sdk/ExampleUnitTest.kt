package com.bnb.bnb_sec_sdk

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun `test md5 hash string for hello world matches online md5 generator`() {
        val md5 = HashLib.getHashString("hello world", HashLib.HashType.MD5)
        /**
         * md5 value generated from online md5 generator: https://www.md5hashgenerator.com/
         * */
        assertEquals("5eb63bbbe01eeed093cb22bb8f5acdc3", md5)
    }

    @Test
    fun `test sha256 hash string for hello world matches online md5 generator`() {
        val sha256 = HashLib.getHashString("hello world", HashLib.HashType.SHA256)
        /**
         * md5 value generated from online sha256 generator: https://emn178.github.io/online-tools/sha256.html
         * */
        assertEquals("b94d27b9934d3e08a52e52d7da7dabfac484efe37a5380ee9088f7ace2efcde9", sha256)
    }
    @Test
    fun `test sha1 hash string for hello world matches online sha1 generator`() {
        val sha1 = HashLib.getHashString("hello world", HashLib.HashType.SHA1)
        /**
         * sha1 value generated from online sha1 generator: https://www.md5hashgenerator.com/
         * */
        assertEquals("2aae6c35c94fcfb415dbe95f408b9ce91ee846ed", sha1)
    }
}