package com.bnb.bnb_sec_sdk

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class HashLibUnitTest {
    @Test
    fun `test md5 hash string for hello world matches online md5 generator`() {
        val md5 = HashLib.getStringHash("hello world", HashLib.HashType.MD5)
        assertEquals("5eb63bbbe01eeed093cb22bb8f5acdc3", md5)
    }

    @Test
    fun `test sha256 hash string for hello world matches online md5 generator`() {
        val sha256 = HashLib.getStringHash("hello world", HashLib.HashType.SHA256)
        assertEquals("b94d27b9934d3e08a52e52d7da7dabfac484efe37a5380ee9088f7ace2efcde9", sha256)
    }

    @Test
    fun `test sha1 hash string for hello world matches online sha1 generator`() {
        val sha1 = HashLib.getStringHash("hello world", HashLib.HashType.SHA1)
        assertEquals("2aae6c35c94fcfb415dbe95f408b9ce91ee846ed", sha1)
    }

    @Test
    fun `test calculate file md5 hash string matches online md5 generator`() {
        val file = File("src/test/test-hash.txt")
        val md5 = HashLib.getFileHash(file, HashLib.HashType.MD5)
        assertEquals("1397c8e097423f2099d29fc72143b3e6", md5)
    }

    @Test
    fun `test calculate file SHA1 hash string matches online SHA1 generator`() {
        val file = File("src/test/test-hash.txt")
        val sha1 = HashLib.getFileHash(file, HashLib.HashType.SHA1)
        assertEquals("5df9954f1ca26eabf18c663cc9258f7f1fd09f9e", sha1)
    }

    @Test
    fun `test calculate file SHA256 hash string matches online SHA256 generator`() {
        val file = File("src/test/test-hash.txt")
        val sha1 = HashLib.getFileHash(file, HashLib.HashType.SHA256)
        assertEquals("f250fc8f40aeea3297c0158ec1bfa07b503805f2a822530bd63c50625bb9376b", sha1)
    }
}