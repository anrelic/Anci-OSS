package su.jfdev.util

import org.junit.rules.*

inline fun tempFolder(folder: TemporaryFolder = TemporaryFolder(), block: (TemporaryFolder) -> Unit) = folder.apply {
    create()
    try {
        block(this)
    } finally {
        delete()
    }
}