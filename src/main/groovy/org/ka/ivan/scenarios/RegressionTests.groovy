package org.ka.ivan.scenarios

class RegressionTests {

    final def tests = []

    def testRun(String name, Closure c) {
        tests.add([name, c])
    }
}
