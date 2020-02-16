(ns backend-api.rabbit
  (:gen-class)
  (:require [langohr.core      :as rmq]
            [langohr.channel   :as lch]
            [langohr.queue     :as lq]
            [langohr.consumers :as lc]
            [langohr.basic     :as lb]))

(def ^{:const true}
  default-exchange-name "")
(def conn (rmq/connect))
(def ch (lch/open conn))
(def qname "hellow")

(defn message-handler
  [ch {:keys [content-type delivery-tag type] :as meta} ^bytes payload]
  (println (format "[consumer] Received a message: %s, delivery tag: %d, content type: %s, type: %s"
                   (String. payload "UTF-8") delivery-tag content-type type)))

(defn init-rabbit []
  (do
  (println (format "[main] Connected. Channel id: %d" (.getChannelNumber ch)))
    (lq/declare ch qname {:durable false :auto-delete false})
    (lc/subscribe ch qname message-handler {:auto-ack true})))

(defn destroy-rabbit []
  (do
    (rmq/close ch)
    (rmq/close conn)))
