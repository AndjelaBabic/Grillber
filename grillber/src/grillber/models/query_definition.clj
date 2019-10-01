(ns grillber.models.query_definition
  (:require [yesql.core :refer [defqueries]]))
  
(def db-spec (clojure.edn/read-string (slurp "config.edn")))

(defqueries "grillber/models/queries.sql" {:connection db-spec})
