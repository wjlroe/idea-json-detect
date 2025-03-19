package com.github.wjlroe.ideajsondetect

import com.intellij.json.JsonFileType
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.fileTypes.FileTypeRegistry
import com.intellij.openapi.util.io.ByteSequence
import com.intellij.openapi.vfs.VirtualFile

class JsonFileTypeDetector : FileTypeRegistry.FileTypeDetector {
    override fun detect(file: VirtualFile, firstBytes: ByteSequence, firstCharsIfText: CharSequence?): FileType? {
        if (firstCharsIfText != null) {
            if (firstCharsIfText.isEmpty()) {
                return null
            }
            if (firstCharsIfText[0] != '{') {
                return null
            }
            // ok looks like JSON? Check for a field in particular
            if ("\"\$schema\"" in firstCharsIfText) {
                return JsonFileType.INSTANCE
            }
        }
        return null
    }
}
