package org.ka.ivan.outputs

import org.ka.ivan.checkers.CsvFileChecker

class OutputsChecker {
    Outputs outputs
    private def checkers = [
            csv: new CsvFileChecker().&check
    ]

    def check() {
        outputs.files.each checkers['csv']
    }
}
