package org.ka.ivan

import org.ka.ivan.actions.Actions
import org.ka.ivan.inputs.Inputs
import org.ka.ivan.outputs.Outputs
import org.ka.ivan.outputs.OutputsChecker
import org.ka.ivan.scenarios.RegressionTests

class RegressionTestRunner {

    private final Configs configs
    private Closure inputs
    private OutputsChecker outputsChecker

    private final Map<String, String> databases

    private final RegressionTests scenarios

    private Closure beforeTestSuit
    private Closure afterTestSuit
    private Closure beforeTest
    private Closure afterTest

    RegressionTestRunner(Map<String, String> databases) {
        this.databases = databases
        assert 'default' in this.databases

        scenarios = new RegressionTests()
        configs = new Configs()
    }

    def configs(Closure c) {
        configs.with c
    }

    def inputs(Closure c) {
        def inp = new Inputs(databases)
        inputs = {inp.with c}
    }

    def outputs(Closure c) {
        def outputs = new Outputs()
        outputs.with c
        this.outputsChecker = new OutputsChecker(outputs: outputs)
    }

    def regressionTests(Closure c) {
        scenarios.with c
    }

    def beforeTestSuit(Closure c) {
        beforeTestSuit = c
    }

    def afterTestSuit(Closure c) {
        afterTestSuit = c
    }

    def beforeTest(Closure c) {
        beforeTest = c
    }

    def afterTest(Closure c) {
        afterTest = c
    }

    def execute() {
        def actions = new Actions()

        if (beforeTestSuit) {
            actions.with beforeTestSuit
        }

        println 'inputs'
        inputs()

        def beforeTest = beforeTest
        def afterTest = afterTest

        scenarios.tests.each { name, c ->
            if (beforeTest) {
                actions.with beforeTest
            }
            println "do test $name"
            actions.with c
            if (afterTest) {
                actions.with afterTest
            }

        }


        println 'checking outputs'
//        outputsChecker.check()

        if (afterTestSuit) {
            actions.with afterTestSuit
        }
    }
}
