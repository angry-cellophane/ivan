package org.ka.ivan.scenario

import org.junit.Test
import org.ka.ivan.RegressionTestRunner


class SmokeTest {

    @Test
    void test() {
        def databases = [
                'default': 'Sybase',
                'Oracle': 'Oracle'
        ]

        def test = new RegressionTestRunner(databases)

        test.configs {
            tempDir = '~/tmp'
        }
        test.inputs {
            file '$AppData/input/input.csv' copyOf 'Fixtures/myApp/input.csv'
            table 'MyDatabase..MyTable' copyOf 'MyFixtureDatabase..MyTable'
            database 'Oracle' table 'MySchema.MyTable' copyOf 'MyFixtureSchema.MyTable'
            file '$AppData/input/input.csv' copyOf 'classpath:env/test/fixtures/myApp/input.csv'
        }
        test.outputs {
            file '$AppData/output/output.csv'
        }
        test.regressionTests {
            regressionTest('oldTest') {
                println "doing oldTest"
                cd '/source'
                cp './file' '/dest/folder'
                cp '-r' './folder' '/dest/folder'
                run '$SCRIPTS_HOME/script1'
                rm '-rf' '~/tmp1'
                bcpin 'file':'~/tmp', 'query':'select * from MyTable'
                database 'Sybase' request 'select * from DB..Persons' forEachRow { row ->
                    println "id: ${row['id']}, name: ${row['name']}, surname: ${row['surname']}"
                }

                database 'Sybase' request 'select * from DB..Mytable' into '~/tmp/dump'
            }

            regressionTest('newTest') {
                println "doing newTest"
                drop table 'MyDatabase..MyTable'
            }
        }
        test.beforeTest {
            remove file '~/tmp/*'
            truncate table 'MyTable..TableName'
        }
        test.afterTestSuit {
            ftp ('user@host.com:/path/to/dir/') {
                cd 'some/dir'
                put 'myFile.txt'
                get 'myFile.txt'
            }
        }


        test.execute()
    }
}
