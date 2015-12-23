package org.ka.ivan.actions


trait Sql {
    def select(String columns) {
        [from: {String tableName ->
            println "select $columns from $tableName"
        }]
    }

    def database(String dbName) {
        [request: {String sql ->
            [into: {String fileName ->
                println "from $dbName $sql into file $fileName"
            },
             forEachRow: { c ->
                 println "emulating request"
                 def dummyRows = [
                         ['id':1, 'name':'Ivan', 'surname' : 'Drago'],
                         ['id':2, 'name':'Ivan', 'surname' : 'Kupalo']
                 ]
                 dummyRows.each c
             }]
        }]
    }
}
