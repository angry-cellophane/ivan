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

        test.loadFixtures {
            file '$AppData/input/input.csv' fromFixture 'Fixtures/myApp/input.csv'
            table 'MyDatabase..MyTable' fromFixture 'MyFixtureDatabase..MyTable'
            database 'Oracle' table 'MySchema.MyTable' fromFixture 'MyFixtureSchema.MyTable'
        }
        test.outputs {
            file '$AppData/output/output.csv'
        }
        test.regressionTests {
            regressionTest('oldTest') {
                copy '~/tmp/myFile.csv' to '~/tmp/myNewFile.csv'
                println "doing oldTest"
//                copy file '/source/file' to '/dest/file'
            }

            regressionTest('newTest') {
                println "doing newTest"
            }
        }
        test.beforeTest {
            removeFile '~/tmp/*'
            truncateTable 'MyTable..TableName'
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
