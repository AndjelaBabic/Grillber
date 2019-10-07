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

(defn insert-order
(defn logout [request]
  (-> (redirect "/login")
      (assoc :session {})))


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
           (POST "/signup" [& form] (sign-up-on-submit form))
           (GET "/" [] (index-page))
           (POST "/insertorder" [] insert-order-try)
           (GET "/update" [] (update-page))
           (GET "/login" [] (login-page))
           (POST "/login" request (login-on-submit request)))
           (GET "/logout" request (logout request)))
