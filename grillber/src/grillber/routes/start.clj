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

(def message-schema
  [[:username
    st/required
    st/string
    ]
   [:password
    [st/required :message "This field is required"]
    st/string
    {:message  "Password must contain at least 6 characters"
     :validate #(> (count %) 6)}
    ]
   [:email
    st/required
    st/email
    ]])

(defn validate-message [params]
  (first (st/validate params message-schema)))

(defn signup-page-submit [params]
  (let [errors (validate-message params)]
    (if (empty? errors)
      (response/redirect "/login")
      (layout/render "signup.html" (assoc params :errors errors)))))

  (defroutes start-routes
           (GET "/signup" [] (signup-page))
           (POST "/signup" [& form] (signup-page-submit form))
           (GET "/login" [] (login-page)))
