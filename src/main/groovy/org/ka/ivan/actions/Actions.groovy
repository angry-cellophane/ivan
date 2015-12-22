package org.ka.ivan.actions

class Actions {

    def copy(String fileName) {
        [to: { String targetDirName ->
            println "copy $fileName to $targetDirName"
        }]
    }

    def removeFile(String fileName) {
        println "remove file $fileName"
    }

    def truncateTable(String tableName) {
        println "truncate table $tableName"
    }

    def ftp (String url, Closure c) {
        println "ftp to $url"
        def ftp = new Ftp(url)
        ftp.with c
    }

    def propertyMissing(String name) {
        return null
    }
}
