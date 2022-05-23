package com.bnb.bnb_sec_sdk

import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HashLibUnitTest {
    @get:Rule
    var folder = TemporaryFolder()

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
        val createdFile = folder.newFile("test-hash.txt")
        val md5 = HashLib.getFileHash(createdFile, HashLib.HashType.MD5)
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", md5)
    }

    @Test
    fun `test calculate file SHA1 hash string matches online SHA1 generator`() {
        val createdFile = folder.newFile("test-hash.txt")
        val sha1 = HashLib.getFileHash(createdFile, HashLib.HashType.SHA1)
        assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", sha1)
    }

    @Test
    fun `test calculate file SHA256 hash string matches online SHA256 generator`() {
        val createdFile = folder.newFile("test-hash.txt")
        val sha1 = HashLib.getFileHash(createdFile, HashLib.HashType.SHA256)
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", sha1)
    }
}