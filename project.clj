(defproject caiorulli.github.io "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring "1.7.1"]
                 [stasis "2.5.0"]
                 [hiccup "1.0.5"]
                 [garden "1.3.9"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler caiorulli.github.io/app}
  :repl-options {:init-ns caiorulli.github.io}
  :aliases {"build-site" ["run" "-m" "caiorulli.github.io/export"]})
