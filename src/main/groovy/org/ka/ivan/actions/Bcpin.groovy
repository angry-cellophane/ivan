package org.ka.ivan.actions

trait Bcpin {
    def bcpin(args) {
        println "bcpin: fileName = ${args['file']}, query = ${args['query']}"
    }
}
