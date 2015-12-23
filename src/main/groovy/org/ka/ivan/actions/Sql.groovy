package org.ka.ivan.actions


trait Sql {
    def select(String columns) {
        [from: {String tableName ->
            println "select $columns from $tableName"
        }]
    }
}
