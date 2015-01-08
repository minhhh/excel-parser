(defproject excel-parser "0.1.0-SNAPSHOT"
  :description ""
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.apache.poi/poi "3.11"]
                 [org.apache.poi/poi-ooxml "3.11"]
                 ]
  :main ^:skip-aot excel-parser.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
