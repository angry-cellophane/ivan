package org.ka.ivan.scenario

import org.junit.Test
import org.ka.ivan.RegressionTest


class SmokeTest {

    @Test
    void test() {
//        def date = getDateFromDatabase()

        def test = new RegressionTest()
        test.inputs {
            file "\$AppData/input/input${it}.csv" fromFixture "\$Fixtures/myApp/input${it}.csv"
        }
        test.outputs {
            file '$APPData/output/output.csv'
        }
        def a = 1
        test.oldTest {
            println "do oldTest"
            println "oldTest: a = $a"
//            copy file '/source/file' to '/dest/file'
        }
        test.newTest {
            println "do newTest"
        }
        test.execute()

//        def builder = new NodeBuilder()
//        builder.test {
//            inputs() {
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
