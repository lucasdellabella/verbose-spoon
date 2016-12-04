(defproject verbose-spoon "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [ring "1.5.0"]
                 [ring/ring-defaults "0.2.1"]
                 [compojure "1.5.1"]
                 [hiccup "1.0.5"]
                 [proto-repl "0.3.1"]
                 [mysql/mysql-connector-java "5.1.18"]
                 [com.cemerick/url "0.1.1"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler phase3.routes/app})
