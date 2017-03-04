(ns calligrid.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [calligrid.core-test]
   [calligrid.common-test]))

(enable-console-print!)

(doo-tests 'calligrid.core-test
           'calligrid.common-test)
