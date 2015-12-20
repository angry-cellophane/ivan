package org.ka.ivan.inputs


class Inputs {
    def file(String fileName) {
        [
               fromFixture: { String fixtureFileName ->
                   println "copy $fileName from $fixtureFileName"
               }
        ]
    }
}
