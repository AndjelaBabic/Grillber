(ns grillber.routes.start
  (:require [compojure.core :refer :all]
            [grillber.layout :as layout]
            [ring.util.response :refer [response redirect]]
            [struct.core :as st]
            [grillber.models.query_definition :as db]))
  
(defn index-page
"Renders main page for booking"
 []
(layout/render "index.html"))
  
(defn update-page
 "Renders order history page"
 []
(layout/render "update.html"))

(defn insert-order!
  "Stores new order in db"
  [request]
     (db/insert-order! {
                                             :userid (:id (nth (get-in request [:session :identity]) 0))
                                             :grillid (get-in request [:params :radio])
                                             :delivery_time (clojure.string/join [(:Delivery (:params request)) " " (:time (:params request)) ":00"])
                                             :addressid (:id (nth (db/last-insert-id) 0))
                                             :status "Booked"}))
(defn insert-address!
  "Stores new address in db"
  [params]
     (db/insert-address! {
                                             :street_name (:Address params)
                                             :part_of_the_city (:Municipality params)
                                             })
)
                                                                                          
(defn save-order
  "Stores new order in db"
  [request]
  	(if (layout/is-authenticated? (:session request))
  	(do
  		(println :params request)
  		(insert-address! (:params request))
  		(insert-order! request)
  		(layout/render "index.html" (assoc (:params request) :message "Order successfully saved! Check it out in the order history!"))
  	)
  	(redirect "/login"))
 )
 
 (defn update-page
  "Displays a page with all orders made by logged-in user"
 [request]
  (if (layout/is-authenticated? (:session request))
  (do
  (def userid (:id (nth (get-in request [:session :identity]) 0)))
  (def orders (db/get-all-orders-by-user-id {:userid userid}))
  (layout/render "update.html"
 				{:orders orders})
  )
  (redirect "/login")))    
                                                                                              
  
  (defn delete-order
  "Deletes an order from database by id"
  [request]
  	(if (layout/is-authenticated? (:session request))
  	(do
  	(db/delete-order-by-id! {:id (:id (:params request))})
    (redirect "/")
    )
    (redirect "/login")))  
    
  (defn update-order
  "Updates an order in the database by with new address and/or different bbq"
  [request]
  (do
   (db/update-address! {
   				:id (get-in request [:params :addressid])
   				:street_name (get-in request [:params :street_name])
   				})
   (db/update-order! {
   				:id (get-in request [:params :orderid])
   				:grillid (get-in request [:params :bbqid])})
   (update-page request)
   )
   )                                                                                   

 (defroutes start-routes
           (GET "/" [] (index-page))
           (POST "/insertorder" [] save-order)
           (GET "/update" request [] (update-page request))
           (POST "/updateorder" request [] (update-order request))
           (POST "/delete" request [] (delete-order request)))
