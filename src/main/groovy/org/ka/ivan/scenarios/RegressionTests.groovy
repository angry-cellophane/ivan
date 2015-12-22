package org.ka.ivan.scenarios

class RegressionTests {

    final def tests = []

    def regressionTest(String name, Closure c) {
        tests.add([name, c])
    }
}
