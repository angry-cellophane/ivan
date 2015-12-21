package org.ka.ivan.actions

class Ftp {
    private final String url
    Ftp(String url) {
        this.url = url
    }

    def cd(String dirName) {
        println "ftp: cd $dirName"
    }

    def put(String fileName) {
        println "ftp: put $fileName"
    }

    def get(String fileName) {
        println "ftp: get $fileName"
    }
}
