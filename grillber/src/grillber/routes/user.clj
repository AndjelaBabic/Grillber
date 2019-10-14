(ns grillber.routes.user
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
"Renders login page"
  []
  (layout/render "login.html"))

(defn signup-page
"Renders sign up page"
  []
  (layout/render "signup.html"))
  
(defn sign-up-on-submit 
"Stores new user in the database"
 [params]
  (let [errors (validate-user-sign-up? params)]
   (if (empty? errors)
      (do
        (db/insert-user! (dissoc params :password2))
        (layout/render "/login.html"))
      (layout/render "signup.html" (assoc params :errors errors)))))
      
(defn login-on-submit 
"Handles request for login of the user"
[{:keys [params session]}]
 (let [user (db/get-user-by-username-and-password params)]
    (if(empty? user)
   	 (layout/render "login.html" (assoc params :errors "The provided username and/or password are incorrect."))
     (assoc (redirect "/") :session (assoc session :identity user))))
 )

(defn logout 
"Handles request for logout/ redirects on login page"
[request]
  (-> (redirect "/login")
      (assoc :session {})))
                                                                     

(defroutes user-routes
           (GET "/signup" [] (signup-page))
           (POST "/signup" [& form] (sign-up-on-submit form))
           (GET "/login" [] (login-page))
           (POST "/login" request (login-on-submit request))
           (GET "/logout" request (logout request)))
