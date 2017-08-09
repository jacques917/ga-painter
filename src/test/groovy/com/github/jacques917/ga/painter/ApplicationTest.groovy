package com.github.jacques917.ga.painter

import spock.lang.Specification

class ApplicationTest extends Specification {

    def 'Just should work'() {
        when:
            def result = new Application().start()

        then:
            result == 'Yo!'
    }

}
