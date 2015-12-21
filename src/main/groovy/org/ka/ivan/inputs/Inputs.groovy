package org.ka.ivan.inputs

import groovy.transform.TupleConstructor

class Inputs {

    private final Map<String, String> databases

    Inputs(Map<String, String> databases) {
        this.databases = databases
    }

    def file(String fileName) {
        [fromFixture: { String fixtureFileName ->
            println "copy $fileName from $fixtureFileName"
        }]
    }

    def database(String databaseName) {
        [table: { String tableName ->
            _table(databaseName, tableName)
        }]
    }

    private def _table(String databaseName, String tableName) {
        [fromFixture: { String fixtureTableName ->
            println "copy data from table $fixtureTableName to $tableName in $databaseName"
        }]
    }

    def table(String tableName) {
        def databaseName = databases['default']
        _table(databaseName, tableName)
    }
}
