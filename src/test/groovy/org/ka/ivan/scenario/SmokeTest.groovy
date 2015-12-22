package org.ka.ivan.scenario

import org.junit.Test
import org.ka.ivan.RegressionTest


class SmokeTest {

    @Test
    void test() {
        def databases = [
                'default': 'Sybase',
                'Oracle': 'Oracle'
        ]

        def test = new RegressionTest(databases)

        test.loadFixtures {
            file '$AppData/input/input.csv' fromFixture 'Fixtures/myApp/input.csv'
            table 'MyDatabase..MyTable' fromFixture 'MyFixtureDatabase..MyTable'
        }
        test.outputs {
            file '$AppData/output/output.csv'
        }
        test.scenarios {
            scenario('oldTest') {
                copy '~/tmp/myFile.csv' to '~/tmp/myNewFile.csv'
                println "doing oldTest"
//                copy file '/source/file' to '/dest/file'
            }

            scenario('newTest') {
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

//        def builder = new NodeBuilder()
//        builder.test {
//            loadFixtures() {
//                file "\$DATA/MyApp/$date/input_data.csv" fromFixture "\$DATA/fixtures/MyTest/input_data.csv"
//                database 'Oracle' table 'MySchema.MyTable' fromFixture 'FixtureSchema.MyTable'
//                table 'MyDB..MyTable2' fromFixture 'FixtureDb..MyTable2'
//                file "\$DATA/MyApp/$date/scp_file.csv" scpFixtureFrom "user@yet-another-serer.com:/path/to/fixture/file.csv"
//                file "\$DATA/MyApp/$date/input_data.csv" foreachTest [
//                        oldTest : fromFixture '',
//                        newTest : fromFixture ''
//                ]
//            }
//            beforeTestSuit() {
//
//            }
//            beforeTest() {
//
//            }
//            oldTest() {
//                execute script '/path/to/old/script'
//            }
//            newTest() {
//                execute script '/path/to/new/script'
//            }
//            afterTest() {
//
//            }
//            afterTestSuit() {
//
//            }
//            outputs() {
//                file "\$DATA/MyApp/$date/output_data.csv"
//                table 'MyDB..OutputTable'
//            }
//        }


    }
}
