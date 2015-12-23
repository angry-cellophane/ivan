package org.ka.ivan

class Configs {

    final def props = [
            'tempDir': "~/tmp/regressionTest-${new Date().format("YYYY-MM-dd")}"]

    def propertyMissing(String name, def arg) {
        props[name] = arg[0]
    }
}
