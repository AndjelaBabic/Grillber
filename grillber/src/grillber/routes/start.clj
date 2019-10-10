(ns grillber.routes.start
  (:require [compojure.core :refer :all]
            [grillber.layout :as layout]
            [ring.util.response :refer [response redirect]]
            [struct.core :as st]
            [grillber.models.query_definition :as db]))

(def user-schema-sign-up
  [
   [:password
    st/string {:message  "Password must contain at least 6 characters"
               :validate #(> (count %) 6)}]
   [:email [st/email :message "Email not in a valid format"]]])  
   
   
(defn validate-user-sign-up? [user]
  (first (st/validate user user-schema-sign-up)))
  
 
(defn login-page
  []
  (layout/render "login.html"))

(defn signup-page
  []
  (layout/render "signup.html"))
  
(defn sign-up-on-submit 
 [params]
  (let [errors (validate-user-sign-up? params)]
   (if (empty? errors)
      (do
        (db/insert-user! (dissoc params :password2))
        (layout/render "/login.html"))
      (layout/render "signup.html" (assoc params :errors errors)))))
      
(defn login-on-submit [{:keys [params session]}]
 (let [user (db/get-user-by-username-and-password params)]
    (if(empty? user)
   	 (layout/render "login.html" (assoc params :errors "The provided username and/or password are incorrect."))
     (assoc (redirect "/") :session (assoc session :identity user))))
 )
  
(defn index-page
 []
(layout/render "index.html"))
  
(defn update-page
 []
(layout/render "update.html"))

(defn logout [request]
  (-> (redirect "/login")
      (assoc :session {})))

(defn insert-order!
  "Stores new order in db"
  [request]
     (db/insert-order! {
                                             :userid (:id (nth (get-in request [:session :identity]) 0))
                                             :grillid 1
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
  		(insert-address! (:params request))
  		(insert-order! request)
  		(layout/render "index.html" (assoc (:params request) :message "Order successfully saved! Check it out in the order history!"))
  	)
  	(redirect "/login"))
 )
 
 (defn update-page
 [request]
  (if (layout/is-authenticated? (:session request))
  (do
  (def userid (:id (nth (get-in request [:session :identity]) 0)))
  (def orders (db/get-all-orders-by-user-id {:userid userid}))
  (println orders)
  (layout/render "update.html"
 				{:orders orders})
  )
  (redirect "/login")))    
                                                                                              
  
  (defn delete-order 
  [request]
  	(if (layout/is-authenticated? (:session request))
  	(do
  	(db/delete-order-by-id! {:id (:id (:params request))})
    (redirect "/")
    )
    (redirect "/login")))  
    
   ; TODO fix redirect
  (defn update-order 
  [request]
  (do
   (db/update-address! {
   				:id (get-in request [:params :addressid])
   				:street_name (get-in request [:params :street_name])
   				})
   (db/update-order! {
   				:id (get-in request [:params :orderid])
   				:grillid (get-in request [:params :bbqid])})
   (redirect "/")
   )
   )                                                                                   

 (defroutes start-routes
           (GET "/signup" [] (signup-page))
           (POST "/signup" [& form] (sign-up-on-submit form))
           (GET "/" [] (index-page))
           (POST "/insertorder" [] save-order)
           (GET "/update" request [] (update-page request))
           (POST "/updateorder" request [] (update-order request))
           (GET "/login" [] (login-page))
           (POST "/login" request (login-on-submit request))
           (GET "/logout" request (logout request))
           (POST "/delete" request [] (delete-order request)))
