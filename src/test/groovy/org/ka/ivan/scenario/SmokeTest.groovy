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
