(ns grillber.routes.start
  (:require [compojure.core :refer :all]
            [grillber.layout :as layout]
            [ring.util.response :as response]
            [struct.core :as st]
            [grillber.models.query_definition :as db]))

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

(defn insert-order
  "Stores new order in db"
  [request]
  (
    (str "" (get (db/insert-order! {
                                             :userid 1
                                             :grillid 1
                                             :delivery_time "2019-02-01 16:26:13"
                                             :pickup_time "2019-02-23 15:07:20"
                                             :addressid 1
                                             :status 'Processed'}) :id) )))
                                             
(defn insert-order-try
  "Stores new order in db"
  [request]
	(def delivery (:Delivery (:params request)))
	(def time (:time (:params request)))
	(def address (:Address (:params request)))
	(def municipality (:Municipality (:params request)))
	(def bbq (:time (:params request)))
	
	(def params ( :params request))
	(println params)
	(println bbq)
 )
                                             
                                             

 (defroutes start-routes
           (GET "/signup" [] (signup-page))
           (GET "/" [] (index-page))
           (POST "/insertorder" [] insert-order-try)
           (GET "/update" [] (update-page))
           (GET "/login" [] (login-page)))
