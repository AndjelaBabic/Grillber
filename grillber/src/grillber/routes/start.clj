(ns grillber.routes.start
  (:require [compojure.core :refer :all]
            [grillber.layout :as layout]
            [ring.util.response :as response]
            [struct.core :as st]))

(defn login-page
  []
  (layout/render "login.html"))

(defn signup-page
  []
  (layout/render "signup.html"))
  
(defn index-page
 []
(layout/render "index.html"))
  
(defn update-page
 []
(layout/render "update.html"))

 (defroutes start-routes
           (GET "/signup" [] (signup-page))
           (GET "/index" [] (index-page))
           (GET "/update" [] (update-page))
           (GET "/login" [] (login-page)))
