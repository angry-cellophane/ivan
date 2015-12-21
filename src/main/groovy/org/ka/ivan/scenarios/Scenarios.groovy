package org.ka.ivan.scenarios

class Scenarios {

    final def scenarios = []

    def scenario(String name, Closure c) {
        scenarios.add([name, c])
    }
}
