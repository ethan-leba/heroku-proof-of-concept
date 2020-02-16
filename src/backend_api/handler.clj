(ns backend-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(def notfound "Not Found")

(defn handle-submit [request] (str request))

(defroutes app-routes
  (GET "/api/" [] "DATA")
  (GET "/api/submit" request (handle-submit request))
  (route/resources "/public/")
  (route/not-found notfound))

(def app
  (wrap-defaults app-routes site-defaults))
