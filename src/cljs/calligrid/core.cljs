(ns calligrid.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce app-state (atom {:number-of-lines 10
                          :distance 30}))

(defn line [y]
  [:line {:x1 0 :y1 y :x2 600 :y2 y :stroke "red" :stroke-width 5 :key y}])

(defn lines [nol dist]
  (for [y (range 5 (* nol dist) dist)]
    (line y)))

(defn change-number [event]
  (swap! app-state assoc :number-of-lines (.. event -target -value)))

(defn change-distance [event]
  (swap! app-state assoc :distance (js/parseInt (.. event -target -value))))

(defn page []
  (let [{:keys [number-of-lines distance]} @app-state]
    [:div
     [:input {:type "range" :min 10 :max 30 :step 1 :on-change change-number}]
     [:input {:type "range" :min 10 :max 30 :step 1 :on-change change-distance}]
     [:h1 (str "number of lines: " number-of-lines)]
     [:svg {:width 600 :height 800}
      (lines number-of-lines distance)]]))

(reagent/render [page] (js/document.getElementById "app"))
