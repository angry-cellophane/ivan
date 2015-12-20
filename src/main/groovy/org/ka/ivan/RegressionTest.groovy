package org.ka.ivan

import org.ka.ivan.inputs.Inputs
import org.ka.ivan.outputs.Outputs
import org.ka.ivan.outputs.OutputsChecker

class RegressionTest {

    private Closure inputs
    private OutputsChecker outputsChecker
    private final def tests = []

    def inputs(Closure c) {
        inputs = {new Inputs().with c}
    }

    def outputs(Closure c) {
        def outputs = new Outputs()
        outputs.with c
        this.outputsChecker = new OutputsChecker(outputs: outputs)
    }

    def execute() {
        println 'executing test'
        println 'inputs'
        inputs()
        println 'tests'

        tests.each { it[1][0]() }
        println 'checking outputs'
//        outputsChecker.check()
    }

    def methodMissing(String name, def args) {
        if (!(name =~ /.*Test/)) throw new MissingMethodException(name, this.class)

        tests.add([name, args])
    }
}
