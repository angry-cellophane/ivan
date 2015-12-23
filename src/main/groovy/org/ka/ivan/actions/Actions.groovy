package org.ka.ivan.actions

class Actions implements Sql, Bcpin {

    private final def chainOperators = [
            'file',
            'table'
    ]

    def cp = { String firstParam ->
        if (firstParam =~ /-.*/) {
            def options = firstParam
            new PropAsClosureParam({ String sourceDir ->
                return {String destDir ->
                    println "cp $options $sourceDir $destDir"
                }
            })
        } else {
            def sourceDir = firstParam
            new PropAsClosureParam({ String destDir ->
                println "cp $sourceDir $destDir"
            })
        }

    }

    def run = {String scriptPath ->
        println ". $scriptPath"
    }

    def cd = {String dirName ->
        println "cd $dirName"
    }

    def rm = {String firstParam ->
        if (firstParam =~ /-.*/) {
            def options = firstParam
            new PropAsClosureParam({ String fileNames ->
                println "rm $options $fileNames"
            })
        } else {
            def file = firstParam
            println "rm $file"
        }
    }

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

    def methodMissing(String name, def args) {
        c(name).call(*args)
    }
}
