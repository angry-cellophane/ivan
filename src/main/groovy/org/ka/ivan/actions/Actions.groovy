package org.ka.ivan.actions

class Actions {

    private final def chainOperators = [
            'file',
            'table'
    ]

//    def copy = chainTo 'file', { String fileName ->
//        [to: { String targetDirName ->
//            println "copy $fileName to $targetDirName"
//        }]
//    }

    def copy = { String fileName ->
        [to: { String targetDirName ->
            println "copy $fileName to $targetDirName"
        }]
    }

    def remove = chainTo 'file', { String fileName ->
        println "rm -rf $fileName"
    }

    def truncate = chainTo 'table', {String tableName ->
        println "truncate table $tableName"

    }

    def drop = chainTo 'table', {String tableName ->
        println "drop table $tableName"
    }

    def ftp (String url, Closure c) {
        println "ftp to $url"
        def ftp = new Ftp(url)
        ftp.with c
    }

    def propertyMissing(String name) {
        if (!(name in chainOperators)) throw new MissingPropertyException(name, this.class)

        name
    }

    private Closure<PropAsClosureParam> chainTo(String expectedNextOperator, Closure c) {
        return { String actualNextOperator ->
            if (actualNextOperator != expectedNextOperator) {
                throw new IllegalArgumentException("Expecting $expectedNextOperator, but get $actualNextOperator operator")
            }
            new PropAsClosureParam(c)
        }
    }
}

class PropAsClosureParam {
    private final Closure c

    PropAsClosureParam(Closure c) {
        this.c = c
    }

    def propertyMissing(String name) {
        c(name)
    }
}
